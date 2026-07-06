package com.sairapuentes.inventario.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="inventario", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ID_producto", "ID_sede"})
})
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_inventario")
    private int idInventario;
    @Column(name="ID_producto", nullable = false)
    private int idProducto;
    @ManyToOne
    @JoinColumn(name="ID_sede")
    private Sede sede;
    @Column(name="stock")
    private int cantidad;

    public Inventario() {
        super();
    }
    public Inventario(int idInventario, int idProducto, Sede sede, int cantidad) {
        this.idInventario = idInventario;
        this.idProducto = idProducto;
        this.sede = sede;
        this.cantidad = cantidad;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
