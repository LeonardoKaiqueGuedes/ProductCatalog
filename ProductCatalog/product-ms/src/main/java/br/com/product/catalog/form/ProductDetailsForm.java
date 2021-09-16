package br.com.product.catalog.form;

import java.util.List;
import java.util.stream.Collectors;

import br.com.product.catalog.model.Product;
import lombok.Getter;

@Getter
public class ProductDetailsForm {

	private Long id;
	private String name;
	private String description;
	private double price;
	
	public ProductDetailsForm(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.description = product.getDescription();
		this.price = product.getPrice();
	}

	public static List<ProductDetailsForm> convert(List<Product> products) {
		return products.stream().map(ProductDetailsForm::new).collect(Collectors.toList());
	}
	
}