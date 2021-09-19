package br.com.product.validation;

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

import io.swagger.v3.oas.annotations.Hidden;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Hidden
@RestControllerAdvice
public class ValidationExceptionHandler{

	@Autowired
	@JsonInclude(Include.NON_DEFAULT) @Hidden
	private MessageSource messageSource;
	private int status_code;
	private String message;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ValidationExceptionHandler handle(MethodArgumentNotValidException exception){
		String errorMessage = null;
		int statusCode = 0;
		FieldError fe = exception.getBindingResult().getFieldError();
		
		if(fe != null) {
			errorMessage = "O campo " + fe.getField() + " " + messageSource.getMessage(fe, LocaleContextHolder.getLocale());
			statusCode = HttpStatus.BAD_REQUEST.value();				
		}
			
		ValidationExceptionHandler validationException = new ValidationExceptionHandler();
		validationException.setStatus_code(statusCode);
		validationException.setMessage(errorMessage);
			
		return validationException;		
	}
}