package com.sairapuentes.productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class ProductosApplication {

	public static void main(String[] args) {

		SpringApplication.run(ProductosApplication.class, args);
	}

}
