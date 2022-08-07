package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.Gender;
import com.ticketco.ticketcomain.Model.Passenger;
import com.ticketco.ticketcomain.Model.Trip;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {

    private Long id;

    private Passenger passenger;

    private Integer amount;

    private Trip trip;


}
