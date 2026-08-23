package com.sairapuentes.consolidado.service;

import com.sairapuentes.consolidado.communication.IConsolidadoComunicacionVentas;
import com.sairapuentes.consolidado.communication.VentasResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoProductoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoResponse;
import com.sairapuentes.consolidado.dto.ConsolidadoSedeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConsolidadoServicio implements IConsolidadoServicio{

    private final IConsolidadoComunicacionVentas comunicacionVentas;

    public ConsolidadoServicio(IConsolidadoComunicacionVentas comunicacionVentas){
        this.comunicacionVentas = comunicacionVentas;
    }
    private List<VentasResponse> obtenerVentas(Integer idSede, String rol){
        if("ADMIN".equals(rol)){
            return comunicacionVentas.listarTodasLasVentas();
        }
        return comunicacionVentas.listarVentas(idSede);
    }

    @Override
    public ConsolidadoResponse obtenerConsolidado(Integer idSede, String rol){

        List<VentasResponse> ventas = obtenerVentas(idSede, rol);
        int totalVentas = ventas.size();
        int totalUnidades = ventas.stream().mapToInt(VentasResponse::getCantidad).sum();
        double totalVendido = ventas.stream().mapToDouble(VentasResponse::getValorTotal).sum();

        return new ConsolidadoResponse(totalVentas, totalUnidades, totalVendido);
    }
    @Override
    public List<ConsolidadoSedeResponse> obtenerConsolidadoPorSede(Integer idSede, String rol){

        List<VentasResponse> ventas = obtenerVentas(idSede, rol);
        Map<Integer, List<VentasResponse>> ventasPorSede = ventas.stream()
                .collect(Collectors.groupingBy(VentasResponse::getIdSede));
        return ventasPorSede.entrySet().stream().map(entry ->{
            List<VentasResponse> ventasSede = entry.getValue();
            VentasResponse primeraVenta = ventasSede.get(0);
            int totalVentas = ventasSede.size();
            int totalUnidades = ventasSede.stream().mapToInt(VentasResponse::getCantidad).sum();
            double totalVendido = ventasSede.stream().mapToDouble(VentasResponse::getValorTotal).sum();

            return new ConsolidadoSedeResponse(entry.getKey(), primeraVenta.getNombreSede(), totalVentas, totalUnidades,totalVendido);

        })
                .collect(Collectors.toList());
    }
    @Override
    public List<ConsolidadoProductoResponse> obtenerConsolidadoPorProducto(Integer idSede, String rol){
        List<VentasResponse> ventas = obtenerVentas(idSede, rol);
        Map<Integer, List<VentasResponse>> ventasPorProducto = ventas.stream()
                .collect(Collectors.groupingBy(VentasResponse::getIdProducto));
        return ventasPorProducto.entrySet().stream().map(entry ->{
                    List<VentasResponse> ventasProducto = entry.getValue();
                    VentasResponse primeraVenta = ventasProducto.get(0);
                    int totalUnidades = ventasProducto.stream().mapToInt(VentasResponse::getCantidad).sum();
                    double totalVendido = ventasProducto.stream().mapToDouble(VentasResponse::getValorTotal).sum();

                    return new ConsolidadoProductoResponse(entry.getKey(), primeraVenta.getNombreProducto(), totalUnidades,totalVendido);

                })
                .collect(Collectors.toList());
    }

}
