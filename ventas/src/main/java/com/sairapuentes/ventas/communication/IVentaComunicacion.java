package com.sairapuentes.ventas.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "productos")
public interface IVentaComunicacion {
//    @GetMapping("/api/productos/{id}")
//    ProductoResponse getProductoById(@PathVariable("id") int id);
}
