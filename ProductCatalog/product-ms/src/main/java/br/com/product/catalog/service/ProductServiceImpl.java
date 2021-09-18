package br.com.product.catalog.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.catalog.model.Product;
import br.com.product.catalog.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> findAll(){
		return productRepository.findAll();	
	}
	
	@Override
	public ResponseEntity<Product> findById(Long id){
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new Product(product.get()));
		}		
		return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<List<Product>> findFiltered(String q, Double min_price, Double max_price){
		List<Product> products = productRepository.findFiltered(q, min_price, max_price);		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Product> post(@RequestBody @Valid Product form, UriComponentsBuilder uriBuilder){
		Product product = form.created();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new Product(product));
	}
	
	@Override
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid Product form){
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = form.update(id, productRepository);
			return ResponseEntity.ok(new Product(product));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<?> delete(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();		
	}
}