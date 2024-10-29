package com.deluxe;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deluxe.entity.Docente;
import com.deluxe.service.IDocenteService;

@SpringBootTest
class DocenteTest {

	@Autowired
	IDocenteService docenteService;
	/*
	@Test
	void AltaTest() {
		Docente docente = new Docente("Argentina","Apellido Test","Nombre Test","DNI","41409949",false,null);
		docente.setEmail("email@gmail.com");
		docente.setNombreUsuario("Louk");
		docente.setPassword("asdasd");
		docente.setEstado(true);
		docente.setFechaCreacion(LocalDate.now());
		docente.setTelefono("3884668987");
		try {
			docenteService.setObject(docente);
		} catch (Exception e) {
			// TODO: handle exception
			fail("Error al cargar");
		}
	}
*/
}
