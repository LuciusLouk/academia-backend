package com.deluxe.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.deluxe.service.IUsuarioService;
import com.deluxe.service.imp.UsuarioServiceImp;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidationUtil {
	@Autowired
    IUsuarioService usuarioServiceImp = new UsuarioServiceImp();
	
	public List<String> getRolAuth(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());
		return roles;
	}
	
	public Long getIdAuth () {
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT

		Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    return idUsuarioAutenticado;
	}
	
	
	
}
