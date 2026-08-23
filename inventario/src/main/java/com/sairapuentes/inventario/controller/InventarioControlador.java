package com.sairapuentes.inventario.controller;

import com.sairapuentes.inventario.dto.InventarioRequest;
import com.sairapuentes.inventario.dto.InventarioResponse;
import com.sairapuentes.inventario.dto.InventarioRestarRequest;
import com.sairapuentes.inventario.service.IInventarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
public class InventarioControlador {
    @Autowired
    private IInventarioServicio inventarioServicio;

    @GetMapping
    public ResponseEntity<List<InventarioResponse>> listar(@RequestHeader("X-Usuario-Sede") Integer idSede,
                                                           @RequestHeader("X-Usuario-Rol") String rol){
        System.out.println("INVENTARIO: Sede recibida: " + idSede);
        System.out.println("INVENTARIO: Rol recibido: " + rol);

        if (rol.equals("ADMIN")) {
            return ResponseEntity.ok(inventarioServicio.findAll());
        }
        return ResponseEntity.ok(inventarioServicio.findAllBySede(idSede));
    }

    @PostMapping("/crear")
    public ResponseEntity<InventarioResponse> crear(
            @Valid @RequestBody InventarioRequest request,
            @RequestHeader("X-Usuario-Sede") Integer idSede,
            @RequestHeader("X-Usuario-Rol") String rol) {

        if (rol.equals("ADMIN")) {
            InventarioResponse response = inventarioServicio.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        if (rol.equals("BODEGA")) {
             request.setIdSede(idSede);

            InventarioResponse response = inventarioServicio.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioResponse> findById(
            @PathVariable Integer id,
            @RequestHeader("X-Usuario-Sede") Integer idSede,
            @RequestHeader("X-Usuario-Rol") String rol) {

        InventarioResponse response = inventarioServicio.findById(id);

        if (rol.equals("ADMIN")) {
            return ResponseEntity.ok(response);
        }

        if (response.getIdSede() != idSede) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(response);
    }

    //Comunicacion entre microservicios
    @GetMapping("/producto/{idProducto}/sede/{idSede}")
    public ResponseEntity<InventarioResponse> buscarProducto(@PathVariable Integer idProducto, @PathVariable Integer idSede){
        return ResponseEntity.ok(inventarioServicio.findByIdProductoSede(idProducto, idSede));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioResponse> actualizar(
            @PathVariable Integer id,
            @RequestBody InventarioRequest request,
            @RequestHeader("X-Usuario-Sede") Integer idSede,
            @RequestHeader("X-Usuario-Rol") String rol) {

        InventarioResponse actual = inventarioServicio.findById(id);

        if (rol.equals("ADMIN")) {
            InventarioResponse response =
                    inventarioServicio.update(id, request);

            return ResponseEntity.ok(response);
        }

        if (rol.equals("BODEGA")) {

            if (actual.getIdSede() != idSede) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            if (request.getIdSede() != idSede) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            InventarioResponse response = inventarioServicio.update(id, request);

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/restarStock")
    public ResponseEntity<String> restarStock(
            @RequestBody InventarioRestarRequest request,
            @RequestHeader("X-Usuario-Sede") Integer idSede,
            @RequestHeader("X-Usuario-Rol") String rol) {

        if (rol.equals("ADMIN")) {
            inventarioServicio.restarStock(request);
            return ResponseEntity.ok("Stock actualizado correctamente");
        }

        if (rol.equals("BODEGA") &&
                idSede.equals(request.getIdSede())) {

            inventarioServicio.restarStock(request);
            return ResponseEntity.ok("Stock actualizado correctamente");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("/interno/restarStock")
    public ResponseEntity<String> restarStockVenta(@RequestBody InventarioRestarRequest request){
        inventarioServicio.restarStock(request);
        return ResponseEntity.ok("Stock actualizado correctamente");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id,
            @RequestHeader("X-Usuario-Sede") Integer idSede,
            @RequestHeader("X-Usuario-Rol") String rol) {

        InventarioResponse actual = inventarioServicio.findById(id);

        if (rol.equals("ADMIN")) {
            inventarioServicio.eliminar(id);
            return ResponseEntity.ok("Inventario eliminado correctamente");
        }

        if (rol.equals("BODEGA") && actual.getIdSede() != (idSede)) {
            inventarioServicio.eliminar(id);
            return ResponseEntity.ok("Inventario eliminado correctamente");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
