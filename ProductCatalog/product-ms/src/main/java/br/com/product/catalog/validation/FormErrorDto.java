package br.com.product.catalog.validation;

import lombok.Getter;

@Getter
public class FormErrorDto {
	
	private String field;
	private int status_code;
	private String message;
	
	public FormErrorDto(String field, int status_code, String message) {
		this.field = field;
		this.status_code = status_code;
		this.message = message;
	}
}