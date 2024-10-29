package com.deluxe.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Component
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="DOCENTES")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper=true)
public class Docente extends Usuario{

	@Column(name="doc_pais")
	private String pais;
	
	@Column(name="doc_apellido")
	private String apellido;
	
	@Column(name="doc_nombre")
	private String nombre;
	
	@Column(name="doc_tipo_dni")
	private String tipoDni;
	
	@Column(name="doc_dni")
	private String dni;
	
	@Column(name="doc_habilitado")
	private boolean habilitado;
	
	@OneToMany(mappedBy = "docente",fetch = FetchType.LAZY)
	private List<Curso> cursos;
	
}
