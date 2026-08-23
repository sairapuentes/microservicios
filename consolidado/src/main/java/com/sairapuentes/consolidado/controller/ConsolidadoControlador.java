package com.sairapuentes.consolidado.controller;

import com.sairapuentes.consolidado.dto.ConsolidadoProductoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoSedeResponse;
import com.sairapuentes.consolidado.service.IConsolidadoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consolidado")
public class ConsolidadoControlador {

    @Autowired
    private IConsolidadoServicio consolidadoServicio;

    @GetMapping
    public ResponseEntity<ConsolidadoResponse> obtenerConsolidado(@RequestHeader("X-Usuario-Sede") Integer idSede, @RequestHeader("X-Usuario-Rol") String rol){
        return ResponseEntity.ok(consolidadoServicio.obtenerConsolidado(idSede, rol));
    }
    @GetMapping("/sedes")
    public ResponseEntity<List<ConsolidadoSedeResponse>> obtenerPorSede(@RequestHeader("X-Usuario-Sede") Integer idSede, @RequestHeader("X-Usuario-Rol") String rol){
        return ResponseEntity.ok(consolidadoServicio.obtenerConsolidadoPorSede(idSede, rol));
    }
    @GetMapping("/productos")
    public ResponseEntity<List<ConsolidadoProductoResponse>> obtenerPorProducto(@RequestHeader("X-Usuario-Sede") Integer idSede, @RequestHeader("X-Usuario-Rol") String rol){
        return ResponseEntity.ok(consolidadoServicio.obtenerConsolidadoPorProducto(idSede, rol));
    }
}
