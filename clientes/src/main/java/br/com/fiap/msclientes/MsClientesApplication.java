package br.com.fiap.msclientes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Swagger Config", version = "1", description = "Descrição API"))
public class MsClientesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsClientesApplication.class, args);
	}

}
