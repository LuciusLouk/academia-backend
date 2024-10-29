package com.deluxe.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data @AllArgsConstructor @NoArgsConstructor
public class LoginResponse {
	private Long id;
	
	private String rol;
		
	private boolean estado;
	
	private String nombreUsuario;
}
