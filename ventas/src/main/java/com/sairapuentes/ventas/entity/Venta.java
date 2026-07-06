package com.sairapuentes.ventas.entity;

import jakarta.persistence.*;

@Entity
@Table(name ="venta")

public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_venta")
    private int idVenta;
    @Column(name = "ID_cliente")
    private int idCliente;
    @Column(name = "ID_ciudad")
    private int idCiudad;
    @Column(name = "ID_producto")
    private int idProducto;
    @Column(name = "cantidad")
    private int cantidad;
    @Column(name = "valor_total")
    private double valorTotal;
    @Column(name = "ID_sede")
    private int idSede;
    public Venta() {
    }

    public Venta(int idCliente, int idCiudad, int idProducto, int cantidad, double valorTotal, int idSede) {
        this.idCliente = idCliente;
        this.idCiudad = idCiudad;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.valorTotal = valorTotal;
        this.idSede = idSede;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getIdSede() {
        return idSede;
    }

    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
}
