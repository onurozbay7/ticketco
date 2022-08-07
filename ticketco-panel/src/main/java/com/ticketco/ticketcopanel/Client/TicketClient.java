package com.ticketco.ticketcopanel.Client;

import com.ticketco.ticketcopanel.Dto.TicketDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "ticketClient", url = "${ticket.url}")
public interface TicketClient {

    @GetMapping("/totalAmount")
    ResponseEntity<Double> getTotalAmount();


    @GetMapping("/tickets")
    ResponseEntity<List<TicketDto>> getTickets();


}
