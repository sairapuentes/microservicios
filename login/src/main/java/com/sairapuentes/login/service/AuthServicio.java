package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.LoginRequest;
import com.sairapuentes.login.dto.LoginResponse;
import com.sairapuentes.login.entity.Usuario;
import com.sairapuentes.login.repository.IUsuarioRepositorio;
import org.springframework.stereotype.Service;

@Service
public class AuthServicio implements IAuthServicio{
    private final IUsuarioRepositorio usuarioRepositorio;
    public AuthServicio(IUsuarioRepositorio usuarioRepositorio){
        this.usuarioRepositorio = usuarioRepositorio;
    }
    @Override
    public LoginResponse login(LoginRequest request){
        Usuario usuario = usuarioRepositorio
                .findByCorreoAndPassword(
                        request.getCorreo(),
                        request.getPassword())
                .orElseThrow(()-> new RuntimeException("Correo o contarseña incorrectos"));
        return new LoginResponse(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol(),
                usuario.getIdSede()
        );
    }

}
