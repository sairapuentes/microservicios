package com.sairapuentes.ventas.repository;

import com.sairapuentes.ventas.entity.Venta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IVentaRepositorio extends CrudRepository<Venta, Integer> {

    //Consulta para obtener ventas por producto
    List<Venta> findByIdProducto(Integer idProducto);
}
