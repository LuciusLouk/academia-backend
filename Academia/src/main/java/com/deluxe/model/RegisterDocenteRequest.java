package com.deluxe.model;

import java.time.LocalDate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@Builder
@NoArgsConstructor @AllArgsConstructor
public class RegisterDocenteRequest {
	private Long id;
	private String email;
	private String password;
	private String rol;
	private LocalDate fechaCreacion;
	private boolean estado;
	private String nombreUsuario;
	private String telefono;
	
	private String pais;
	private String apellido;
	private String nombre;
	private String tipoDni;
	private String dni;
	private boolean habilitado;
}
