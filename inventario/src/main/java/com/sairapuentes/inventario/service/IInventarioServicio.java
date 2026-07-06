package com.sairapuentes.inventario.service;

import com.sairapuentes.inventario.dto.InventarioRequest;
import com.sairapuentes.inventario.dto.InventarioResponse;
import com.sairapuentes.inventario.dto.InventarioRestarRequest;

import java.util.List;

public interface IInventarioServicio {
    List<InventarioResponse> findAll();
    InventarioResponse findById(int id);
    InventarioResponse save(InventarioRequest request);
    InventarioResponse update(int id, InventarioRequest request);
    void restarStock(InventarioRestarRequest request);
    InventarioResponse findByIdProducto(Integer idProducto);
    void eliminar(int id);
}
