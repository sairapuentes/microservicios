package com.sairapuentes.login.repository;

import com.sairapuentes.login.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepositorio extends JpaRepository<Rol, Integer> {
}
