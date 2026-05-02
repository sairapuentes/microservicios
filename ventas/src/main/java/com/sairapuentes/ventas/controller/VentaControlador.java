package com.sairapuentes.ventas.controller;

import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import com.sairapuentes.ventas.service.IVentaServico;
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

    @PostMapping("/crear")
    public ResponseEntity<VentaResponse> crear(@RequestBody VentaRequest request){
        VentaResponse response = ventaServicio.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/lista")
    public ResponseEntity<List<VentaResponse>> findAll(){

        return ResponseEntity.ok(ventaServicio.findAll());
    }


    //comunicacion entre microservicios
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<List<VentaResponse>> findByIdProducto(@PathVariable Integer idProducto){
        List<VentaResponse> ventaResponseList = ventaServicio.findByIdProducto(idProducto);
        return ResponseEntity.ok(ventaResponseList);
    }
}
