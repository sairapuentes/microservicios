package com.sairapuentes.clientes.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="cliente")
public class Clientes {

    @Id
    @Column(name="ID_cliente")
    private int idCliente;
    @Column(name="nombre-cliente", nullable = false,length=200)
    private String nombreCliente;
    @Column(name="telefono-cliente",length=20)
    private String telefonoCliente;
    @Column(name="email-cliente")
    private String emailCliente;

    public Clientes() {
    }

    public Clientes(int idCliente, String nombreCliente, String telefonoCliente, String emailCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
