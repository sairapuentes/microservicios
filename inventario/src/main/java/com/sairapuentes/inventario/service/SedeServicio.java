package com.sairapuentes.inventario.service;

import com.sairapuentes.inventario.dto.SedeRequest;
import com.sairapuentes.inventario.dto.SedeResponse;
import com.sairapuentes.inventario.entity.Sede;
import com.sairapuentes.inventario.repository.ISedeRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SedeServicio implements ISedeServicio{
    private final ISedeRepositorio sedeRepositorio;

    public SedeServicio(ISedeRepositorio sedeRepositorio){
         this.sedeRepositorio = sedeRepositorio;
    }
    @Override
    public List<SedeResponse> findAll(){
        return ((List<Sede>) sedeRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    @Override
    public SedeResponse findById(int id) {
        Sede sede = sedeRepositorio.findById(id)
                .orElseThrow(() ->new RuntimeException("Sede no encontrada"));
        return mapToResponse(sede);
    }
    @Override
    public SedeResponse save(SedeRequest request) {

        Sede sede = new Sede();
        sede.setNombreSede(request.getNombreSede());
        sede.setCiudad(request.getCiudad());
        sede.setDireccion(request.getDireccion());

        Sede guardar = sedeRepositorio.save(sede);
        return mapToResponse(guardar);
    }
    @Override
    public SedeResponse update(int id, SedeRequest request){
        Sede sede = sedeRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("La sede no fue encontrada"));
        sede.setNombreSede(request.getNombreSede());
        sede.setCiudad(request.getCiudad());
        sede.setDireccion(request.getDireccion());
        Sede actualizar = sedeRepositorio.save(sede);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id){
        Sede sede = sedeRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Sede no encontrada"));
        sedeRepositorio.delete(sede);
    }

    private SedeResponse mapToResponse(Sede sede){
        return new SedeResponse(
                sede.getIdSede(),
                sede.getNombreSede(),
                sede.getCiudad(),
                sede.getDireccion()
        );
    }
}
