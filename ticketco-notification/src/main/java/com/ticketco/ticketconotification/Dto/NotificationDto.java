package com.ticketco.ticketconotification.Dto;

import com.ticketco.ticketconotification.Model.Enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {

    private String toSend;

    private String title;

    private String content;

    private NotificationType notificationType;
}
