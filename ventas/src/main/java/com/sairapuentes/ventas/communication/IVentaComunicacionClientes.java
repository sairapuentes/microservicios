package com.sairapuentes.ventas.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientes")
public interface IVentaComunicacionClientes {
    @GetMapping("/api/clientes/{id}")
    ClienteResponse getClienteById(@PathVariable("id") int id);
}
