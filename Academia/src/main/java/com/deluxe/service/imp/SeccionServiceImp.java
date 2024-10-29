package com.deluxe.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Archivo;
import com.deluxe.entity.Curso;
import com.deluxe.entity.Seccion;
import com.deluxe.entity.Unidad;
import com.deluxe.repository.ISeccionRepository;
import com.deluxe.service.IArchivoService;
import com.deluxe.service.ISeccionService;

@Service
public class SeccionServiceImp implements ISeccionService {

	@Autowired
	ISeccionRepository seccionRepository;
	@Autowired
	IArchivoService archivoService;
	
	@Override
	public List<Seccion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seccion getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Seccion setObject(Seccion o) {
		// TODO Auto-generated method stub
		return seccionRepository.save(o);
	}

	@Override
	public void modObject(Seccion o) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delObject(Long id) {
		archivoService.eliminarArchivosDeSeccion(id);
		seccionRepository.deleteById(id);
	}

	@Override
	public List<Seccion> getSeccionesDeUnidad(Long idUnidad) {
		List<Seccion> secciones = seccionRepository.findByUnidadId(idUnidad);
		
		for (Seccion seccion : secciones) {
			seccion.setUnidad(new Unidad());
			
		}
		
		return secciones;
	}

}
