package com.ticketco.ticketcopanel.Model;

import com.ticketco.ticketcopanel.Model.Enums.VehicleStatus;
import com.ticketco.ticketcopanel.Model.Enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

}
