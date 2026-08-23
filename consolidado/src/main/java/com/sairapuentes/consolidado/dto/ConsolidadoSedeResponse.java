package com.sairapuentes.consolidado.dto;

public class ConsolidadoSedeResponse {
    private int idSede;
    private String nombreSede;
    private int totalVentas;
    private int totalUnidades;
    private double totalVendido;

    public ConsolidadoSedeResponse() {
    }

    public ConsolidadoSedeResponse(int idSede, String nombreSede, int totalVentas, int totalUnidades, double totalVendido) {
        this.idSede = idSede;
        this.nombreSede = nombreSede;
        this.totalVentas = totalVentas;
        this.totalUnidades = totalUnidades;
        this.totalVendido = totalVendido;
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
