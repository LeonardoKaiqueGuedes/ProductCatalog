package br.com.product.catalog.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.product.catalog.dto.ProductDetailsDto;
import br.com.product.catalog.dto.ProductDto;
import br.com.product.catalog.form.ProductForm;
import br.com.product.catalog.form.UpdateProductForm;
import br.com.product.catalog.model.Products;
import br.com.product.catalog.repository.ProductDao;
import br.com.product.catalog.repository.ProductRepository;

@RestController
@RequestMapping(value="/products", produces="application/json")
public class ProductsController{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductDao productDao;
	
	
	@GetMapping()
	public List<ProductDto> getAll() {		
		List<Products> products = productRepository.findAll();
		return ProductDto.convert(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ProductDetailsDto> getById(@PathVariable Long id) {
		Optional<Products> product = productRepository.findById(id);
		if (product.isPresent()) {
			return ResponseEntity.ok(new ProductDetailsDto(product.get()));
		}		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Products>> getFiltered(
			@RequestParam(required=false) String q,
			@RequestParam(required=false) Double min_price,
			@RequestParam(required=false) Double max_price){
		
		if(q != "" && q != null) {
			q = q.toLowerCase();
		}
		
		List<Products> products = productDao.getProducts(q, min_price, max_price);
		
		return new ResponseEntity<List<Products>>(products, HttpStatus.OK);
	}
	
	@Transactional
	@PostMapping()
	public ResponseEntity<ProductDto> newProduct(@RequestBody @Validated ProductForm form, UriComponentsBuilder uriBuilder) {
		Products product = form.created();
		productRepository.save(product);
		
		URI uri = uriBuilder.path("/products/{id}").buildAndExpand(product.getId()).toUri();
		return ResponseEntity.created(uri).body(new ProductDto(product));
	}
		
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody @Validated UpdateProductForm form) {
		Optional<Products> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			Products product = form.update(id, productRepository);
			return ResponseEntity.ok(new ProductDto(product));
		}
		return ResponseEntity.notFound().build();
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeProduct(@PathVariable Long id) {
		Optional<Products> optional = productRepository.findById(id);
		if (optional.isPresent()) {
			productRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}		
		return ResponseEntity.notFound().build();
	}
}