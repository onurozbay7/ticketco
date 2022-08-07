package com.ticketco.ticketcopanel.Service;

import com.ticketco.ticketcopanel.Dto.AdminDto;
import com.ticketco.ticketcopanel.Model.Admin;
import com.ticketco.ticketcopanel.Repository.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
public class AdminService implements UserDetailsService {

    final AdminRepository adminRepository;

    final BCryptPasswordEncoder bCryptPasswordEncoder;

    final ModelMapper modelMapper;

    public AdminService(AdminRepository adminRepository, BCryptPasswordEncoder bCryptPasswordEncoder, ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.modelMapper = modelMapper;
    }


    public ResponseEntity<AdminDto> register(AdminDto request) {
        Admin admin = modelMapper.map(request, Admin.class);
        admin.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        log.info(admin.getFullName() + " adlı kullanıcı sisteme kayıt oldu.");

        return ResponseEntity.ok().body(modelMapper.map(adminRepository.save(admin), AdminDto.class));
    }


    public ResponseEntity<List<AdminDto>> getAllUsers() {
        List<Admin> admins = adminRepository.findAll();
        log.info("Tüm kullanıcılar görüntülendi.");
        return ResponseEntity.ok().body(admins.stream().map(user -> modelMapper.map(user, AdminDto.class)).toList());
    }

    public ResponseEntity<AdminDto> getUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Admin user = adminRepository.findByEmail(email);
        log.info("Tüm kullanıcılar görüntülendi.");
        return ResponseEntity.ok().body(modelMapper.map(user,AdminDto.class));
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        return adminRepository.findByEmail(usernameOrEmail);
    }

    public AdminDto updateUser(AdminDto request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Admin foundAdmin = adminRepository.findByEmail(email);

        if (request.getFullName() != null) foundAdmin.setFullName(request.getFullName());
        if (request.getPassword() != null) foundAdmin.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

        return modelMapper.map(adminRepository.save(foundAdmin), AdminDto.class);
    }



}
