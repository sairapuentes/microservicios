package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.RolRequest;
import com.sairapuentes.login.dto.RolResponse;
import com.sairapuentes.login.entity.Rol;
import com.sairapuentes.login.repository.IRolRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class RolServicio implements IRolServicio {
    private final  IRolRepositorio rolRepositorio;

    public RolServicio(IRolRepositorio rolRepositorio){
        this.rolRepositorio = rolRepositorio;
    }

    @Override
    public List<RolResponse> findAll(){
        return ((List<Rol>) rolRepositorio.findAll())
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RolResponse findById(int id){
        Rol rol = rolRepositorio.findById(id)
                .orElseThrow(()-> new RuntimeException("Rol no encontrado"));
        return mapToResponse(rol);
    }
    @Override
    public RolResponse save(RolRequest request){
        Rol rol = new Rol();
        rol.setNombreRol(request.getNombreRol());

        Rol guardar = rolRepositorio.save(rol);
        return mapToResponse(guardar);
    }
    @Override
    public RolResponse update(int id,RolRequest request){
        Rol rol = rolRepositorio.findById(id).orElseThrow(()-> new RuntimeException("El rol no fue encontrado"));
        rol.setNombreRol(request.getNombreRol());

        Rol actualizar = rolRepositorio.save(rol);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id){
        Rol rol = rolRepositorio.findById(id).orElseThrow(()-> new RuntimeException("El rol no fue encontrado"));
        rolRepositorio.delete(rol);
    }

    private RolResponse mapToResponse (Rol rol){
        return new RolResponse(
                rol.getIdRol(),
                rol.getNombreRol()
        );
    }
}
