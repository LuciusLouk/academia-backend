package com.deluxe.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RegisterAlumnoRequest {

	private Long id;
	private String email;
	private String password;
	private String nombreUsuario;
	private String telefono;
	

	private String pais;
	private String apellido;
	private String nombre;
}
