package com.deluxe.entity;


import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name="ARCHIVOS")
@Data @AllArgsConstructor @NoArgsConstructor
public class Archivo {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="arc_id")
	private Long id;
	private String nombre;
	private String path;
	private String tipo;
	

	@ManyToOne
	@JoinColumn(name="sec_id",nullable=false)
	private Seccion seccion;
	
}
