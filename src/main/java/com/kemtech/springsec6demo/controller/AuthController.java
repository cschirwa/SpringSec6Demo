package com.kemtech.springsec6demo.controller;

import com.kemtech.springsec6demo.entity.AuthRequest;
import com.kemtech.springsec6demo.entity.StudentRequest;
import com.kemtech.springsec6demo.security.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        daoAuthenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDetailsService.loadUserByUsername(authRequest.getUsername()), authRequest.getPassword())
            );
        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
        return ResponseEntity.ok(userDetails);
    }
}
