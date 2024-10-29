package com.deluxe.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Curso;
import com.deluxe.entity.Seccion;
import com.deluxe.entity.Unidad;
import com.deluxe.repository.IUnidadRepository;
import com.deluxe.service.ICursoService;
import com.deluxe.service.ISeccionService;
import com.deluxe.service.IUnidadService;
@Service
public class UnidadServiceImp implements IUnidadService {
	
	@Autowired
	IUnidadRepository unidadRepository;
	@Autowired
	ISeccionService seccionServiceImp;
	
	@Override
	public List<Unidad> getAll() {
		// TODO Auto-generated method stub
		return unidadRepository.findAll();
	}

	@Override
	public Unidad getByID(Long id) {
		// TODO Auto-generated method stub
		return unidadRepository.findById(id).orElse(null);
	}

	@Override
	public Unidad setObject(Unidad o) {
		// TODO Auto-generated method stub
		System.out.println(o.toString());
		return unidadRepository.save(o);
	}

	@Override
	public void modObject(Unidad o) {
		// TODO Auto-generated method stub
		unidadRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		Unidad unidad =unidadRepository.findById(id).orElse(null);
		for(Seccion s: unidad.getSecciones()) {
			seccionServiceImp.delObject(s.getId());
		}
		unidadRepository.delete(unidad);
	}

	@Override
	public List<Unidad> getUnidadesCurso(Long idCurso) {
		List<Unidad> unidades = unidadRepository.findByCursoId(idCurso);
		for (int i = 0; i < unidades.size(); i++) {
			unidades.get(i).setCurso(new Curso());
			unidades.get(i).setSecciones(new ArrayList<>());
		}
		return unidades;
	}

	@Override
	public Unidad getUnidad(Long cid, Long uid,Long did, String rol) {
		Unidad unidad = new Unidad();
		if(rol.equals("DOCENTE")) {
			unidad =unidadRepository.findByCursoAndUnidadAndDocenteId(cid, uid,did);
		}else if(rol.equals("ALUMNO")) {
			unidad =unidadRepository.findByCursoAndUnidadAndAlumnoId(cid, uid,did);
		}
		if(unidad!=null) {
			unidad.setCurso(new Curso());
			unidad.setSecciones(new ArrayList<>());
		}
		return unidad;
	}

}
