package br.com.product.catalog.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.product.catalog.model.Products;
import lombok.Getter;

@Getter
public class ProductDto {

	private Long id;
	private String name;
	private String description;
	private double price;
	
	public ProductDto(Products product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public static List<ProductDto> convert(List<Products> products) {
		return products.stream().map(ProductDto::new).collect(Collectors.toList());
	}

}
