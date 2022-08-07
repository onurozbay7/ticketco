package com.ticketco.ticketcopanel.Service;



import com.ticketco.ticketcopanel.Dto.TripDto;
import com.ticketco.ticketcopanel.Model.Admin;
import com.ticketco.ticketcopanel.Model.Enums.VehicleStatus;
import com.ticketco.ticketcopanel.Model.Enums.VehicleType;
import com.ticketco.ticketcopanel.Model.Trip;
import com.ticketco.ticketcopanel.Repository.TripRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TripService {

    final TripRepository tripRepository;

    final ModelMapper modelMapper;


    public ResponseEntity<List<TripDto>> getAllTrips() {

        List<Trip> trips = tripRepository.findAll();

        log.info("Tüm seferler görüntülendi.");

        return ResponseEntity.ok().body(trips.stream().map(trip -> modelMapper.map(trip, TripDto.class)).toList());
    }

    public ResponseEntity<TripDto> getTrip(Long id) {
        Trip trip = tripRepository.findById(id).orElseThrow();

        log.info(id + " id'li sefer görüntülendi.");

        return ResponseEntity.ok().body(modelMapper.map(trip, TripDto.class));
    }

    public ResponseEntity<TripDto> createTrip(TripDto tripDto) {

            Trip trip = modelMapper.map(tripDto, Trip.class);
            if (trip.getVehicleType() == VehicleType.BUS) trip.setCapacity(45); else trip.setCapacity(189);
            trip.setStatus(VehicleStatus.ACTIVE);
            log.info(tripDto.getFromStation() + " şehrinden "  + tripDto.getToStation() + " şehrine" + tripDto.getTripCode() +"sayılı yeni sefer eklendi.");
            return ResponseEntity.ok().body(modelMapper.map(tripRepository.save(trip), TripDto.class));


    }

    public ResponseEntity<List<TripDto>> createAll(List<TripDto> tripDtos) {
            List<Trip> trips = tripDtos.stream().map(tripDto -> modelMapper.map(tripDto, Trip.class)).toList();
            tripRepository.saveAll(trips);

            log.info("Seferler sisteme eklendi.");

            return ResponseEntity.ok().body(trips.stream().map(trip -> modelMapper.map(trip, TripDto.class)).toList());
    }

    public ResponseEntity<TripDto> updateTrip(Long id, TripDto tripDto) {


            Trip trip = tripRepository.findById(id).orElseThrow();

            trip.setDate(tripDto.getDate());
            trip.setPrice(tripDto.getPrice());
            trip.setStatus(tripDto.getStatus());


            log.info(trip.getTripCode() + " seferi güncellendi.");

            tripRepository.save(trip);

            return ResponseEntity.ok().body(modelMapper.map(trip, TripDto.class));

    }

    public ResponseEntity<String> deleteTrip(Long id) {


            tripRepository.deleteById(id);

            log.info(id + " id'li seyehat sistemden silindi.");

            return ResponseEntity.ok().body(id + " id'li seyehat silindi.");

    }



    public ResponseEntity<List<TripDto>> searchTrips(TripDto tripDto) {

        List<Trip> trips = tripRepository.findAllByVehicleTypeAndFromStationAndToStationAndDate(tripDto.getVehicleType(), tripDto.getFromStation(),
                tripDto.getToStation(), tripDto.getDate());

        return ResponseEntity.ok().body(trips.stream().map(trip -> modelMapper.map(trip, TripDto.class)).toList());
    }
}
