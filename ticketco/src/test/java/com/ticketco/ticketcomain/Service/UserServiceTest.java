package com.ticketco.ticketcomain.Service;

import com.ticketco.ticketcomain.Dto.NotificationDto;
import com.ticketco.ticketcomain.Dto.SingUpDto;
import com.ticketco.ticketcomain.Dto.UserDto;
import com.ticketco.ticketcomain.Exception.MailAlreadyInUseException;
import com.ticketco.ticketcomain.Exception.UserNotFoundException;
import com.ticketco.ticketcomain.Model.Enums.UserType;
import com.ticketco.ticketcomain.Model.User;
import com.ticketco.ticketcomain.Repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private AmqpTemplate amqpTemplate;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Mock
    private ModelMapper modelMapper;

    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("it should create user")
    void register_if_email_is_not_used() {
        SingUpDto request = new SingUpDto(1L,"Cem dirman", "cmdrmn@user.com", "123456", UserType.CORPORATE);

        when(userRepository.save(any(User.class))).thenReturn(any(User.class));
        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
        var result = userService.register(request);


        verify(userRepository, times(1)).findByEmail(request.getEmail());
        verify(amqpTemplate, times(1)).convertAndSend(any(NotificationDto.class));
        verify(userRepository, times(1)).save(modelMapper.map(result, User.class));


        assertEquals(request.getEmail() + " mailli kullanıcı başarı ile kayıt edildi","");


    }


    @Test
    void cant_register_because_email_is_used() {
        SingUpDto request = new SingUpDto(1L,"Onur Özbay", "user@user.com", "123456", UserType.CORPORATE);

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mock(User.class))).thenThrow(MailAlreadyInUseException.class);

        verify(amqpTemplate,never()).convertAndSend(anyString(), anyString(), any(NotificationDto.class));
        verify(userRepository, never()).save(any(User.class));

        assertThrows(MailAlreadyInUseException.class, () -> userService.register(request));


    }


    @Test
    void cant_get_user_with_email() {
        when(userRepository.findByEmail(anyString())).thenThrow(UserNotFoundException.class);
        assertThrows(UserNotFoundException.class, () -> userService.getUser());

    }

    @Test
    void get_user_succeeded() throws UserNotFoundException {
        var email = "ozzie@gmail.com";
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mock(User.class)));
        var user = userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        verify(userRepository,times(1)).findByEmail(email);
        assertEquals(ResponseEntity.ok().body(modelMapper.map(user, UserDto.class)), userService.getUser());

    }
}
