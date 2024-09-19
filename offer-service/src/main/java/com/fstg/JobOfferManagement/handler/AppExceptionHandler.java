package com.fstg.JobOfferManagement.handler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fstg.JobOfferManagement.exception.CompanyNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fstg.JobOfferManagement.exception.EntityAlreadyExistsException;
import com.fstg.JobOfferManagement.shared.ErrorMessage;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = CompanyNotFoundException.class)
	public ResponseEntity<String> handle(CompanyNotFoundException ex){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(ex.getMsg());
	}

 @ExceptionHandler(value = {EntityNotFoundException.class})
	    public ResponseEntity<Object> entityNotFoundException(EntityNotFoundException ex) {
	        ErrorMessage errorMessage = ErrorMessage.builder()
	                .message(ex.getMessage())
	                .timestamp(new Date())
	                .code(404)
	                .build();
	        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	    }
	 @ExceptionHandler(value = {EntityAlreadyExistsException.class})
	    public ResponseEntity<Object> entityAlreadyExistsException(EntityAlreadyExistsException ex) {
	        ErrorMessage errorMessage = ErrorMessage.builder()
	                .message(ex.getMessage())
	                .timestamp(new Date())
	                .code(409)
	                .build();
	        return new ResponseEntity<>(errorMessage, HttpStatus.CONFLICT);
	    }
	 @ExceptionHandler(value = MethodArgumentNotValidException.class)
	    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	        Map<String, String> errors = new HashMap<>(); 
	        ex.getBindingResult().getFieldErrors().forEach(error ->
	                errors.put(error.getField(), error.getDefaultMessage()));
	        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	    }

}
