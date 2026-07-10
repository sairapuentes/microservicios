package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.UsuarioRequest;
import com.sairapuentes.login.dto.UsuarioResponse;

import java.util.List;

public interface IUsuarioServicio {
    List<UsuarioResponse> findAll();
    UsuarioResponse findById(int id);
    UsuarioResponse save(UsuarioRequest request);
    UsuarioResponse update(int id, UsuarioRequest request);
    void eliminar(int id);

}
