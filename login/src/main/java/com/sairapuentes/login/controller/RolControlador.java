package com.sairapuentes.login.controller;

import com.sairapuentes.login.dto.RolRequest;
import com.sairapuentes.login.dto.RolResponse;
import com.sairapuentes.login.service.IRolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rol")
public class RolControlador {
    @Autowired
    private IRolServicio rolServicio;

    @GetMapping
    public ResponseEntity<List<RolResponse>> listar(){
        return ResponseEntity.ok(rolServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<RolResponse> crear(@RequestBody RolRequest request){
        RolResponse response = rolServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<RolResponse> findById(@PathVariable Integer id){
        RolResponse rol = rolServicio.findById(id);
        return ResponseEntity.ok(rol);
    }
    @PutMapping("/{id}")
    public ResponseEntity<RolResponse> actualizar(@PathVariable Integer id,@RequestBody RolRequest request){
        RolResponse reponse = rolServicio.update(id, request);
        return ResponseEntity.ok(reponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        rolServicio.eliminar(id);
        return ResponseEntity.ok("Rol eliminado correctamente");
    }
}
