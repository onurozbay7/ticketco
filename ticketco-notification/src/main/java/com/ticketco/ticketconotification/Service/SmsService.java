package com.ticketco.ticketconotification.Service;

import com.ticketco.ticketconotification.Dto.NotificationDto;
import com.ticketco.ticketconotification.Model.Sms;
import com.ticketco.ticketconotification.Repository.SmsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SmsService implements INotificationService{

    final SmsRepository smsRepository;

    public SmsService(SmsRepository smsRepository) {
        this.smsRepository = smsRepository;
    }


    @Override
    public void sender(NotificationDto notificationDto) {

        Sms sms = new Sms(notificationDto.getToSend(), notificationDto.getContent());

        smsRepository.save(sms);

    }
}
