package com.sairapuentes.ventas.controller;

import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import com.sairapuentes.ventas.service.IVentaServico;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ventas")
public class VentaControlador {

    @Autowired
    private IVentaServico ventaServicio;

    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar(){

        return ResponseEntity.ok(ventaServicio.findAll());
    }
    @PostMapping("/crear")
    public ResponseEntity<VentaResponse> crear(@Valid @RequestBody VentaRequest request){
        VentaResponse response = ventaServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> findById(@PathVariable Integer id){
        VentaResponse response = ventaServicio.findById(id);
        return ResponseEntity.ok(response);
    }


    //comunicacion entre microservicios
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<VentaResponse>> findByIdProducto(@PathVariable Integer idProducto){
        List<VentaResponse> ventaResponseList = ventaServicio.findByIdProducto(idProducto);
        return ResponseEntity.ok(ventaResponseList);
    }
}
