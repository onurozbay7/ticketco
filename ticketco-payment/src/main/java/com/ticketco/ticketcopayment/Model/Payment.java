package com.ticketco.ticketcopayment.Model;

import com.ticketco.ticketcopayment.Model.Enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@Document(collection = "Payment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    private String email;

    private PaymentType paymentType;

    private LocalDate date;

    private Integer amount;
}
