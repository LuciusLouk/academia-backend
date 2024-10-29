package com.deluxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deluxe.entity.Seccion;
import com.deluxe.service.ISeccionService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
public class SeccionController {
	
	@Autowired
	ISeccionService seccionServiceImp;
	
	
	@PostMapping(value="/seccion")
	public void registrarSeccion(@RequestBody Seccion seccion) {
		seccionServiceImp.setObject(seccion);
	}
	
	@GetMapping(value="/secciones/{id}")
	public List<Seccion> obtenerSecciones(@PathVariable(value="id")Long id){
		System.out.println("entra a secciones");
		List<Seccion> secciones = seccionServiceImp.getSeccionesDeUnidad(id);
		System.out.println("secciones cargadas " + secciones);
		return secciones;
	}
	
	@GetMapping(value="/seccion/eliminar/{id}")
	public void eliminarSeccion(@PathVariable(value="id")Long id){
		seccionServiceImp.delObject(id);
	}
}
