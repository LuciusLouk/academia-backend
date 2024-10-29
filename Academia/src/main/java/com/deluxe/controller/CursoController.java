package com.deluxe.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deluxe.entity.Alumno;
import com.deluxe.entity.Curso;
import com.deluxe.service.IAlumnoService;
import com.deluxe.service.ICursoService;
import com.deluxe.service.IDocenteService;
import com.deluxe.service.IUsuarioService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class CursoController {

	@Autowired
	IAlumnoService alumnoService;
	@Autowired
	IDocenteService docenteService;
	
	@Autowired
	ICursoService cursoServiceImp;
	
	@Autowired
	IUsuarioService usuarioServiceImp;

	@PostMapping(value="curso")
	public ResponseEntity<Map<String, String>> registrarCurso(@RequestBody Curso curso) {

		Map<String, String> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());

		
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario
	    
	    if(idUsuarioAutenticado!=curso.getDocente().getId()||!roles.get(0).equals("DOCENTE")) {

		    response.put("message", "NO se cambio el estado del curso");
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	    }
		
		cursoServiceImp.setObject(curso);
	    response.put("message", "Se cambio el estado del curso");
	    return ResponseEntity.ok(response);
	}
	
	
	@PostMapping(value="curso/modificar/")
	public ResponseEntity<Map<String, String>> modificarCurso(@RequestBody Curso curso){
		Map<String, String> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());

		
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    // Validar que el curso le pertenezca al usuario
	    Curso cursoAux = cursoServiceImp.obtenerCursoPorIds(idUsuarioAutenticado, curso.getId(), roles.get(0));
	    if(cursoAux==null) {
	    	response.put("message", "NO se modifico el curso");
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
	    }
	    
	    cursoServiceImp.modObject(curso); 
	    response.put("message", "Curso modificado");
	    return ResponseEntity.ok(response);
	}
	
	@GetMapping(value="curso/{id}")
	public ResponseEntity<Curso> obtenerCurso(@PathVariable(value="id")Long cursoId){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());

		
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    // Validar que el curso le pertenezca al usuario
	    Curso curso = cursoServiceImp.obtenerCursoPorIds(idUsuarioAutenticado, cursoId, roles.get(0));

	    if(curso == null) {
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }
		//return cursoServiceImp.getByID(id);
		return ResponseEntity.ok(curso);
	}
	
	@GetMapping(value="auth/cursoventa/{id}")
	public ResponseEntity<Curso> obtenerCursoVenta(@PathVariable(value="id")Long id){
	    Curso curso = cursoServiceImp.getByID(id);

	    if(curso == null) {
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }
		return ResponseEntity.ok(curso);
	}
	
	@GetMapping(value="curso/alumno/{id}")
	public ResponseEntity<List<Curso>> obtenerCursosAlumno(@PathVariable(value="id")Long id){
		// Obtener el ID del alumno autenticado desde el token JWT
	    //System.out.println(SecurityContextHolder.getContext());
		//Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario
	    
	    // Validar que el ID de la URL es el mismo que el ID del token
	   
	    if(!id.equals(idUsuarioAutenticado)){
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Retorna 403 Forbidden si los IDs no coinciden
	    }
	    
	    List<Curso> cursos = cursoServiceImp.obtenerCursoPorAlumno(id);
	    return ResponseEntity.ok(cursos);
	}
	
	
	@GetMapping(value="curso/docente/{id}")
	public ResponseEntity<List<Curso>> obtenerCursosDocente(@PathVariable(value="id")Long id){
		// Obtener el ID del docente autenticado desde el token JWT
	    //System.out.println(SecurityContextHolder.getContext());
		//Authentication authentication = (Authentication) SecurityContextHolder.getContext().getAuthentication();
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    // Validar que el ID de la URL es el mismo que el ID del token
	   
	    if(!id.equals(idUsuarioAutenticado)){
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null); // Retorna 403 Forbidden si los IDs no coinciden
	    }
	    
	    List<Curso> cursos = cursoServiceImp.obtenerCursoPorDocente(id);
	    return ResponseEntity.ok(cursos);
	}
	
	@GetMapping(value="cursos/activos")
	public List<Curso> obtenerCursosActivos(){
		
		return cursoServiceImp.obtenerCursosActivos();
	}
	
	
	@GetMapping(value="curso/estado/{id}")
	public ResponseEntity<Map<String,String>> cambiarEstadoCurso(@PathVariable(value="id")Long id) {
		Map<String, String> response = new HashMap<>();
		cursoServiceImp.activarCurso(id);
		response.put("message", "Curso modificado");
	    return ResponseEntity.ok(response);
	}
	

	@GetMapping(value="auth/comprar/{curId}")
	public ResponseEntity<Boolean> puedeComprar(@PathVariable(value="curId")Long curId){
		boolean puedeComprar=false;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    System.out.println("usuario logeado "+idUsuarioAutenticado);
	    if(roles.get(0).equals("DOCENTE")){
			return ResponseEntity.ok(false);	
		}else {
			Alumno alumno = alumnoService.getByID(idUsuarioAutenticado);
			puedeComprar = true;
			for (Curso b : alumno.getBiblioteca()) {
				if(b.getId()==curId)
					puedeComprar=false;
			}
		}
		return ResponseEntity.ok(puedeComprar);	
	}
	
	
	
	@PostMapping(value="registroAlumnoCurso/{aluId}&{curId}")
	public ResponseEntity<String> registroAlumnoCurso(@PathVariable(value="aluId")Long aluId, @PathVariable(value="curId")Long curId){
		
		alumnoService.registrarAlumnoCurso(aluId, curId);
		return ResponseEntity.ok("Registrado");
	}
	
	
	
	
	@GetMapping(value="curso/eliminar/{id}")
	public ResponseEntity<Map<String,String>> eliminarCurso(@PathVariable(value="id")Long cursoId){
		Map<String, String> response = new HashMap<>();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Obtener todos los roles del usuario
		List<String> roles = authentication.getAuthorities().stream()
		                 .map(GrantedAuthority::getAuthority)
		                 .collect(Collectors.toList());
		
		// Obtener el ID del usuario autenticado desde el token JWT
	    String username = SecurityContextHolder.getContext().getAuthentication().getName(); // El nombre de usuario del token JWT
	    Long idUsuarioAutenticado = usuarioServiceImp.getUsuarioValidation(username).getId(); // Obtener ID del usuario por nombre de usuario

	    // Validar que el curso le pertenezca al usuario
	    Curso curso = cursoServiceImp.obtenerCursoPorIds(idUsuarioAutenticado, cursoId, roles.get(0));
	    
	    if(curso == null || !roles.get(0).equals("DOCENTE")) {
	    	return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
	    }
	    
	    cursoServiceImp.delObject(cursoId);
		response.put("message", "Curso Eliminado");
	    return ResponseEntity.ok(response);
	}
	
}
