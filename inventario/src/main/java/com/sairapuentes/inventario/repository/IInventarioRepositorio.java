package com.sairapuentes.inventario.repository;

import com.sairapuentes.inventario.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInventarioRepositorio extends JpaRepository<Inventario, Integer> {
    Optional<Inventario> findByIdProducto(Integer idProducto);
    Optional<Inventario> findByIdProductoAndSede_IdSede(Integer idProducto, Integer idSede);
}
