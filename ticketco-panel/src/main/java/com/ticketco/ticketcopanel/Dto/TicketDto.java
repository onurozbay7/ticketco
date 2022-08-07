package com.ticketco.ticketcopanel.Dto;

import com.ticketco.ticketcopanel.Model.Trip;
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
