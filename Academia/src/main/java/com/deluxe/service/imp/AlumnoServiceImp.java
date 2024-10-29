package com.deluxe.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Alumno;
import com.deluxe.entity.Curso;
import com.deluxe.repository.IAlumnoRepository;
import com.deluxe.repository.ICursoRepository;
import com.deluxe.service.IAlumnoService;

@Service
public class AlumnoServiceImp implements IAlumnoService {

	@Autowired
	IAlumnoRepository alumnoRepository;
	@Autowired
	ICursoRepository cursoRepository;
	
	@Override
	public List<Alumno> getAll() {
		// TODO Auto-generated method stub
		return alumnoRepository.findAll();
	}

	@Override
	public Alumno getByID(Long id) {
		// TODO Auto-generated method stub
		return alumnoRepository.findById(id).orElse(null);
	}

	@Override
	public Alumno setObject(Alumno o) {
		// TODO Auto-generated method stub
		return alumnoRepository.save(o);
	}

	@Override
	public void modObject(Alumno o) {
		// TODO Auto-generated method stub
		alumnoRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		// TODO Auto-generated method stub
		alumnoRepository.delete(getByID(id));
	}

	@Override
	public void registrarAlumnoCurso(Long alumnoId, Long cursoId) {
		// TODO Auto-generated method stub
		Alumno alumno = new Alumno();
		alumno = alumnoRepository.findById(alumnoId).orElse(null);
		
		Curso curso = new Curso();
		curso = cursoRepository.findById(cursoId).orElse(curso);
		
		boolean inscripto = false;
		for (Curso c : alumno.getBiblioteca()) {
			if(c.getId()==cursoId) {
				inscripto=true;
			}
		}
		if(!inscripto) {
			alumno.getBiblioteca().add(curso);
			alumnoRepository.save(alumno);
		}
		//alumnoRepository.agregarCursoAAlumno(alumnoId, cursoId);
	}

}
