package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.LoginRequest;
import com.sairapuentes.login.dto.LoginResponse;

public interface IAuthServicio {
    LoginResponse login(LoginRequest request);
}
