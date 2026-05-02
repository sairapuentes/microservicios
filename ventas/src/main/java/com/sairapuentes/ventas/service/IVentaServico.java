package com.sairapuentes.ventas.service;

import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import java.util.List;
public interface IVentaServico {

    List<VentaResponse> findAll();
    VentaResponse findById(int id);
    VentaResponse save(VentaRequest request);
    void eliminar(int id);
    List<VentaResponse> findByIdProducto(Integer idProducto);
}
