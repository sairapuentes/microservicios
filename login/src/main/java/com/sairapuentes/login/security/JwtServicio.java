package com.sairapuentes.login.security;

import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;
import com.sairapuentes.login.entity.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServicio {
    private final String SECRET_KEY = "microservicios-supermercado-secret-key-2026";

    private final long EXPIRATION_TIME = 1000 * 60 * 60;

    private SecretKey getKey(){
        return Keys.hmacShaKeyFor(
                SECRET_KEY.getBytes(StandardCharsets.UTF_8)
        );
    }
    public String generarToken(Usuario usuario){
        String rol = usuario.getRol().getNombreRol().trim().toUpperCase();
        return Jwts.builder()
                .subject(usuario.getCorreo())
                .claim("idUsuario",usuario.getIdUsuario())
                .claim("rol", rol)
                .claim("idSede", usuario.getIdSede())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getKey())
                .compact();
    }
    public String extraerCorreo(String token){
        //para saber que correo es el token asignado
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public String extraerRol(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("rol", String.class);
    }

    public boolean validarToken(String token){
        //Se valida que el token no este vencido o alterado
        try{
            Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token);
            return  true;
        }catch (Exception e){
            return false;
        }
    }
}
