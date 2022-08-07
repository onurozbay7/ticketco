package com.ticketco.ticketconotification.Listener;

import com.ticketco.ticketconotification.Dto.NotificationDto;
import com.ticketco.ticketconotification.Model.Enums.NotificationType;
import com.ticketco.ticketconotification.Service.EmailService;
import com.ticketco.ticketconotification.Service.SmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationListener {

    final EmailService emailService;

    final SmsService smsService;

    public NotificationListener(EmailService emailService, SmsService smsService) {
        this.emailService = emailService;
        this.smsService = smsService;
    }

    @RabbitListener(queues = "ticketco.email")
    public void emailListener(NotificationDto notificationDto) {
        log.info("Notification Type: {}", notificationDto.getNotificationType());

        if(notificationDto.getNotificationType() == NotificationType.EMAIL) {
            emailService.sender(notificationDto);
        }
        else if(notificationDto.getNotificationType() == NotificationType.SMS) {
            smsService.sender(notificationDto);
        }

        else{
            log.info("Yanlış veri gönderildi");
        }
        // TO DO mail at
    }
}
