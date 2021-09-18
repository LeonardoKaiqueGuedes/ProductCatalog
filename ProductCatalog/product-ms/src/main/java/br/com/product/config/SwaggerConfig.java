package br.com.product.config;

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
public class SwaggerConfig{
	
	private final String[] statusMessage = {"Created", "Bad Request", "Not Found", "Internal Server Error"};
	
    @Bean
    public Docket api(){
    	return new Docket(DocumentationType.SWAGGER_2)
          .select()
          .apis(RequestHandlerSelectors.basePackage("br.com.product.controller"))
          .build()
          .useDefaultResponseMessages(false)
          .globalResponseMessage(RequestMethod.GET, responseMessageForGETDELETE())
          .globalResponseMessage(RequestMethod.DELETE, responseMessageForGETDELETE())
          .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
          .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
          .apiInfo(productsInfo());
    }
    
    private ApiInfo productsInfo(){
        return new ApiInfoBuilder()
                .title("Products Catalog API")
                .description("Desafio FastTrack - Catálogo de Produtos com Java e Spring Boot")
                .version("1.0.0")
                .build();
    }
    
    private List<ResponseMessage> responseMessageForGETDELETE(){
        return new ArrayList<ResponseMessage>(){
	        /**
			* 
			*/
			private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
	                .code(500)
	                .message(statusMessage[3])
	                .build());            
            add(new ResponseMessageBuilder()
            		.code(404)
            		.message(statusMessage[2])
            		.build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPOST(){
        return new ArrayList<ResponseMessage>(){
	        /**
			* 
			*/
        	private static final long serialVersionUID = 1L;
		{
            add(new ResponseMessageBuilder()
	                .code(201)
	                .message(statusMessage[0])
	                .build());
            add(new ResponseMessageBuilder()
	            	.code(400)
	            	.message(statusMessage[1])
	            	.build());
            add(new ResponseMessageBuilder()
            		.code(500)
	                .message(statusMessage[3])
	                .build());
        }};
    }
    
    private List<ResponseMessage> responseMessageForPUT(){
        return new ArrayList<ResponseMessage>(){
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
		{
        	add(new ResponseMessageBuilder()
	        		.code(400)
	        		.message(statusMessage[1])
	        		.build());
        	add(new ResponseMessageBuilder()
        			.code(404)
        			.message(statusMessage[2])
        			.build());
            add(new ResponseMessageBuilder()
	                .code(500)
	                .message(statusMessage[3])
	                .build());
        }};
    }
}