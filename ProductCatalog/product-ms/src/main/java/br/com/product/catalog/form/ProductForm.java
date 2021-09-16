package br.com.product.catalog.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.product.catalog.model.Product;
import br.com.product.catalog.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm{
	
	@NotNull @NotEmpty @JsonInclude(Include.NON_NULL)
	private String name;
	
	@NotNull @NotEmpty @JsonInclude(Include.NON_NULL)
	private String description;
	
	@NotNull @Positive @JsonInclude(Include.NON_DEFAULT)
	private double price;
	
	private int status_code;
	private String message;
	
	public Product created() {
		return new Product(name, description, price);
	}
	
	public Product update(Long id, ProductRepository productRepository) {
		Product product = productRepository.getById(id);
		
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);
		
		return product;
	}
		
	public ProductForm error(int status_code, String message) {
		ProductForm productError = new ProductForm();
		
		this.status_code = status_code;
		this.message = message;
		
		productError.setStatus_code(status_code);
		productError.setMessage(message);
		
		return productError;
	}
}