package br.com.product.catalog.form;

import lombok.Getter;

@Getter
public class ErrorForm {
	
	private String field;
	private int status_code;
	private String message;
	
	public ErrorForm(String field, int status_code, String message) {
		this.field = field;
		this.status_code = status_code;
		this.message = message;
	}
}