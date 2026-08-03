package com.sairapuentes.clientes.repository;

import com.sairapuentes.clientes.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IClienteRepositorio extends JpaRepository<Clientes, Long> {

}
