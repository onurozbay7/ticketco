package com.ticketco.ticketconotification.Service;

import com.ticketco.ticketconotification.Dto.NotificationDto;
import com.ticketco.ticketconotification.Model.Email;
import com.ticketco.ticketconotification.Repository.EmailRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService implements INotificationService {

    final EmailRepository emailRepository;

    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }


    @Override
    public void sender(NotificationDto notificationDto) {

       Email email = new Email(notificationDto.getToSend(), notificationDto.getTitle(), notificationDto.getContent());

       emailRepository.save(email);

    }
}
