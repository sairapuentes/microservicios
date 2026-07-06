package com.sairapuentes.inventario.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="sedes")
public class Sede {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_sede")
    private int idSede;
    @Column(name="nombre_sede", nullable = false, length=100)
    private String nombreSede;
    @Column(name="direccion")
    private String direccion;
    @Column(name="ciudad")
    private String ciudad;

    public Sede() {
        super();
    }

    public Sede(int idSede, String nombreSede,String direccion,String ciudad) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }

    public String getNombreSede() {
        return nombreSede;
    }

    public void setNombreSede(String nombreSede) {
        this.nombreSede = nombreSede;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
