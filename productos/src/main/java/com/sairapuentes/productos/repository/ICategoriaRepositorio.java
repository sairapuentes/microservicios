package com.sairapuentes.productos.repository;

import com.sairapuentes.productos.entity.Categoria;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ICategoriaRepositorio extends JpaRepository<Categoria, Integer> {
}
