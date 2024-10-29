package com.deluxe.service;

import java.util.List;

import com.deluxe.dto.CursoDTO;
import com.deluxe.entity.Curso;
import com.deluxe.enumerado.Role;

public interface ICursoService  extends IGenericCRUDService<Curso>{
	List<Curso> obtenerCursoPorDocente(Long id);
	List<Curso> obtenerCursoPorAlumno(Long id);
	List<CursoDTO> obtenerCursoPorDocenteDTO(Long id);
	List<Curso> obtenerCursosActivos();
	
	Curso obtenerCursoPorIds(Long userId, Long curId, String rol);
	
	void activarCurso(Long id);
}
