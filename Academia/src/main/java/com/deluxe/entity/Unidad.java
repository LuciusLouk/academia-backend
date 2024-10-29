package com.deluxe.entity;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
@Table(name="UNIDADES")
@Data @AllArgsConstructor @NoArgsConstructor
public class Unidad {
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="uni_id")
	private Long id;
	
	@Column(name="uni_titulo")
	private String titulo;
	
	@Column(name="uni_descripcion")
	private String descripcion;	

	@Column(name="uni_activo")
	private boolean activo;
	
	@ManyToOne
	@JoinColumn(name="cur_id",nullable=false)
	private Curso curso;
	
	@OneToMany(mappedBy = "unidad",fetch = FetchType.LAZY)
	@Autowired
	private List<Seccion> secciones;


}
