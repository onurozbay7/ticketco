package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.VehicleStatus;
import com.ticketco.ticketcomain.Model.Enums.VehicleType;
import lombok.*;


import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private Long id;

    private String tripCode;

    private String from;

    private String to;

    private Integer price;

    private VehicleType vehicleType;

    private LocalDate date;

    private Integer capacity;

    private VehicleStatus status;
}
