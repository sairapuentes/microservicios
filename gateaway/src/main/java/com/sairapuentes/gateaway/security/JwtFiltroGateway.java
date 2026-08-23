package com.sairapuentes.gateaway.security;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtFiltroGateway implements org.springframework.cloud.gateway.filter.GlobalFilter, Ordered {
    private final JwtServicio jwtServicio;

    public JwtFiltroGateway(JwtServicio jwtServicio) {
        this.jwtServicio = jwtServicio;
    }

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        String path = exchange.getRequest()
                .getURI()
                .getPath();
        String metodo = exchange.getRequest()
                .getMethod()
                .name();

        if (path.startsWith("/api/auth/")) {
            return chain.filter(exchange);
        }

        String autorizacion = exchange.getRequest()
                .getHeaders()
                .getFirst("Authorization");

        if (autorizacion == null ||
                !autorizacion.startsWith("Bearer ")) {
            System.out.println("GATEWAY: No se recibió token");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }

        String token = autorizacion.substring(7);

        if (!jwtServicio.validarToken(token)) {
            System.out.println("GATEWAY: Token inválido");
            exchange.getResponse()
                    .setStatusCode(HttpStatus.UNAUTHORIZED);

            return exchange.getResponse().setComplete();
        }


        String rol = jwtServicio.extraerRol(token);
        Integer idSede = jwtServicio.extraerIdSede(token);

        ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header("X-Usuario-Correo",
                        jwtServicio.extraerCorreo(token))
                .header("X-Usuario-Rol", rol.toUpperCase())
                .header("X-Usuario-Sede",
                        String.valueOf(idSede))
                .build();
        System.out.println("GATEWAY: Token válido");
        System.out.println("GATEWAY: Correo: " + jwtServicio.extraerCorreo(token));
        System.out.println("GATEWAY: Rol: " + rol);
        System.out.println("GATEWAY: Sede: " + idSede);
        exchange = exchange.mutate()
                .request(request)
                .build();

        System.out.println("GATEWAY: X-Usuario-Sede enviado: " + idSede);
        return validarPermisos(path, rol, metodo, exchange, chain);
    }

    private Mono<Void> validarPermisos(
            String path,
            String rol,
            String metodo,
            ServerWebExchange exchange,
            GatewayFilterChain chain) {

        if (path.startsWith("/api/usuario") || path.startsWith("/api/rol")) {

            if (!rol.equals("ADMIN")) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }
        // PRODUCTOS
        if (path.startsWith("/api/productos")) {
            // GET → todos
            if (metodo.equals("GET")) {
                return chain.filter(exchange);
            }
            // POST y PUT → ADMIN
            if ((metodo.equals("POST") || metodo.equals("PUT")) && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            // DELETE → ADMIN
            if (metodo.equals("DELETE") && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // INVENTARIO
        if (path.startsWith("/api/inventario")) {
            // GET → todos
            if (metodo.equals("GET")) {
                return chain.filter(exchange);
            }
            // POST y PUT → ADMIN y BODEGA
            if ((metodo.equals("POST") || metodo.equals("PUT")) && (rol.equals("ADMIN") || rol.equals("BODEGA"))) {
                return chain.filter(exchange);
            }
            // DELETE → ADMIN
            if (metodo.equals("DELETE") && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // CLIENTES
        if (path.startsWith("/api/clientes")) {
            // GET → ADMIN, GERENTE y CAJA
            if (metodo.equals("GET") && (rol.equals("ADMIN") || rol.equals("GERENTE") || rol.equals("CAJA"))) {
                return chain.filter(exchange);
            }
            // POST y PUT → ADMIN y CAJA
            if ((metodo.equals("POST") || metodo.equals("PUT")) && (rol.equals("ADMIN") || rol.equals("CAJA"))) {
                return chain.filter(exchange);
            }
            // DELETE → ADMIN
            if (metodo.equals("DELETE") && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // VENTAS
        if (path.startsWith("/api/ventas")) {
            // GET → ADMIN, GERENTE y CAJA
            if (metodo.equals("GET") && (rol.equals("ADMIN") || rol.equals("GERENTE") || rol.equals("CAJA"))) {
                return chain.filter(exchange);
            }
            // POST → ADMIN y CAJA
            if (metodo.equals("POST") && (rol.equals("GERENTE") || rol.equals("CAJA"))) {
                return chain.filter(exchange);
            }
            // PUT y DELETE → ADMIN y GERENTE
            if ((metodo.equals("PUT") || metodo.equals("DELETE")) && (rol.equals("ADMIN") || rol.equals("GERENTE"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // CATEGORIA
        if (path.startsWith("/api/categoria")) {
            // GET → todos
            if (metodo.equals("GET")) {
                return chain.filter(exchange);
            }
            // POST y PUT → ADMIN y BODEGA
            if ((metodo.equals("POST") || metodo.equals("PUT")) && (rol.equals("ADMIN")|| rol.equals("BODEGA"))) {
                return chain.filter(exchange);
            }
            // DELETE → ADMIN
            if (metodo.equals("DELETE") && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        // SEDE
        if (path.startsWith("/api/sede")) {
            // GET → ADMIN y GERENTE
            if (metodo.equals("GET") && (rol.equals("ADMIN") || rol.equals("GERENTE"))) {
                return chain.filter(exchange);
            }
            // POST y PUT → ADMIN
            if ((metodo.equals("POST") || metodo.equals("PUT")) && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            // DELETE → ADMIN
            if (metodo.equals("DELETE") && (rol.equals("ADMIN"))) {
                return chain.filter(exchange);
            }
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
