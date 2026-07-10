package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.UsuarioRequest;
import com.sairapuentes.login.dto.UsuarioResponse;
import com.sairapuentes.login.entity.Rol;
import com.sairapuentes.login.entity.Usuario;
import com.sairapuentes.login.repository.IRolRepositorio;
import com.sairapuentes.login.repository.IUsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio implements IUsuarioServicio{
     private final IUsuarioRepositorio usuarioRepositorio;
     private final IRolRepositorio rolRepositorio;
     public UsuarioServicio(IUsuarioRepositorio usuarioRepositorio, IRolRepositorio rolRepositorio){
         this.usuarioRepositorio = usuarioRepositorio;
         this.rolRepositorio = rolRepositorio;
     }

     @Override
     public List<UsuarioResponse> findAll(){
         return ((List<Usuario>) usuarioRepositorio.findAll())
                 .stream()
                 .map(this::mapToResponse)
                 .collect(Collectors.toList());
     }
     @Override
     public UsuarioResponse findById(int id){
         Usuario usuario = usuarioRepositorio.findById(id)
                 .orElseThrow(()->new RuntimeException("Usuario no encontrado"));
         return mapToResponse(usuario);
     }
     @Override
     public UsuarioResponse save(UsuarioRequest request){

         Rol rol = rolRepositorio.findById(request.getIdRol())
                 .orElseThrow(()-> new RuntimeException("Rol no encontrado"));

         Usuario usuario = new Usuario();
         usuario.setNombreUsuario(request.getNombreUsuario());
         usuario.setCedula(request.getCedula());
         usuario.setCorreo(request.getCorreo());
         usuario.setPassword(request.getPassword());
         usuario.setRol(rol);
         usuario.setIdSede(request.getIdSede());

         Usuario guardar = usuarioRepositorio.save(usuario);
         return mapToResponse(guardar);
     }
    @Override
    public UsuarioResponse update(int id,UsuarioRequest request){

        Rol rol = rolRepositorio.findById(request.getIdRol())
                .orElseThrow(()-> new RuntimeException("Rol no encontrado"));

        Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado"));
        usuario.setNombreUsuario(request.getNombreUsuario());
        usuario.setCedula(request.getCedula());
        usuario.setCorreo(request.getCorreo());
        usuario.setPassword(request.getPassword());
        usuario.setRol(rol);
        usuario.setIdSede(request.getIdSede());

        Usuario actualizar = usuarioRepositorio.save(usuario);
        return mapToResponse(actualizar);
    }
    @Override
    public void eliminar(int id){
         Usuario usuario = usuarioRepositorio.findById(id)
                 .orElseThrow(()->new RuntimeException("Usuario no encontrado"));
         usuarioRepositorio.delete(usuario);
    }

     private UsuarioResponse mapToResponse(Usuario usuario){
         return new UsuarioResponse(
                 usuario.getIdUsuario(),
                 usuario.getNombreUsuario(),
                 usuario.getCorreo(),
                 usuario.getRol().getNombreRol(),
                 usuario.getIdSede()
         );
     }
}
