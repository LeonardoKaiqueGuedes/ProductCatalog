package br.com.product.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.model.Product;
import br.com.product.model.ProductForm;
import br.com.product.service.ProductService;
import br.com.product.validation.ValidationExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value="/products")
public class ProductController{
	
	 @Autowired
	 private ProductService productService;
	
	 @Operation(summary = "Lista de produtos")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping()
	 public @ResponseBody List<Product> findAll(){		
		 return productService.findAll();
	 }
	
	 
	 @Operation(summary = "Busca de um produto por ID")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( schema = @Schema(implementation = Product.class)) }),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping("/{id}")
	 public ResponseEntity<Product> findById(@PathVariable Long id){
		 return productService.findById(id);
	 }
	
	 
	 @Operation(summary = "Lista de produtos filtrados")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( array = @ArraySchema(schema = @Schema(implementation = Product.class)))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @GetMapping("/search")
	 public List<Product> findFiltered(
			@RequestParam(required=false) String q,
			@RequestParam(required=false, value = "min_price") Double minPrice,
			@RequestParam(required=false, value = "max_price") Double maxPrice){		
		 return productService.findFiltered(q, minPrice, maxPrice);
	 }
	
	 
	 @Operation(summary = "Criação de um produto")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "201", description = "Created", content = { @Content( schema = @Schema(implementation = Product.class)) }),
			 @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content( schema = @Schema(implementation = ValidationExceptionHandler.class))}),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @PostMapping()
	 public ResponseEntity<Product> post(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder){
		 return productService.post(form, uriBuilder);
	 }
	
	 
	 @Operation(summary = "Atualização de um produto")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = { @Content( schema = @Schema(implementation = Product.class)) }),
			 @ApiResponse(responseCode = "400", description = "Bad Request", content = { @Content( schema = @Schema(implementation = ValidationExceptionHandler.class))}),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @PutMapping("/{id}")
	 public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		 return productService.update(id, form);
	 }
	
	 
	 @Operation(summary = "Deleção de um produto")
	 @ApiResponses(value = { 
			 @ApiResponse(responseCode = "200", description = "OK", content = @Content),
			 @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
			 @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)})
	 @Transactional
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Product> delete(@PathVariable Long id){
		 return productService.delete(id);
	 }
}