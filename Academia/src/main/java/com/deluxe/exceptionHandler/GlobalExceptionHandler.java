package com.deluxe.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
/*
	//TODO Crear un mensaje personalizado para la respuesta. Un objeto especifico
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<String> handlerArgumentException(IllegalArgumentException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	//TODO Crear un mensaje personalizado para la respuesta
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handlerRuntimeException(RuntimeException exception){
		return new ResponseEntity<String>(exception.getMessage(),HttpStatus.BAD_GATEWAY);
	}*/
}
