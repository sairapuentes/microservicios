package com.sairapuentes.ventas.service;

import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import java.util.List;
public interface IVentaServico {

    List<VentaResponse> findAll();
    List<VentaResponse> findAllBySede(Integer idSede);
    VentaResponse findById(int id, Integer idSede);
    VentaResponse save(VentaRequest request, Integer idSede);
    void eliminar(int id, Integer idSede);
    List<VentaResponse> findByIdProducto(Integer idProducto);
    List<VentaResponse> findByIdProductoAndIdSede(Integer idProducto, Integer idSede);
}
