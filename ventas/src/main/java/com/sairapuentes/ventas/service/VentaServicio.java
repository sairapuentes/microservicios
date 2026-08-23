package com.sairapuentes.ventas.service;

import com.sairapuentes.ventas.communication.*;
import com.sairapuentes.ventas.dto.VentaRequest;
import com.sairapuentes.ventas.dto.VentaResponse;
import com.sairapuentes.ventas.entity.Venta;
import com.sairapuentes.ventas.repository.IVentaRepositorio;

import feign.FeignException;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
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
    public List<VentaResponse> findAllBySede(Integer idSede){
        return ventaRepositorio.findByIdSede(idSede)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VentaResponse findById(int id, Integer idSede) {

        Venta venta = ventaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        
        if(venta.getIdSede() != idSede){
            throw new RuntimeException("La venta no pertenece a esta sede");
        }
        
        return mapToResponse(venta);
    }

    @Override
    public VentaResponse save(VentaRequest request, Integer idSede) {

        Venta venta = new Venta();
        venta.setIdCliente(request.getIdCliente());
        ClienteResponse clienteVenta = ventaComunicacionClientes.getClienteById(request.getIdCliente());
        if(clienteVenta == null){
            throw new RuntimeException("Cliente no encontrado");
        }
        venta.setIdProducto(request.getIdProducto());
        venta.setIdSede(idSede);
        ProductoResponse productoVenta = ventaComunicacionProductos.getProductoById(request.getIdProducto());
        if(productoVenta == null){
            throw new RuntimeException("Producto no encontrado");
        }

        try{
            InventarioResponse inventarioS = ventaComunicacionInventario.getInventario(
                request.getIdProducto(), idSede);

            System.out.println("=================================");
            System.out.println("Producto recibido: " + inventarioS.getIdProducto());
            System.out.println("Sede recibida: " + inventarioS.getIdSede());
            System.out.println("Cantidad recibida: " + inventarioS.getCantidad());
            System.out.println("Cantidad solicitada: " + request.getCantidad());
            System.out.println("=================================");
    
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
            restar.setIdSede(idSede);
            restar.setCantidad(request.getCantidad());
            ventaComunicacionInventario.restarStockVenta(restar);
    
            Venta guardar = ventaRepositorio.save(venta);
            return mapToResponse(guardar);
        }catch(FeignException.BadRequest e){
            throw new RuntimeException("El producto no tiene inventario disponible en esta sede");
        } 
    }

    @Override
    public void eliminar(int id, Integer idSede) {
        Venta venta = ventaRepositorio.findById(id)
                        .orElseThrow(()-> new RuntimeException("Venta no encontrada"));
        
        if(venta.getIdSede() != idSede){
            throw new RuntimeException("La venta no pertenece a esta sede");
        }
        
        ventaRepositorio.delete(venta);
    }

    @Override
    public List<VentaResponse> findByIdProducto(Integer idProducto) {

        return ventaRepositorio.findByIdProducto(idProducto)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaResponse> findByIdProductoAndIdSede(Integer idProducto, Integer idSede) {

        return ventaRepositorio.findByIdProductoAndIdSede(idProducto, idSede)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private VentaResponse mapToResponse(Venta venta){
        ClienteResponse clienteResponse = ventaComunicacionClientes.getClienteById(venta.getIdCliente());
        ProductoResponse productoResponse = ventaComunicacionProductos.getProductoById(venta.getIdProducto());
        InventarioResponse inventarioResponse = ventaComunicacionInventario.getInventario(venta.getIdProducto(), venta.getIdSede());
        return new VentaResponse(
                venta.getIdVenta(),
                venta.getIdCliente(),
                venta.getIdProducto(),
                venta.getCantidad(),
                venta.getValorTotal(),
                venta.getIdSede(),
                clienteResponse.getNombreCliente(),
                productoResponse.getNombreProducto(),
                inventarioResponse.getNombreSede()
        );
    }
}