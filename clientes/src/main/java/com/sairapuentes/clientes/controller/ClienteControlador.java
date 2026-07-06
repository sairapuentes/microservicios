package com.sairapuentes.clientes.controller;

import com.sairapuentes.clientes.dto.ClienteRequest;
import com.sairapuentes.clientes.dto.ClienteResponse;
import com.sairapuentes.clientes.service.IClienteServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteControlador {
    @Autowired
    private IClienteServicio clienteServicio;

    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listar(){
        return ResponseEntity.ok(clienteServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<ClienteResponse> crear(@Valid @RequestBody ClienteRequest request){
        ClienteResponse response = clienteServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponse> findById(@PathVariable Integer id){
        ClienteResponse cliente = clienteServicio.findById(id);
        return ResponseEntity.ok(cliente);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody ClienteRequest request){
                 ClienteResponse response = clienteServicio.update(id, request);
                 return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        clienteServicio.eliminar(id);
        return ResponseEntity.ok("Cliente eliminado correctamente");
    }
}
