package com.shop.tienda_virtual.controller;

import com.shop.tienda_virtual.dto.AuthResponseDTO;
import com.shop.tienda_virtual.dto.LoginRequestDTO;
import com.shop.tienda_virtual.dto.RegisterRequestDTO;
import com.shop.tienda_virtual.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping ("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginRequestDTO request) {

        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping ("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody RegisterRequestDTO request){


        return ResponseEntity.ok(authService.register(request));
    }
}
