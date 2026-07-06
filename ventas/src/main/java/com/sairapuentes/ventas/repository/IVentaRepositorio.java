package com.sairapuentes.ventas.repository;

import com.sairapuentes.ventas.entity.Venta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IVentaRepositorio extends CrudRepository<Venta, Integer> {

    //Consulta para obtener ventas por producto
    List<Venta> findByIdProducto(Integer idProducto);
}
