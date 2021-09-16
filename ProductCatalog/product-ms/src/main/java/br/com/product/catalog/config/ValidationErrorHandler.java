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

import br.com.product.catalog.form.ProductForm;

@RestControllerAdvice
public class ValidationErrorHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ProductForm handle(MethodArgumentNotValidException exception) {
		FieldError fe = exception.getBindingResult().getFieldError();		
		int statusCode = HttpStatus.BAD_REQUEST.value();
		String mensagem = "O campo " + fe.getField() + " " + messageSource.getMessage(fe, LocaleContextHolder.getLocale());
		
		ProductForm formError = new ProductForm();
		formError.error(statusCode, mensagem);
		
		return formError;
	}
}