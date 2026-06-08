package com.sairapuentes.productos.controller;

import com.sairapuentes.productos.dto.CategoriaRequest;
import com.sairapuentes.productos.dto.CategoriaResponse;
import com.sairapuentes.productos.service.ICategoriaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaControlador {
    @Autowired
    private ICategoriaServicio categoriaServicio;

    @GetMapping
    public ResponseEntity<List<CategoriaResponse>> listar(){
        return ResponseEntity.ok(categoriaServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<CategoriaResponse> crear(@RequestBody CategoriaRequest request){
        CategoriaResponse response = categoriaServicio.save(request) ;
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponse> findById(@PathVariable Integer id){
        CategoriaResponse categoria = categoriaServicio.findById(id);
        return ResponseEntity.ok(categoria);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        categoriaServicio.eliminar(id);
        return ResponseEntity.ok("Categoria eliminada correctamente");
    }
}
