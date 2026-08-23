package com.sairapuentes.consolidado.dto;

public class ConsolidadoResponse {
    private int totalVentas;
    private int totalUnidades;
    private double totalVendido;

    public ConsolidadoResponse(){
    }

    public ConsolidadoResponse(int totalVentas, int totalUnidades, double totalVendido) {
        this.totalVentas = totalVentas;
        this.totalUnidades = totalUnidades;
        this.totalVendido = totalVendido;
    }

    public int getTotalVentas() {
        return totalVentas;
    }

    public void setTotalVentas(int totalVentas) {
        this.totalVentas = totalVentas;
    }

    public int getTotalUnidades() {
        return totalUnidades;
    }

    public void setTotalUnidades(int totalUnidades) {
        this.totalUnidades = totalUnidades;
    }

    public double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(double totalVendido) {
        this.totalVendido = totalVendido;
    }
}
