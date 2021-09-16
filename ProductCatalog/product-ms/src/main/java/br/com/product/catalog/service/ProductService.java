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

import br.com.product.catalog.form.ProductDetailsForm;
import br.com.product.catalog.form.ProductForm;
import br.com.product.catalog.model.Product;
import br.com.product.catalog.repository.ProductDao;
import br.com.product.catalog.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDao productDao;
	
	public List<Product> findAll() {
		return productRepository.findAll();	
	}
	
	public ResponseEntity<ProductDetailsForm> findById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDetailsForm(product.get()));
		}		
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<List<Product>> findFiltered(String q, Double min_price, Double max_price){
		if(null != q && !"".equals(q)) {
			q = q.toLowerCase();
		}
		
		List<Product> products = productDao.getProducts(q, min_price, max_price);
		
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}
	
	public ResponseEntity<ProductDetailsForm> post(@RequestBody @Valid ProductForm form, UriComponentsBuilder uriBuilder) {
		Product product = form.created();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDetailsForm(product));
	}
	
	public ResponseEntity<ProductDetailsForm> update(@PathVariable Long id, @RequestBody @Valid ProductForm form) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Product product = form.update(id, productRepository);
			return ResponseEntity.ok(new ProductDetailsForm(product));
		}
		return ResponseEntity.notFound().build();
	}
	
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Product> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();		
	}
}
