package com.ticketco.ticketcomain.Dto;

import com.ticketco.ticketcomain.Model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {

    private Long id;

    private String fullName;

    private String email;

    private String phone;


    private Gender gender;

    private Integer age;
}
