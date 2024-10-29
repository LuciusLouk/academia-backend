package com.deluxe.entity;


import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Table(name="SECCIONES")
@Data @AllArgsConstructor @NoArgsConstructor
public class Seccion {

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="sec_id")
	private Long id;
	private String titulo;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name="uni_id",nullable=false)
    @JsonIgnoreProperties("secciones")
	private Unidad unidad;
	
	/*
    @OneToMany(mappedBy = "seccion", cascade = CascadeType.REMOVE)
    private List<Archivo> archivos;*/
}
