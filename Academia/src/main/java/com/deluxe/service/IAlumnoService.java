package com.deluxe.service;

import com.deluxe.entity.Alumno;

public interface IAlumnoService  extends IGenericCRUDService<Alumno>{

	public void registrarAlumnoCurso(Long alumnoId,Long cursoId);
}
