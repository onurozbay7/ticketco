package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.PaymentType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {

    private String email;

    private PaymentType paymentType;

    private LocalDate date;

    private Integer amount;
}
