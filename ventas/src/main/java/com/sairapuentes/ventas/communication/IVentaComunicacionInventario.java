package com.sairapuentes.ventas.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "inventario")
public interface IVentaComunicacionInventario {
    @GetMapping("/api/inventario/producto/{idProducto}/sede/{idSede}")
    InventarioResponse getInventario(@PathVariable("idProducto") Integer idProducto,
                                     @PathVariable("idSede") Integer idSede);
    @PutMapping("/api/inventario/restarStock")
    void restarStock(@RequestBody InventarioRestarRequest request);
}
