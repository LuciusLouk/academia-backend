package com.deluxe.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Component
@Table(name="CURSOS")
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Curso{

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="cur_id")
	private Long id;
	

	@Column(name="cur_nombre")
	private String nombre;
	@Column(name="cur_duracion")
	private int duracionMeses;
	@Column(name="cur_precio")
	private double precio;
	@Column(name="cur_cupo")
	private int cupo;
	@Column(name="cur_cantidad_egresados")
	private long cantidadEgresados;
	@Column(name="cur_descuento")
	private int descuento;
	@Column(name="cur_modalidad")
	private String modalidad;
	@Column(name="cur_activo")
	private boolean activo;
	@Column(name="cur_fecha_inicio")
	private LocalDate fechaInicio;
	@Column(name="cur_imagen")
	private String imagen;
	
	
	@Autowired
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="doc_id",nullable=false)
	private Docente docente;
	
	
	@Autowired
	@ManyToMany(mappedBy = "biblioteca",fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	
	@OneToMany(mappedBy = "curso",fetch = FetchType.LAZY, cascade= CascadeType.REMOVE)
	@Autowired
	private List<Unidad> unidades;

	
}
