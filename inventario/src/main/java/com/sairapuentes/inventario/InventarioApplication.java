package com.sairapuentes.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
//@EnableFeignClients
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class InventarioApplication {

	public static void main(String[] args) {

		SpringApplication.run(InventarioApplication.class, args);
	}

}
