package com.sairapuentes.login.controller;

import com.sairapuentes.login.dto.LoginRequest;
import com.sairapuentes.login.dto.LoginResponse;
import com.sairapuentes.login.service.IAuthServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
    private final IAuthServicio authServicio;

    public AuthControlador(IAuthServicio authServicio){
        this.authServicio = authServicio;
    }
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity.ok(authServicio.login(request));
    }
}
