package com.ticketco.ticketcomain.Client;

import com.ticketco.ticketcomain.Dto.PaymentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "paymentClient", url = "${payment.url}")
public interface PaymentClient {

    @PostMapping
    PaymentDto createPayment(@RequestBody PaymentDto paymentDto);
}
