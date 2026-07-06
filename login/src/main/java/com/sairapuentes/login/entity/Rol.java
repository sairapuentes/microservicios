package com.sairapuentes.login.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_rol")
    private int idRol;
    @Column(name="nombre_rol", nullable = false, length=100)
    private String nombreRol;

    public Rol() {
    }

    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }
}
