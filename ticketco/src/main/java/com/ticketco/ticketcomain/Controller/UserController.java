package com.ticketco.ticketcomain.Controller;

import com.ticketco.ticketcomain.Dto.SingUpDto;
import com.ticketco.ticketcomain.Dto.UserDto;
import com.ticketco.ticketcomain.Exception.UserNotFoundException;
import com.ticketco.ticketcomain.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;



    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.getUser());
    }


    @PostMapping("/register")
    public ResponseEntity<SingUpDto> register(@RequestBody SingUpDto request){
        return ResponseEntity.ok().body(userService.register(request));
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto request) throws UserNotFoundException {
        return ResponseEntity.ok().body(userService.updateUser(request));
    }
}
