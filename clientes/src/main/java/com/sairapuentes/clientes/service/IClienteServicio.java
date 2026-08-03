package com.sairapuentes.clientes.service;

import com.sairapuentes.clientes.dto.ClienteRequest;
import com.sairapuentes.clientes.dto.ClienteResponse;

import java.util.List;

public interface IClienteServicio {
    List<ClienteResponse> findAll();
    ClienteResponse findById(long id);
    ClienteResponse save(ClienteRequest request);
    ClienteResponse update(long id, ClienteRequest request);
    void eliminar(long id);
}
