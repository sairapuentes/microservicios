package com.sairapuentes.login.service;

import com.sairapuentes.login.dto.LoginRequest;
import com.sairapuentes.login.dto.LoginResponse;
import com.sairapuentes.login.entity.Usuario;
import com.sairapuentes.login.repository.IUsuarioRepositorio;
import com.sairapuentes.login.security.JwtServicio;
import org.springframework.stereotype.Service;

@Service
public class AuthServicio implements IAuthServicio{
    private final IUsuarioRepositorio usuarioRepositorio;
    private final JwtServicio jwtServicio;
    public AuthServicio(IUsuarioRepositorio usuarioRepositorio, JwtServicio jwtServicio){
        this.usuarioRepositorio = usuarioRepositorio;
        this.jwtServicio = jwtServicio;
    }
    @Override
    public LoginResponse login(LoginRequest request){
        Usuario usuario = usuarioRepositorio
                .findByCorreoAndPassword(
                        request.getCorreo(),
                        request.getPassword())
                .orElseThrow(()-> new RuntimeException("Correo o contarseña incorrectos"));
        String token = jwtServicio.generarToken(usuario);
        return new LoginResponse(
                usuario.getIdUsuario(),
                usuario.getNombreUsuario(),
                usuario.getCorreo(),
                usuario.getRol().getNombreRol(),
                usuario.getIdSede(),
                token
        );
    }

}
