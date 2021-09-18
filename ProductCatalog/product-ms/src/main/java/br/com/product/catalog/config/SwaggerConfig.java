package br.com.product.catalog.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket api(){
    	return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.product.catalog.controller"))
          .build()
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
          .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
          .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
          .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
          .operationOrdering(null)
          .apiInfo(productsInfo());
    }
    
    private ApiInfo productsInfo() {
        return new ApiInfoBuilder()
                .title("Products Catalog")
                .description("Desafio FastTrack - Catálogo de Produtos com Java e Spring Boot")
                .version("1.0.0")
                .build();
    }
    
    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {
	        /**
			* 
			*/
			private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
	                .code(500)
	                .message("Internal Server Error")
	                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPOST() {
        return new ArrayList<ResponseMessage>() {
	        /**
			* 
			*/
        	private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
	                .code(201)
	                .message("Created")
	                .build());
            add(new ResponseMessageBuilder()
	            	.code(400)
	            	.message("Bad Request")
	            	.build());
            add(new ResponseMessageBuilder()
            		.code(500)
	                .message("Internal Server Error")
	                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPUT() {
        return new ArrayList<ResponseMessage>() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		{
        	add(new ResponseMessageBuilder()
	        		.code(400)
	        		.message("Bad Request")
	        		.build());
        	add(new ResponseMessageBuilder()
        			.code(404)
        			.message("Not Found")
        			.build());
            add(new ResponseMessageBuilder()
	                .code(500)
	                .message("Internal Server Error")
	                .build());
        }};
    }
        
    private List<ResponseMessage> responseMessageForDELETE() {
    	return new ArrayList<ResponseMessage>() {
    		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
            		.code(404)
            		.message("Not Found")
            		.build());
        }};
    } 
}