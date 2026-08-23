package com.sairapuentes.consolidado.communication;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.List;
@FeignClient(name="ventas")
public interface IConsolidadoComunicacionVentas {
    @GetMapping("/api/ventas")
    List<VentasResponse> listarVentas(@RequestHeader("X-Usuario-Sede") Integer idSede);

    @GetMapping("/api/ventas/consolidado")
    List<VentasResponse> listarTodasLasVentas();
}
