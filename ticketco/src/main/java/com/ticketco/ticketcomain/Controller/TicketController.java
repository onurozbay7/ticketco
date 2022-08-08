package com.ticketco.ticketcomain.Controller;

import com.ticketco.ticketcomain.Dto.TicketDto;
import com.ticketco.ticketcomain.Dto.TripDto;
import com.ticketco.ticketcomain.Exception.TicketcoException;
import com.ticketco.ticketcomain.Exception.TripNotFoundException;
import com.ticketco.ticketcomain.Exception.UserNotFoundException;
import com.ticketco.ticketcomain.Model.Ticket;
import com.ticketco.ticketcomain.Service.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }


    @GetMapping
    public ResponseEntity<ResponseEntity<Set<TicketDto>>> getMyTickets() throws UserNotFoundException {
        return ResponseEntity.ok().body(ticketService.findMyTickets());
    }


    @PostMapping("/{tripId}")
    public ResponseEntity<List<TicketDto>> buyTickets(@RequestBody List<TicketDto> ticketDtos, @PathVariable Long tripId) throws TicketcoException, UserNotFoundException, TripNotFoundException {
        return ticketService.buyTickets(ticketDtos, tripId);
    }

    @GetMapping("/totalAmount")
    ResponseEntity<Double> getTotalAmount(){
       return ticketService.getTotalAmount();
    }


    @GetMapping("/tickets")
    ResponseEntity<List<TicketDto>> getTickets(){
       return ticketService.getTickets();
    }
}
