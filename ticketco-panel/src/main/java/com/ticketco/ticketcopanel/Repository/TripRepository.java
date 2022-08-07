package com.ticketco.ticketcopanel.Repository;


import com.ticketco.ticketcopanel.Model.Enums.VehicleStatus;
import com.ticketco.ticketcopanel.Model.Enums.VehicleType;
import com.ticketco.ticketcopanel.Model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findAllByDate(LocalDate date);

    List<Trip> findAllByStatus(VehicleStatus status);

    List<Trip> findAllByVehicleTypeAndFromStationAndToStationAndDate(VehicleType vehicleType, String from, String to, LocalDate localDate);

    List<Trip> findAllByFromStationAndToStation(String from, String to);

    List<Trip> findAllByVehicleType(VehicleType vehicleType);
}
