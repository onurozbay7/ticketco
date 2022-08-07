package com.ticketco.ticketcopanel.Dto;

import com.ticketco.ticketcopanel.Model.Enums.VehicleStatus;
import com.ticketco.ticketcopanel.Model.Enums.VehicleType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripDto {

    private Long id;

    private String tripCode;

    private String fromStation;

    private String toStation;

    private Integer price;

    private VehicleType vehicleType;

    private LocalDate date;

    private Integer capacity;

    private VehicleStatus status;
}
