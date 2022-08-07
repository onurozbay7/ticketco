package com.ticketco.ticketcopanel.Controller;

import com.ticketco.ticketcopanel.Dto.AdminDto;
import com.ticketco.ticketcopanel.Service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllUsers(){
        return adminService.getAllUsers();
    }

    @GetMapping("/me")
    public ResponseEntity<AdminDto> getUser(){
        return adminService.getUser();
    }


    @PostMapping("/register")
    public ResponseEntity<AdminDto> register(@RequestBody AdminDto request){
        return adminService.register(request);
    }

    @PutMapping("/update")
    public ResponseEntity<AdminDto> updateUser(@RequestBody AdminDto request){
        return ResponseEntity.ok().body(adminService.updateUser(request));
    }
}
