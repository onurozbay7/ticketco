package com.ticketco.ticketcomain.Model;


import com.ticketco.ticketcomain.Model.Enums.VehicleStatus;
import com.ticketco.ticketcomain.Model.Enums.VehicleType;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tripCode;

    private String fromStation;

    private String toStation;

    private Integer price;

    @Enumerated(value = EnumType.STRING)
    private VehicleType vehicleType;

    private LocalDate date;

    private Integer capacity;

    @Enumerated(value = EnumType.STRING)
    private VehicleStatus status;



    public Trip(Long id, String tripCode, String fromStation, String toStation, Integer price, VehicleType vehicleType, LocalDate date, VehicleStatus status) {
        this.id = id;
        this.tripCode = tripCode;
        this.fromStation = fromStation;
        this.toStation = toStation;
        this.price = price;
        this.vehicleType = vehicleType;
        this.date = date;
        if(vehicleType == VehicleType.AIRPLANE) { this.capacity = 189;} else { this.capacity = 45;}

        this.status = status;
    }
}
