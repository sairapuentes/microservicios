package com.sairapuentes.login.security;
import com.sairapuentes.login.security.JwtServicio;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtFiltro extends OncePerRequestFilter {
    private final JwtServicio jwtServicio;

    public JwtFiltro(JwtServicio jwtServicio){
        this.jwtServicio = jwtServicio;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String autorizacion= request.getHeader("Authorization");
        if(autorizacion == null || !autorizacion.startsWith("Bearer")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = autorizacion.substring(7);
        try{
            Claims claims = jwtServicio.extraerClaims(token);
            String correo = claims.getSubject();

            if(jwtServicio.validarToken(token)){
                String correo = jwtServicio.extraerCorreo(token);
                UsernamePasswordAuthenticationToken autenticacionUp = new UsernamePasswordAuthenticationToken(correo, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(autenticacionUp);
            }
        }catch (Exception e){
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
