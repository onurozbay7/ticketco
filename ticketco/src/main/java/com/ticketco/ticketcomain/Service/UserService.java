package com.ticketco.ticketcomain.Service;

import com.ticketco.ticketcomain.Dto.NotificationDto;
import com.ticketco.ticketcomain.Dto.SingUpDto;
import com.ticketco.ticketcomain.Dto.UserDto;
import com.ticketco.ticketcomain.Model.Enums.NotificationType;
import com.ticketco.ticketcomain.Model.User;
import com.ticketco.ticketcomain.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    final UserRepository userRepository;

    final AmqpTemplate amqpTemplate;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final ModelMapper modelMapper;




    public SingUpDto register(SingUpDto request) {
        User user = modelMapper.map(request, User.class);
        user.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        amqpTemplate.convertAndSend(new NotificationDto(user.getEmail(), "Hoşgeldin " + user.getFullName(),
                "TicketCO sistemine kayıt olma işlemi başarıyla gerçekleşti.", NotificationType.EMAIL));

        log.info(user.getFullName() + " adlı kullanıcı sisteme kayıt oldu.");

        return modelMapper.map(userRepository.save(user), SingUpDto.class);
    }


    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Tüm kullanıcılar görüntülendi.");
        return users.stream().map(user -> modelMapper.map(user, UserDto.class)).toList();    }

    public UserDto getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email);
        log.info("Tüm kullanıcılar görüntülendi.");
        return modelMapper.map(user,UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return userRepository.findByEmail(usernameOrEmail);
    }

    public UserDto updateUser(com.ticketco.ticketcomain.Dto.UserDto request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User foundUser = userRepository.findByEmail(email);

        if (request.getFullName() != null) foundUser.setFullName(request.getFullName());
        if (request.getPassword() != null) foundUser.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        if (request.getUserType() != null) foundUser.setUserType(request.getUserType());

        return modelMapper.map(userRepository.save(foundUser), UserDto.class);
    }
}
