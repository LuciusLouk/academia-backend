package com.deluxe.controller;

import java.time.LocalDate;

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
import com.deluxe.entity.Docente;
import com.deluxe.entity.Usuario;
import com.deluxe.model.AuthResponse;
import com.deluxe.model.LoginRequest;
import com.deluxe.model.LoginResponse;
import com.deluxe.model.RegisterAlumnoRequest;
import com.deluxe.model.RegisterDocenteRequest;
import com.deluxe.service.IAlumnoService;
import com.deluxe.service.IDocenteService;
import com.deluxe.service.IUsuarioService;
import com.deluxe.service.imp.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequiredArgsConstructor
@RequestMapping("/auth")
public class LoginController {
	@Autowired
	IDocenteService docenteServiceIMP;
	
	@Autowired
	IAlumnoService alumnoServiceIMP;
	
	@Autowired
	IUsuarioService usuarioServiceImp;
	
	
	@Autowired
	Usuario usuario;
	
	private final AuthService authService;
	
	@PostMapping(value="login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest){
		
		
		return ResponseEntity.ok(authService.login(loginRequest));
	}
	
	@PostMapping(value="alumnoRegistro")
	public ResponseEntity<AuthResponse> registrarAlumno(@RequestBody RegisterAlumnoRequest request) {
			System.out.println("entra al registro alumno");
		return ResponseEntity.ok(authService.registerAlumno(request));
	}
	
	@PostMapping(value="docenteRegistro")
	public ResponseEntity<AuthResponse> registrarDocente(@RequestBody RegisterDocenteRequest request) {
			System.out.println("entra al registro docente");
		return ResponseEntity.ok(authService.registerDocente(request));
	}
	
	@GetMapping(value="prueba")
	public String prueba() {
		return "funciona";
	}
	
	
	
	
	/**
	 * Login antigup
	@PostMapping(value="/auth/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
		String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        
        System.out.println(loginRequest.toString());
        usuario = usuarioServiceImp.getUserLogin(username, password);
        
        LoginResponse response = new LoginResponse(
        		usuario.getId(),
        		usuario.getRol(),
        		usuario.isEstado(),
        		usuario.getNombreUsuario()
            );
        
        return new ResponseEntity<>(response,HttpStatus.OK);
        //return ResponseEntity.ok(authService.login(loginRequest));
	}*/
	
	


/*
	@PostMapping(value="/auth/docenteRegistro")
	public ResponseEntity<AuthResponse> registrarDocente(@RequestBody RegisterDocenteRequest request) {
		
		/*
		request.setEstado(true);
		request.setFechaCreacion(LocalDate.now());
		request.setHabilitado(true);
		*/
	/*
		return ResponseEntity.ok(authService.registerDocente(request));
	}
	*/
	
	
	
	
	
	
	
	

	
	
	

	/**
	 * Usado para validar usuarios repetidos en registro de usuarios
	 * @param email
	 * @return
	 */
	@GetMapping("/usuarios/{username}")
	public Boolean getUsuario(@PathVariable(value="username")String username) {
		Boolean result = true;
		try {
			result = usuarioServiceImp.getUsuarioValidation(username).equals(null);
		} catch (Exception e) {
			System.out.println(username);
			System.out.println("exception: "+ e);
		}
		
		return result;
	}
}
