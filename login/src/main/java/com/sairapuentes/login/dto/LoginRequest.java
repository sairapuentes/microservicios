package com.sairapuentes.login.dto;

public class LoginRequest {
    private String correo;
    private String password;

    public LoginRequest(LoginRequest request) {
    }

    public LoginRequest(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
}
