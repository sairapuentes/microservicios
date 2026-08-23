package com.sairapuentes.login.security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final  JwtFiltro jwtFiltro;

    public SecurityConfig(JwtFiltro jwtFiltro){
        this.jwtFiltro =jwtFiltro;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**").permitAll()
                        // ADMIN
                        .requestMatchers("/api/usuario/**").hasRole("ADMIN")
                        .requestMatchers("/api/rol/**").hasRole("ADMIN")
                        .requestMatchers("/api/sede/**").hasRole("ADMIN")

                        // PRODUCTOS
                        .requestMatchers("/api/productos/**")
                        .hasAnyRole("ADMIN", "GERENTE", "BODEGA", "CAJA")

                        .requestMatchers("/api/categoria/**")
                        .hasAnyRole("ADMIN", "GERENTE", "BODEGA")

                        // INVENTARIO
                        .requestMatchers("/api/inventario/**")
                        .hasAnyRole("ADMIN", "GERENTE", "BODEGA", "CAJA")

                        // CLIENTES
                        .requestMatchers("/api/clientes/**")
                        .hasAnyRole("ADMIN", "GERENTE", "CAJA")

                        // VENTAS
                        .requestMatchers("/api/ventas/**")
                        .hasAnyRole("ADMIN", "GERENTE", "CAJA")

                        // CONSOLIDADO
                        .requestMatchers("/api/consolidado/**")
                        .hasAnyRole("ADMIN", "GERENTE")
                        .anyRequest().authenticated()
                ).addFilterBefore(jwtFiltro, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
