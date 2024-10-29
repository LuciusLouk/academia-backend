package com.deluxe.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.deluxe.entity.Curso;
import com.deluxe.entity.Docente;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Component
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class CursoDTO {
private Long id;
	
	private String nombre;
	private int duracionMeses;
	private double precio;
	private int cupo;
	private long cantidadEgresados;
	private int descuento;
	private String modalidad;
	private boolean activo;
	private LocalDate fechaInicio;
	private String imagen;
}
