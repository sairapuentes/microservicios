package com.sairapuentes.productos.service;

import com.sairapuentes.productos.dto.CategoriaRequest;
import com.sairapuentes.productos.dto.CategoriaResponse;

import java.util.List;


public interface ICategoriaServicio {
    List<CategoriaResponse> findAll();
    CategoriaResponse findById(int id);
    CategoriaResponse save(CategoriaRequest request);
    CategoriaResponse update(int id, CategoriaRequest request);
    void eliminar(int id);
}
