package com.sairapuentes.productos.repository;

import com.sairapuentes.productos.entity.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

@Repository
public interface IProductoRepositorio extends JpaRepository<Producto, Integer> {

    //Consulta para nombre producto
    List<Producto> findByIdCategoria(int idCategoria);

}

