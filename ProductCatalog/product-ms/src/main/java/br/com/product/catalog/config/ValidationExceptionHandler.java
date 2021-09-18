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
public class ValidationExceptionHandler {	
	
	@Autowired @JsonInclude(Include.NON_DEFAULT)
	private MessageSource messageSource;
	private int status_code;
	private String message;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	public ValidationExceptionHandler handle(MethodArgumentNotValidException e) {
			
		FieldError fe = e.getBindingResult().getFieldError();		
		String errorMessage = "O campo " + fe.getField() + " " + messageSource.getMessage(fe, LocaleContextHolder.getLocale());
		int statusCode = HttpStatus.BAD_REQUEST.value();
		
		ValidationExceptionHandler exception = new ValidationExceptionHandler();
		exception.setStatus_code(statusCode);
		exception.setMessage(errorMessage);
		
		return exception;
	}
}