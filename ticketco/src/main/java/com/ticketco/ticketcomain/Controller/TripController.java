package com.ticketco.ticketcomain.Controller;

import com.ticketco.ticketcomain.Dto.TripDto;
import com.ticketco.ticketcomain.Service.TripService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("trips")
public class TripController {

    final TripService tripService;


    public TripController(TripService tripService) {
        this.tripService = tripService;
    }


    @GetMapping
    public ResponseEntity<ResponseEntity<List<TripDto>>> getAllTrips(){
        return ResponseEntity.ok().body(tripService.getAllTrips());
    }


    @GetMapping("/search")
    public ResponseEntity<ResponseEntity<List<TripDto>>> getAllTripsByDate(@RequestBody TripDto tripDto){
        return ResponseEntity.ok().body(tripService.searchTrips(tripDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseEntity<TripDto>> getTrip(@PathVariable Long id){
        return ResponseEntity.ok().body(tripService.getTrip(id));
    }
}
