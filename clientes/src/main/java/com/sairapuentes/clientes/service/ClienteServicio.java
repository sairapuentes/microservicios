package com.sairapuentes.clientes.service;

import com.sairapuentes.clientes.dto.ClienteRequest;
import com.sairapuentes.clientes.dto.ClienteResponse;
import com.sairapuentes.clientes.entity.Clientes;
import com.sairapuentes.clientes.repository.IClienteRepositorio;
import org.springframework.stereotype.Service;

@Service
public class ClienteServicio implements IClienteServicio{

    private final IClienteRepositorio clienteRepositorio;

    public ClienteServicio(IClienteRepositorio clienteRepositorio){
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public ClienteResponse findById(int id) {
        Clientes cliente = clienteRepositorio.findById(id)
                .orElseThrow(()->new RuntimeException("Cliente no encontrado"));
        return mapToResponse(cliente);
    }

    @Override
    public ClienteResponse save(ClienteRequest request) {
        Clientes cliente = new Clientes();
        cliente.setIdCliente(request.getIdCliente());
        cliente.setNombreCliente(request.getNombreCliente());
        cliente.setTelefonoCliente(request.getTelefonoCliente());
        cliente.setEmailCliente(request.getEmailCliente());

        Clientes guardar = clienteRepositorio.save(cliente);
        return mapToResponse(guardar);
    }

    @Override
    public ClienteResponse update(int id, ClienteRequest request) {
        Clientes cliente = clienteRepositorio.findById(id)
                .orElseThrow(()->new RuntimeException("El cliente no fue encontrado"));
        cliente.setNombreCliente(request.getNombreCliente());
        cliente.setTelefonoCliente(request.getTelefonoCliente());
        cliente.setEmailCliente(request.getEmailCliente());

        Clientes actualizar = clienteRepositorio.save(cliente);
        return mapToResponse(actualizar);
    }

    @Override
    public void eliminar(int id) {
        clienteRepositorio.deleteById(id);
    }

    private ClienteResponse mapToResponse(Clientes cliente) {
        return new ClienteResponse(
                cliente.getIdCliente(),
                cliente.getNombreCliente(),
                cliente.getTelefonoCliente(),
                cliente.getEmailCliente()
        );
    }
}
