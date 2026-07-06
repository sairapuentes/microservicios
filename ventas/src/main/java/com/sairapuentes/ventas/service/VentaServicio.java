package com.sairapuentes.ventas.service;

import com.sairapuentes.ventas.communication.*;
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
    private final IVentaComunicacionProductos ventaComunicacionProductos;
    private final IVentaComunicacionClientes ventaComunicacionClientes;
    private final IVentaComunicacionInventario ventaComunicacionInventario;
    public VentaServicio(
            IVentaRepositorio ventaRepositorio,
            IVentaComunicacionProductos ventaComunicacionProductos,
            IVentaComunicacionClientes ventaComunicacionClientes,
            IVentaComunicacionInventario ventaComunicacionInventario){
        this.ventaRepositorio = ventaRepositorio;
        this.ventaComunicacionProductos = ventaComunicacionProductos;
        this.ventaComunicacionClientes = ventaComunicacionClientes;
        this.ventaComunicacionInventario = ventaComunicacionInventario;
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
        venta.setIdCliente(request.getIdCliente());
        ClienteResponse clienteVenta = ventaComunicacionClientes.getClienteById(request.getIdCliente());
        if(clienteVenta == null){
            throw new RuntimeException("Cliente no encontrado");
        }
        venta.setIdCiudad(request.getIdCiudad());
        venta.setIdProducto(request.getIdProducto());
        venta.setIdSede(request.getIdSede());
        ProductoResponse productoVenta = ventaComunicacionProductos.getProductoById(request.getIdProducto());
        if(productoVenta == null){
            throw new RuntimeException("Producto no encontrado");
        }
        InventarioResponse inventarioS = ventaComunicacionInventario.getInventario(
                request.getIdProducto(),
                request.getIdSede());
        if(inventarioS.getCantidad()< request.getCantidad()){
            throw new RuntimeException("No hay stock suficiente");
        }
        venta.setCantidad(request.getCantidad());
        if(request.getCantidad()<=0){
            throw new RuntimeException("La cantidad debe ser mayor que cero");
        }
        double total= productoVenta.getPrecioVenta() * request.getCantidad();
        venta.setValorTotal(total);


        InventarioRestarRequest restar = new InventarioRestarRequest();
        restar.setIdProducto(request.getIdProducto());
        restar.setIdSede(request.getIdSede());
        restar.setCantidad(request.getCantidad());
        ventaComunicacionInventario.restarStock(restar);

        Venta guardar = ventaRepositorio.save(venta);
        return mapToResponse(guardar);
    }


    @Override
    public void eliminar(int id) {
        Venta venta = ventaRepositorio.findById(id)
                        .orElseThrow(()-> new RuntimeException("Venta no encontrada"));
        ventaRepositorio.delete(venta);
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
                venta.getValorTotal(),
                venta.getIdSede()
        );
    }
}