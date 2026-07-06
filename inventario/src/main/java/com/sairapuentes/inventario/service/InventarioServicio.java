package com.sairapuentes.inventario.service;

import com.sairapuentes.inventario.dto.InventarioRequest;
import com.sairapuentes.inventario.dto.InventarioResponse;
import com.sairapuentes.inventario.dto.InventarioRestarRequest;
import com.sairapuentes.inventario.entity.Inventario;
import com.sairapuentes.inventario.entity.Sede;
import com.sairapuentes.inventario.repository.IInventarioRepositorio;
import com.sairapuentes.inventario.repository.ISedeRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InventarioServicio implements IInventarioServicio {
    private final IInventarioRepositorio inventarioRepositorio;
    private final ISedeRepositorio sedeRepositorio;
    public InventarioServicio(IInventarioRepositorio inventarioRepositorio, ISedeRepositorio sedeRepositorio){
        this.inventarioRepositorio = inventarioRepositorio;
        this.sedeRepositorio = sedeRepositorio;
    }
    @Override
    public List<InventarioResponse> findAll(){
        return ((List<Inventario>) inventarioRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InventarioResponse findById(int id){
        Inventario inventario = inventarioRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Identificador de inventario no encontrado"));
        return mapToResponse(inventario);
    }

    @Override
    public InventarioResponse save(InventarioRequest request){
        Sede sede = sedeRepositorio
                .findById(request.getIdSede())
                .orElseThrow(()-> new RuntimeException("Sede no encontrada"));
        Optional<Inventario> existente = inventarioRepositorio.findByIdProductoAndSede_IdSede(
                request.getIdProducto(),
                request.getIdSede());
        if(existente.isPresent()){
            throw new RuntimeException("Ese producto ya existe para esa sede");
        }
        Inventario inventario = new Inventario();

        inventario.setIdProducto(request.getIdProducto());
        if(request.getCantidad() <= 0){
            throw new RuntimeException("La cantidad debe ser mayor que cero");
        }
        //if(inventario.getCantidad()< request.getCantidad()){
        //    throw new RuntimeException("No hay stock suficiente");
        //}
        //inventario.setCantidad(inventario.getCantidad() - request.getCantidad());
        inventario.setCantidad(request.getCantidad());
        inventario.setSede(sede);

        Inventario guardar = inventarioRepositorio.save(inventario);
        return mapToResponse(guardar);
    }
    @Override
    public InventarioResponse update(int id, InventarioRequest request){
        Sede sede = sedeRepositorio
                .findById(request.getIdSede())
                .orElseThrow(()-> new RuntimeException("Sede no encontrada"));

        Inventario inventario = inventarioRepositorio.findById(id)
                .orElseThrow(()->new RuntimeException("El inventario no fue encontrado"));

        Optional<Inventario> existente2 = inventarioRepositorio.findByIdProductoAndSede_IdSede(
                request.getIdProducto(),
                request.getIdSede());
        if(existente2.isPresent() && existente2.get().getIdInventario() != id){
            throw new RuntimeException("Ese producto ya existe para esa sede");
        }
        inventario.setIdProducto(request.getIdProducto());
        if(request.getCantidad()<=0){
            throw new RuntimeException("La cantidad debe ser mayor que cero");
        }
        inventario.setCantidad(request.getCantidad());
        inventario.setSede(sede);

        Inventario actualizar = inventarioRepositorio.save(inventario);
        return mapToResponse(actualizar);
    }
    @Override
    public void restarStock(InventarioRestarRequest request){
        Inventario inventario = inventarioRepositorio.findByIdProductoAndSede_IdSede(
                request.getIdProducto(),
                request.getIdSede())
                .orElseThrow(()->
                        new RuntimeException("No existe inventario para ese producto en esa sede"));
        if(inventario.getCantidad() < request.getCantidad()){
            throw new RuntimeException("Stock insuficiente");
        }
        inventario.setCantidad(
                inventario.getCantidad() - request.getCantidad());
        inventarioRepositorio.save(inventario);
    }
    @Override
    public void eliminar(int id){
        Inventario invetario = inventarioRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Inventario no encontrado"));
        inventarioRepositorio.delete(invetario);
    }
    @Override
    public InventarioResponse findByIdProducto(Integer idProducto){
        Inventario inventarioP = inventarioRepositorio
                .findByIdProducto(idProducto)
                .orElseThrow(()-> new RuntimeException("Producto no encontrado en inventario"));
       return mapToResponse(inventarioP);
   }

    private InventarioResponse mapToResponse(Inventario inventario){
        return new InventarioResponse(
                inventario.getIdInventario(),
                inventario.getIdProducto(),
                inventario.getCantidad(),
                inventario.getSede().getIdSede()
        );
    }

}
