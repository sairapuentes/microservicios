package com.sairapuentes.login.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtFiltro extends OncePerRequestFilter {
    private final JwtServicio jwtServicio;

    public JwtFiltro(JwtServicio jwtServicio){
        this.jwtServicio = jwtServicio;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String autorizacion= request.getHeader("Authorization");
        if(autorizacion == null || !autorizacion.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }

        String token = autorizacion.substring(7);
        try{

            if(jwtServicio.validarToken(token)){
                System.out.println("Token Valido");
                String correo = jwtServicio.extraerCorreo(token);
                System.out.println("Correo "+ correo);
                String rol = jwtServicio.extraerRol(token);
                System.out.println("Rol "+ rol);

                List<GrantedAuthority> authRol = List.of(new SimpleGrantedAuthority("ROLE_" + rol));

                UsernamePasswordAuthenticationToken autenticacionUp = new UsernamePasswordAuthenticationToken(correo, null, authRol);
                SecurityContextHolder.getContext().setAuthentication(autenticacionUp);
                System.out.println("Autenticacion creada");
                System.out.println("Usuario: " + SecurityContextHolder.getContext().getAuthentication().getName());
                System.out.println("Authorities: " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
                System.out.println("Authenticated: " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
                System.out.println("--------------------------");
                System.out.println("ROL JWT: " + rol);
                System.out.println("AUTHORITY: ROLE_" + rol.toUpperCase());
                System.out.println("Autenticacion creada");
                System.out.println("Usuario: " +
                        SecurityContextHolder.getContext().getAuthentication().getName());

                System.out.println("Authorities: " +
                        SecurityContextHolder.getContext().getAuthentication().getAuthorities());

                System.out.println("Authenticated: " +
                        SecurityContextHolder.getContext().getAuthentication().isAuthenticated());
            }
        }catch (Exception e){
            System.out.println("Error JWT " + e.getMessage());
            e.printStackTrace();
            SecurityContextHolder.clearContext();
        }
        filterChain.doFilter(request, response);
    }
}
