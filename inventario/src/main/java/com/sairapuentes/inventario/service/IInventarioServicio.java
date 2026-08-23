package com.sairapuentes.inventario.service;

import com.sairapuentes.inventario.dto.InventarioRequest;
import com.sairapuentes.inventario.dto.InventarioResponse;
import com.sairapuentes.inventario.dto.InventarioRestarRequest;

import java.util.List;

public interface IInventarioServicio {
    List<InventarioResponse> findAll();
    List<InventarioResponse> findAllBySede(Integer idSede);
    InventarioResponse findById(int id);
    InventarioResponse save(InventarioRequest request);
    InventarioResponse update(int id, InventarioRequest request);
    void restarStock(InventarioRestarRequest request);
    InventarioResponse findByIdProductoSede(Integer idProducto, Integer idSede);
    void eliminar(int id);
}
