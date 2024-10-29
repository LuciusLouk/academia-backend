package com.deluxe.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deluxe.entity.Curso;
import com.deluxe.entity.Unidad;
import com.deluxe.service.ICursoService;
import com.deluxe.service.ISeccionService;
import com.deluxe.service.IUnidadService;
import com.deluxe.service.IUsuarioService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class UnidadController {
	@Autowired
	IUnidadService unidadServiceImp;
	@Autowired
	IUsuarioService usuarioServiceImp;

	@Autowired
	ICursoService cursoServiceImp;
	@Autowired
	ISeccionService seccionServiceImp;
	
	@PostMapping(value="/unidad")
	public void registrarUnidad(@RequestBody Unidad unidad) {
		unidadServiceImp.setObject(unidad);
	}
	
	@GetMapping(value="curso/{cid}/unidad/{uid}")
	public ResponseEntity<Unidad> obtenerUnidad(@PathVariable(value="cid")Long cid,@PathVariable(value="uid")Long uid) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());

		
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario
	    
		
		Unidad unidad = unidadServiceImp.getUnidad(cid,uid,idUsuarioAutenticado,roles.get(0));
		
		if(unidad==null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
			unidad.setSecciones( seccionServiceImp.getSeccionesDeUnidad(unidad.getId()));
			return ResponseEntity.ok(unidad);
		}
	}
	
	@GetMapping(value="/unidades/{id}")
	public ResponseEntity<List<Unidad>> obtenerUnidades(@PathVariable(value="id")Long id){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());

		
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    // Validar que el curso le pertenezca al usuario
	    Curso curso = cursoServiceImp.obtenerCursoPorIds(idUsuarioAutenticado, id, roles.get(0));

	    if(curso == null) {
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }
		
		
		List<Unidad> unidades = unidadServiceImp.getUnidadesCurso(id);
		return ResponseEntity.ok(unidades);
	}
	@GetMapping(value="unidad/eliminar/{id}")
	public void eliminarUnidad(@PathVariable(value="id")Long id){
		unidadServiceImp.delObject(id);
	}
}
