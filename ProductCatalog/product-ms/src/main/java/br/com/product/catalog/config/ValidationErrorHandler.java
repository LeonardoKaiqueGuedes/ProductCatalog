package br.com.product.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RestControllerAdvice
public class ValidationErrorHandler {	
	
	private int status_code;
	private String message;
	
	@Autowired @JsonInclude(Include.NON_DEFAULT)
	private MessageSource messageSource;
	
	public ValidationErrorHandler error(int status_code, String message) {
		ValidationErrorHandler productError = new ValidationErrorHandler();	
		this.status_code = status_code;
		this.message = message;
		
		productError.setStatus_code(status_code);
		productError.setMessage(message);
		
		return productError;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ValidationErrorHandler handle(MethodArgumentNotValidException exception) {
		FieldError fe = exception.getBindingResult().getFieldError();		
		int statusCode = HttpStatus.BAD_REQUEST.value();
		String mensagem = "O campo " + fe.getField() + " " + messageSource.getMessage(fe, LocaleContextHolder.getLocale());
		
		ValidationErrorHandler formError = new ValidationErrorHandler();
		formError.error(statusCode, mensagem);
		
		return formError;
	}
}