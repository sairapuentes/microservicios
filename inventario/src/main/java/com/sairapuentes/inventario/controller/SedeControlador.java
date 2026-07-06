package com.sairapuentes.inventario.controller;

import com.sairapuentes.inventario.dto.SedeRequest;
import com.sairapuentes.inventario.dto.SedeResponse;
import com.sairapuentes.inventario.service.ISedeServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sede")
public class SedeControlador {
    @Autowired
    private ISedeServicio sedeServicio;

    @GetMapping
    public ResponseEntity<List<SedeResponse>> listar(){
        return ResponseEntity.ok(sedeServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<SedeResponse> crear(@Valid @RequestBody SedeRequest request){
        SedeResponse response = sedeServicio.save(request) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SedeResponse> findById(@PathVariable Integer id){
        SedeResponse sede = sedeServicio.findById(id);
        return ResponseEntity.ok(sede);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SedeResponse> actualizar(@PathVariable Integer id, @RequestBody SedeRequest request){
        SedeResponse response = sedeServicio.update(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        sedeServicio.eliminar(id);
        return ResponseEntity.ok("Sede eliminada correctamente");
    }
}
