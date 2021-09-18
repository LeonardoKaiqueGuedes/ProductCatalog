package br.com.product.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.model.Product;
import br.com.product.model.ProductForm;

public interface ProductService{	
	public List<Product> findAll();	
	public ResponseEntity<Product> findById(Long id);	
	public List<Product> findFiltered(String q, Double minPrice, Double maxPrice);	
	public ResponseEntity<Product> post(ProductForm form, UriComponentsBuilder uriBuilder);
	public ResponseEntity<Product> update(Long id, ProductForm form);	
	public ResponseEntity<Product> delete(Long id);
}