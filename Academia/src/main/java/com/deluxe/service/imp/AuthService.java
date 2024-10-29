package com.deluxe.service.imp;

import java.time.LocalDate;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Alumno;
import com.deluxe.entity.Docente;
import com.deluxe.enumerado.Role;
import com.deluxe.jwt.JwtService;
import com.deluxe.model.AuthResponse;
import com.deluxe.model.LoginRequest;
import com.deluxe.model.RegisterAlumnoRequest;
import com.deluxe.model.RegisterDocenteRequest;
import com.deluxe.repository.IAlumnoRepository;
import com.deluxe.repository.IDocenteRepository;
import com.deluxe.service.IUsuarioService;

import jakarta.persistence.Column;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final IAlumnoRepository alumnoRepository;
	private final IDocenteRepository docenteRepository;
	private final JwtService jwtService;

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final IUsuarioService usuarioServiceImp;
	
	
	public AuthResponse login(LoginRequest request) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));	
		UserDetails user = usuarioServiceImp.getUsuarioValidation(request.getUsername());
		String token = jwtService.getToken(user);
		return AuthResponse.builder()
				.token(token)
				.build();
	}
	
	public AuthResponse registerAlumno(RegisterAlumnoRequest request) {
		//Creo el usuaro a registrar
		Alumno alumno = new Alumno();
		alumno.setEmail(request.getEmail());
		alumno.setPassword(passwordEncoder.encode(request.getPassword()));
		alumno.setRol(Role.ALUMNO);
		alumno.setFechaCreacion(LocalDate.now());
		alumno.setEstado(true);
		alumno.setNombreUsuario(request.getNombreUsuario());
		alumno.setTelefono(request.getTelefono());
		
		alumno.setPais(request.getPais());
		alumno.setApellido(request.getApellido());
		alumno.setNombre(request.getNombre());
		
		alumnoRepository.save(alumno);		
		return AuthResponse.builder()
				.token(jwtService.getToken(alumno))
				.build();
	}
	
	public AuthResponse registerDocente(RegisterDocenteRequest request) {
		//Creo el docente a registrar
		Docente docente = new Docente();
		docente.setEmail(request.getEmail());
		docente.setPassword(passwordEncoder.encode(request.getPassword()));
		docente.setRol(Role.DOCENTE);
		docente.setFechaCreacion(LocalDate.now());
		docente.setEstado(true);
		docente.setNombreUsuario(request.getNombreUsuario());
		docente.setTelefono(request.getTelefono());
		
		docente.setPais(request.getPais());
		docente.setApellido(request.getApellido());
		docente.setNombre(request.getNombre());
		
		docente.setDni(request.getDni());
		docente.setTipoDni(request.getTipoDni());
		docente.setHabilitado(true);
		
		
		docenteRepository.save(docente);		
		return AuthResponse.builder()
				.token(jwtService.getToken(docente))
				.build();
	}
}
