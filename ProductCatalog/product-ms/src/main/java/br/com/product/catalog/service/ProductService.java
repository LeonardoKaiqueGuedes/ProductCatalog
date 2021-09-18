package br.com.product.catalog.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.catalog.model.Product;

public interface ProductService {	
	public List<Product> findAll();	
	public ResponseEntity<Product> findById(Long id);	
	public ResponseEntity<List<Product>> findFiltered(String q, Double min_price, Double max_price);	
	public ResponseEntity<Product> post(Product form, UriComponentsBuilder uriBuilder);
	public ResponseEntity<Product> update(Long id, Product form);	
	public ResponseEntity<?> delete(Long id);
}