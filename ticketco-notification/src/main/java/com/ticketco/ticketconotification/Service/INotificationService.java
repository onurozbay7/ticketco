package com.ticketco.ticketconotification.Service;

import com.ticketco.ticketconotification.Dto.NotificationDto;

public interface INotificationService {

    void sender(NotificationDto notificationDto);
}
