package com.sairapuentes.login.controller;

import com.sairapuentes.login.dto.UsuarioRequest;
import com.sairapuentes.login.dto.UsuarioResponse;
import com.sairapuentes.login.service.IUsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioControlador {
    @Autowired
    private IUsuarioServicio usuarioServicio;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar(){
        return ResponseEntity.ok(usuarioServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<UsuarioResponse> crear(@RequestBody UsuarioRequest request){
        UsuarioResponse response = usuarioServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> findById(@PathVariable Integer id){
        UsuarioResponse response = usuarioServicio.findById(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(@PathVariable Integer id, @RequestBody UsuarioRequest request){
        UsuarioResponse response =usuarioServicio.update(id, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        usuarioServicio.eliminar(id);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }
}
