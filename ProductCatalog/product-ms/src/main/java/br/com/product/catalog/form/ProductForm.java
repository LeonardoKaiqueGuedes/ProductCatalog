package br.com.product.catalog.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import br.com.product.catalog.model.Products;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm {

	@NotNull @NotEmpty @Length(min = 1)
	private String name;
	
	@NotNull @NotEmpty @Length(min = 1)
	private String description;
	
	@NotNull @Positive
	private double price;

	public Products created() {
		return new Products(name, description, price);
	}

}
