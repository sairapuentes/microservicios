package com.sairapuentes.productos.controller;

import com.sairapuentes.productos.dto.ProductoRequest;
import com.sairapuentes.productos.dto.ProductoResponse;
import com.sairapuentes.productos.service.IProductoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private IProductoServicio productoServicio;

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> listar(){

        return ResponseEntity.ok(productoServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<ProductoResponse> crear(@Valid @RequestBody ProductoRequest request){
        ProductoResponse response = productoServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponse>> findByCategoria(@PathVariable Integer idCategoria){
        List<ProductoResponse> productoCategoriaList = productoServicio.findByIdCategoria(idCategoria);
        return ResponseEntity.ok(productoCategoriaList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> findById(@PathVariable Integer id){
        ProductoResponse response = productoServicio.findById(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody ProductoRequest request){
                ProductoResponse response = productoServicio.update(id, request);
                return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id){
        productoServicio.eliminar(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }

}
