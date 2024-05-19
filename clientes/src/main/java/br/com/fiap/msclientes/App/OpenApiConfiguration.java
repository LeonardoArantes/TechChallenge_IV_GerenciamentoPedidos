package br.com.fiap.msclientes.App;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API de Clientes")
                        .description("API de Gerenciamento de Clientes")
                        .contact(new Contact().name("Victor Montibeller").email("victormontibeller@hotmail.com")
                        ).version("1.0.0"));
    }

}