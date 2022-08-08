package com.ticketco.ticketcomain.Model;

import com.ticketco.ticketcomain.Model.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    private String email;

    private String phone;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Integer age;

    @OneToOne(mappedBy = "passenger")
    private Ticket ticket;
}
