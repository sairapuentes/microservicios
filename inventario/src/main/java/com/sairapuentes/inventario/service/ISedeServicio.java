package com.sairapuentes.inventario.service;

import com.sairapuentes.inventario.dto.SedeRequest;
import com.sairapuentes.inventario.dto.SedeResponse;

import java.util.List;

public interface ISedeServicio {
    List<SedeResponse> findAll();
    SedeResponse findById(int id);
    SedeResponse save(SedeRequest request);
    SedeResponse update(int id, SedeRequest request);
    void eliminar(int id);
}
