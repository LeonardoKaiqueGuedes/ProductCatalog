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

@RestController
@RequestMapping(value="/products", produces="application/json")
public class ProductController{
	
	 @Autowired
	 private ProductService productService;
	
	 @GetMapping()
	 public @ResponseBody List<Product> findAll(){		
		 return productService.findAll();
	 }
	
	 @GetMapping("/{id}")
	 public ResponseEntity<Product> findById(@PathVariable Long id){
		 return productService.findById(id);
	 }
	
	 @GetMapping("/search")
	 public List<Product> findFiltered(
			@RequestParam(required=false) String q,
			@RequestParam(value = "min_price", required=false) Double minPrice,
			@RequestParam(value = "max_price", required=false) Double maxPrice){		
		 return productService.findFiltered(q, minPrice, maxPrice);
	 }
	
	 @Transactional
	 @PostMapping()
	 public ResponseEntity<Product> post(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder){
		 return productService.post(form, uriBuilder);
	 }
		
	 @Transactional
	 @PutMapping("/{id}")
	 public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		 return productService.update(id, form);
	 }
	
	 @Transactional
	 @DeleteMapping("/{id}")
	 public ResponseEntity<Product> delete(@PathVariable Long id){
		 return productService.delete(id);
	 }
}