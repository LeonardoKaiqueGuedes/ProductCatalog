package br.com.product.catalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.product.catalog.repository.ProductRepository;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Product")
public class Product {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@ApiModelProperty(hidden=true)
	private Long id;	
	@NotBlank
	private String name;	
	@NotBlank
	private String description;	
	@NotNull @Positive
	private Double price;
	
	public Product(){}
	
	public Product(String name, String description, Double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	public Product(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	
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
}