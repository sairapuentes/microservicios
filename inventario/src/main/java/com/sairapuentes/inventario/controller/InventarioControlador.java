package com.sairapuentes.inventario.controller;

import com.sairapuentes.inventario.dto.InventarioRequest;
import com.sairapuentes.inventario.dto.InventarioResponse;
import com.sairapuentes.inventario.dto.InventarioRestarRequest;
import com.sairapuentes.inventario.service.IInventarioServicio;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioControlador {
    @Autowired
    private IInventarioServicio inventarioServicio;

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> listar(){
        return ResponseEntity.ok(inventarioServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<InventarioResponse> crear(@Valid @RequestBody InventarioRequest request){
        InventarioResponse response = inventarioServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> findById(@PathVariable Integer id){
        InventarioResponse response = inventarioServicio.findById(id);
        return ResponseEntity.ok(response);
    }

    //Comunicacion entre microservicios
    @GetMapping("/producto/{idProducto}/sede/{idSede}")
    public ResponseEntity<InventarioResponse> buscarProducto(@PathVariable Integer idProducto, @PathVariable Integer idSede){
        return ResponseEntity.ok(inventarioServicio.findByIdProductoSede(idProducto, idSede));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizar(@PathVariable Integer id, @RequestBody InventarioRequest request){
        InventarioResponse response = inventarioServicio.update(id, request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/restarStock")
    public ResponseEntity<String> restarStock(@RequestBody InventarioRestarRequest request){
        inventarioServicio.restarStock(request);
        return ResponseEntity.ok("Stock actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        inventarioServicio.eliminar(id);
        return ResponseEntity.ok("Inventario eliminado correctamente");
    }
}
