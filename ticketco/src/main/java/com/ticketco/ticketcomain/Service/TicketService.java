package com.ticketco.ticketcomain.Service;

import com.ticketco.ticketcomain.Client.PaymentClient;
import com.ticketco.ticketcomain.Dto.NotificationDto;
import com.ticketco.ticketcomain.Dto.PaymentDto;
import com.ticketco.ticketcomain.Dto.TicketDto;
import com.ticketco.ticketcomain.Model.Enums.Gender;
import com.ticketco.ticketcomain.Model.Enums.NotificationType;
import com.ticketco.ticketcomain.Model.Enums.PaymentType;
import com.ticketco.ticketcomain.Model.Enums.UserType;
import com.ticketco.ticketcomain.Model.Ticket;
import com.ticketco.ticketcomain.Model.Trip;
import com.ticketco.ticketcomain.Model.User;
import com.ticketco.ticketcomain.Repository.TicketRepository;
import com.ticketco.ticketcomain.Repository.TripRepository;
import com.ticketco.ticketcomain.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TicketService {

    final UserRepository userRepository;

     final ModelMapper modelMapper;
    final TicketRepository ticketRepository;

    final AmqpTemplate amqpTemplate;

    final TripRepository tripRepository;

    final PaymentClient paymentClient;

    public TicketService(UserRepository userRepository, ModelMapper modelMapper, TicketRepository ticketRepository, AmqpTemplate amqpTemplate, TripRepository tripRepository, PaymentClient paymentClient) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.ticketRepository = ticketRepository;
        this.amqpTemplate = amqpTemplate;
        this.tripRepository = tripRepository;
        this.paymentClient = paymentClient;
    }

    public ResponseEntity<Set<TicketDto>> findMyTickets(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);

        Set<Ticket> tickets = user.getTickets();

        log.info("Kullanıcı biletlerini görüntüledi");

        return ResponseEntity.ok().body(tickets.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).collect(Collectors.toSet()));
    }

    public ResponseEntity<List<TicketDto>> buyTickets(List<TicketDto> ticketDtos, Long tripId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email);
        Trip foundTrip = tripRepository.findById(tripId).orElseThrow();

        if(user.getUserType() == UserType.CORPORATE){
            if(user.getTickets().stream().filter(ticket -> Objects.equals(ticket.getTrip().getId(), tripId)).count() < 20){
                return saveTickets(ticketDtos,foundTrip,user);
            } else {
                throw new RuntimeException("");
            }
        }

        else if(user.getUserType() == UserType.INDIVIDUAL){

            if(user.getTickets().stream().filter(ticket -> Objects.equals(ticket.getTrip().getId(), tripId)).count() + ticketDtos.size() <= 5 && ticketDtos.stream().filter(ticketDto -> ticketDto.getPassenger().getGender() == Gender.MALE).count() <= 2){
                return saveTickets(ticketDtos,foundTrip,user);
            } else {
                throw new RuntimeException("");
            }
        }
       else throw new RuntimeException("");
    }

    public ResponseEntity<List<TicketDto>> saveTickets (List<TicketDto> ticketDtos, Trip trip, User user) {
        List<Ticket> tickets = ticketDtos.stream().map(ticketDto -> modelMapper.map(ticketDto, Ticket.class)).toList();
        tickets.forEach(ticket -> {
            ticket.setTrip(trip);
            ticket.setUser(user);
            amqpTemplate.convertAndSend(new NotificationDto(ticket.getPassenger().getPhone(),"Bilet Detayları","Bilet alma işlemi başarılı", NotificationType.SMS));
            paymentClient.createPayment(new PaymentDto(user.getEmail(), PaymentType.CREDITCARD, LocalDate.now(), trip.getPrice()));


            ticketRepository.save(ticket);
        });



        return ResponseEntity.ok().body(tickets.stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList());
    }


    public ResponseEntity<Double> getTotalAmount() {

        Double sum = ticketRepository.findAll().stream().mapToDouble(Ticket::getAmount).sum();
        return ResponseEntity.ok().body(sum);
    }

    public ResponseEntity<List<TicketDto>> getTickets() {
        return ResponseEntity.ok().body(ticketRepository.findAll().stream().map(ticket -> modelMapper.map(ticket, TicketDto.class)).toList());
    }
}
