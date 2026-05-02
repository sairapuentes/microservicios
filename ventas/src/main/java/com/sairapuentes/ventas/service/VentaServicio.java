package com.sairapuentes.ventas.service;

import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import com.sairapuentes.ventas.repository.IVentaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServicio implements IVentaServico {

    private final IVentaRepositorio ventaRepositorio;

    public VentaServicio(IVentaRepositorio ventaRepositorio){
        this.ventaRepositorio = ventaRepositorio;
    }
    @Override
    public List<VentaResponse> findAll() {

        return ((List<Venta>) ventaRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VentaResponse findById(int id) {

        Venta venta = ventaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return mapToResponse(venta);
    }

    @Override
    public VentaResponse save(VentaRequest request) {

        Venta venta = new Venta();
        venta.setIdVenta(request.getIdVenta());
        venta.setIdCliente(request.getIdCliente());
        venta.setIdCiudad(request.getIdCiudad());
        venta.setIdProducto(request.getIdProducto());
        venta.setCantidad(request.getCantidad());
        venta.setValorTotal(request.getValorTotal());

        Venta guardar = ventaRepositorio.save(venta);
        return mapToResponse(guardar);
    }

    @Override
    public void eliminar(int id) {

        ventaRepositorio.deleteById(id);
    }

    @Override
    public List<VentaResponse> findByIdProducto(Integer idProducto) {

        return ventaRepositorio.findByIdProducto(idProducto)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private VentaResponse mapToResponse(Venta venta){
        return new VentaResponse(
                venta.getIdVenta(),
                venta.getIdCliente(),
                venta.getIdCiudad(),
                venta.getIdProducto(),
                venta.getCantidad(),
                venta.getValorTotal()
        );
    }
}