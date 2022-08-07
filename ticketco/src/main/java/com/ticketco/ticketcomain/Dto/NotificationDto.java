package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.NotificationType;
import lombok.*;

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
