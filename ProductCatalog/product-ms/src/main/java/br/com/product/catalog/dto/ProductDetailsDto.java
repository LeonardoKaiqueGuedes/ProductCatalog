package br.com.product.catalog.dto;

import br.com.product.catalog.model.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsDto{

	private Long id;
	private String name;
	private String description;
	private double price;
	
	public ProductDetailsDto(Products product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}
}
