package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.RolRequest;
import com.sairapuentes.login.dto.RolResponse;

import java.util.List;

public interface IRolServicio {
    List<RolResponse> findAll();
    RolResponse findById(int id);
    RolResponse save(RolRequest request);
    RolResponse update(int id,RolRequest request);
    void eliminar(int id);
}
