package com.ticketco.ticketcomain.Service;

import com.ticketco.ticketcomain.Client.TripClient;
import com.ticketco.ticketcomain.Dto.TripDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TripService {

    final TripClient tripClient;

    public TripService(TripClient tripClient) {
        this.tripClient = tripClient;
    }

    public ResponseEntity<List<TripDto>> getAllTrips() {

        return tripClient.getAllTrips();
    }

    public ResponseEntity<List<TripDto>> searchTrips(TripDto tripDto) {
        return tripClient.searchTrips(tripDto);
    }

    public ResponseEntity<TripDto> getTrip(Long id) {
        return tripClient.getTrip(id);
    }
}
