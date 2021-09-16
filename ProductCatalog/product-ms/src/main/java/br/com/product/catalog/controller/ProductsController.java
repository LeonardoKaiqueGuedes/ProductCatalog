package br.com.product.catalog.controller;

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

import br.com.product.catalog.form.ProductDetailsForm;
import br.com.product.catalog.form.ProductForm;
import br.com.product.catalog.model.Product;
import br.com.product.catalog.service.ProductService;

@RestController
@RequestMapping(value="/products", produces="application/json")
public class ProductsController{
	
	 @Autowired
	 private ProductService productService;
	
	 @GetMapping()
	 public @ResponseBody List<Product> getAll() {		
		 return productService.findAll();
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<ProductDetailsForm> getById(@PathVariable Long id) {
		 return productService.findById(id);
	 }
	
	 @GetMapping("/search")
	 public ResponseEntity<List<Product>> getFiltered(
			@RequestParam(required=false) String q,
			@RequestParam(required=false) Double min_price,
			@RequestParam(required=false) Double max_price){		
		 return productService.findFiltered(q, min_price, max_price);
	 }
	
	 @Transactional
	 @PostMapping()
	 public ResponseEntity<ProductDetailsForm> newProduct(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		 return productService.post(form, uriBuilder);
	 }
		
	 @Transactional
	 @PutMapping("/{id}")
	 public ResponseEntity<ProductDetailsForm> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		 return productService.update(id, form);
	 }
	
	 @Transactional
	 @DeleteMapping("/{id}")
	 public ResponseEntity<?> removeProduct(@PathVariable Long id) {
		 return productService.delete(id);
	 }
}