package com.sairapuentes.consolidado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class ConsolidadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsolidadoApplication.class, args);
	}

}
