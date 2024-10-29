package com.deluxe.entity;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@Entity
@Component
@PrimaryKeyJoinColumn(referencedColumnName="id")
@Table(name="ALUMNOS")
@Builder
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode(callSuper=true)
public class Alumno extends Usuario{

	@Column(name="alu_pais")
	private String pais;
	@Column(name="alu_apellido")
	private String apellido;
	@Column(name="alu_nombre")
	private String nombre;
	
	//*****************************************//
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="biblioteca",
			joinColumns = @JoinColumn(name="alu_id"),
			inverseJoinColumns =@JoinColumn(name="cur_id")
			)
	private List<Curso> biblioteca;

	
	
	
	

		
	
}
