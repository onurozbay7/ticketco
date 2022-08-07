package com.ticketco.ticketcomain.Client;

import com.ticketco.ticketcomain.Dto.TripDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "tripClient", url = "${trip.url}")
public interface TripClient {

    @GetMapping
    ResponseEntity<List<TripDto>> getAllTrips();


    @GetMapping("/search")
    ResponseEntity<List<TripDto>> searchTrips(@RequestBody TripDto tripDto);

    @GetMapping("/{id}")
    ResponseEntity<TripDto> getTrip(@PathVariable Long id);

}
