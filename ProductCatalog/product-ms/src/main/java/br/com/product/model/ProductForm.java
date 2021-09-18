package br.com.product.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.product.repository.ProductRepository;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm{

	@NotBlank
	private String name;	
	@NotBlank
	private String description;	
	@NotNull @Positive
	private Double price;
			
	public Product created(){
		return new Product(name, description, price);
	}
	
	public Product update(Long id, ProductRepository productRepository){
		Product product = productRepository.getById(id);	
		product.setName(this.name);
		product.setDescription(this.description);
		product.setPrice(this.price);
		
		return product;
	}
}