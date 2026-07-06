package com.sairapuentes.inventario.repository;

import com.sairapuentes.inventario.entity.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISedeRepositorio extends JpaRepository<Sede, Integer> {
}
