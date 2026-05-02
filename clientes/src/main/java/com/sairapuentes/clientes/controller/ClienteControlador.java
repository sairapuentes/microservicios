package com.sairapuentes.clientes.controller;

import com.sairapuentes.clientes.dto.ClienteRequest;
import com.sairapuentes.clientes.dto.ClienteResponse;
import com.sairapuentes.clientes.service.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {
    @Autowired
    private IClienteServicio clienteServicio;

    @PostMapping("/crear")
    public ResponseEntity<ClienteResponse> crear(@RequestBody ClienteRequest request){
        ClienteResponse response = clienteServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Integer id){
        return ResponseEntity.ok(clienteServicio.findById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody ClienteRequest request){
                 ClienteResponse response = clienteServicio.update(id, request);
                 return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteResponse> eliminar(@PathVariable Integer id){
        return ResponseEntity.noContent().build();
    }
}
