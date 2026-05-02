package com.sairapuentes.productos.service;

import com.sairapuentes.productos.dto.ProductoRequest;
import com.sairapuentes.productos.dto.ProductoResponse;


import java.util.List;

public interface IProductoServicio {

    List<ProductoResponse> findAll();
    List<ProductoResponse> findByIdCategoria(int idCategoria);
    ProductoResponse findById(int id);

    ProductoResponse save(ProductoRequest request);

    ProductoResponse update (int id, ProductoRequest request);
    void eliminar(int id);

    //List<VentaResponse> findByIDProductos(Integer ID_producto);

}
