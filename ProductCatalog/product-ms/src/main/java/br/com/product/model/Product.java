package br.com.product.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="Product")
public class Product{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;	
	private String name;	
	private String description;	
	private Double price;
	
	public Product(){}
	
	public Product(Product product){
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
	
	public Product(String name, String description, Double price){
		this.name = name;
		this.description = description;
		this.price = price;
	}
}