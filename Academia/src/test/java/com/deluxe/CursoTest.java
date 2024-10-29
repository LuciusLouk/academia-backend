package com.deluxe;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deluxe.entity.Curso;
import com.deluxe.entity.Docente;
import com.deluxe.service.ICursoService;

@SpringBootTest
class CursoTest {
	/*
	@Autowired
	private ICursoService cursoServiceImpTest;
	
	@Test
	void test() {
		
	}
	/*
	@Test
	void cargaCurso() {
		Docente d =new Docente();
		d.setId(1l);
		Curso c = new Curso();
		c.setActivo(true);
		c.setCupo(100);
		c.setDocente(d);
		c.setDuracionMeses(12);
		c.setFechaInicio(LocalDate.now());
		c.setImagen("");
		c.setModalidad("virtual");
		c.setNombre("curso de prueba test");
		c.setPrecio(100);
		
		try {
			cursoServiceImpTest.setObject(c);
		} catch (Exception e) {
			fail("Not yet implemented");
		}
	}
	
	@Test
	void getCurso() {
		try {
			Curso c = cursoServiceImpTest.getByID(1l);
			System.out.println("curso obtenido");
			c.setDocente(new Docente());
			System.out.println(c.toString());
		} catch (Exception e) {
			fail(e);
		}
	}
	*/
	
}
