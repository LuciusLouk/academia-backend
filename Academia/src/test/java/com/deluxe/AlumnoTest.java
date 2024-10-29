package com.deluxe;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deluxe.entity.Alumno;
import com.deluxe.repository.IAlumnoRepository;

@SpringBootTest
class AlumnoTest {
/*
	@Autowired
	IAlumnoRepository alumnoRepository;
	
	/*
	
	@Test
	void altaTest() {
		Alumno alumno = new Alumno("Argentina","Lucas","Luis", null);
		alumno.setEmail("email@gmail.com");
		alumno.setNombreUsuario("Louk");
		alumno.setPassword("asdasd");
		alumno.setEstado(true);
		alumno.setFechaCreacion(LocalDate.now());
		alumno.setTelefono("3884668987");
		
		
		try {
			alumnoRepository.save(alumno);
			} catch (Exception e) {
			// TODO: handle exception
			fail("Not yet implemented");
		}
		
	}
*/
}
