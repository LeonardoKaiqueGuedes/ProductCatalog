package br.com.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Products Catalog API", description = "Desafio FastTrack - Catálogo de Produtos com Java e Spring Boot", version = "1.0",
				   license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")))
public class ProductMsApplication{
	public static void main(String[] args){
		SpringApplication.run(ProductMsApplication.class, args);		
	}
}