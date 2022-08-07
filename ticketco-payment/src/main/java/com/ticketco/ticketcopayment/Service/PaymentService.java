package com.ticketco.ticketcopayment.Service;

import com.ticketco.ticketcopayment.Dto.PaymentDto;
import com.ticketco.ticketcopayment.Model.Payment;
import com.ticketco.ticketcopayment.Repository.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    final PaymentRepository paymentRepository;

    final ModelMapper modelMapper;

    public PaymentService(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }

    public PaymentDto createPayment(PaymentDto paymentDto) {
        Payment payment = modelMapper.map(paymentDto, Payment.class);

        paymentRepository.save(payment);

        return modelMapper.map(payment, PaymentDto.class);
    }
}
