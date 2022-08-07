package com.ticketco.ticketcopanel.Controller;


import com.ticketco.ticketcopanel.Dto.TripDto;
import com.ticketco.ticketcopanel.Service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/trips")
public class TripController {

    final TripService tripService;


    @GetMapping
    public ResponseEntity<List<TripDto>> getAllTrips(){
        return tripService.getAllTrips();
    }


    @GetMapping("/search")
    public ResponseEntity<List<TripDto>> searchTrips(@RequestBody TripDto tripDto){
        return tripService.searchTrips(tripDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TripDto> getTrip(@PathVariable Long id){
        return tripService.getTrip(id);
    }

    @PostMapping
    public ResponseEntity<TripDto> createTrip(@RequestBody TripDto tripDto) {
        return tripService.createTrip(tripDto);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<TripDto>> createAllTrips(@RequestBody List<TripDto> tripDtos) {
        return tripService.createAll(tripDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TripDto> updateTrip(@PathVariable Long id, @RequestBody TripDto tripDto) {
        return tripService.updateTrip(id, tripDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTrip (@PathVariable Long id) {
        return tripService.deleteTrip(id);
    }


}
