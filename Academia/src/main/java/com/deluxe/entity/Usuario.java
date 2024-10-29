package com.deluxe.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.deluxe.enumerado.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Component
@Inheritance( strategy = InheritanceType.JOINED)
@Table(name="USUARIOS")
@Data @AllArgsConstructor @NoArgsConstructor
public class Usuario implements UserDetails{
	//implements UserDetails
	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(name="usu_email")
	private String email;
	
	@Column(name="usu_password")
	private String password;
	
	@Column(name="usu_rol")
	private Role rol;
	
	@Column(name="usu_fecha_creacion")
	private LocalDate fechaCreacion;
	
	@Column(name="usu_estado")
	private boolean estado;
	
	@Column(name="usu_nombre_usuario")
	private String nombreUsuario;
	
	@Column(name="usu_tel")
	private String telefono;

	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String rol2 ="";
		if(rol!=null) {
			rol2=rol.toString();
		}
		return List.of(new SimpleGrantedAuthority(rol2));
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nombreUsuario;
	}
	
	/*
	@JsonIgnore
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return rol != null ? List.of(new SimpleGrantedAuthority(rol)) : List.of();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}*/
}
