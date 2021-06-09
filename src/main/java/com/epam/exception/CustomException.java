package com.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {
	@ExceptionHandler(value = {BookNotFoundException.class})
	public ResponseEntity<String> handleUserNotFoundException(BookNotFoundException bookNotFoundException){
		return new ResponseEntity<String>(bookNotFoundException.getMessage(),HttpStatus.BAD_REQUEST);
	}
}