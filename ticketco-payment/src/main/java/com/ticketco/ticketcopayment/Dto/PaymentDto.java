package com.ticketco.ticketcopayment.Dto;

import com.ticketco.ticketcopayment.Model.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
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
