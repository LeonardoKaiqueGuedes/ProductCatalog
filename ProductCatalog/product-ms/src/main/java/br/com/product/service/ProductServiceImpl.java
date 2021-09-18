package br.com.product.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.model.Product;
import br.com.product.model.ProductForm;
import br.com.product.repository.ProductRepository;

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
		return ResponseEntity.of(productRepository.findById(id));
	}
	
	@Override
	public List<Product> findFiltered(String q, Double minPrice, Double maxPrice){
		return productRepository.findFiltered(q, minPrice, maxPrice);
	}
	
	@Override
	public ResponseEntity<Product> post(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder){
		Product product = form.created();
		productRepository.save(product);		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new Product(product));
	}
	
	@Override
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody @Valid ProductForm form){
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = form.update(id, productRepository);
			return ResponseEntity.ok(new Product(product));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Override
	public ResponseEntity<Product> delete(@PathVariable Long id){
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();		
	}
}