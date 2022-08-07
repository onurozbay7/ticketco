package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.UserType;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    private String fullName;

    private String email;

    private String password;

    private UserType userType;
}
