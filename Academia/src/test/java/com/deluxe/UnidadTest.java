package com.deluxe;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.deluxe.entity.Curso;
import com.deluxe.entity.Docente;
import com.deluxe.entity.Unidad;
import com.deluxe.service.IUnidadService;

@SpringBootTest
class UnidadTest {
	
	@Autowired
	private IUnidadService unidadServiceImp;
	/*
	@Test
	void test() {
		fail("Not yet implemented");
	}

	@Test
	void getUnidadPorCurso() {
		List<Unidad> unidades = new ArrayList<>();
		try {
			unidades = unidadServiceImp.getUnidadesCurso(1l);
			for (int i = 0; i < unidades.size(); i++) {
				System.out.println(unidades.get(i).toString());
			}
		} catch (Exception e) {
			fail(e);
		}
	}
	
	*/
}
