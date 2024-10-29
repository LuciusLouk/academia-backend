package com.deluxe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Alumno;
import com.deluxe.entity.Curso;

public interface IAlumnoRepository extends JpaRepository<Alumno, Long> {
	@Modifying
	@Query(value = "INSERT INTO biblioteca (alu_id, cur_id) VALUES (:alumnoId, :cursoId) " +
	               "ON DUPLICATE KEY UPDATE cur_id = cur_id", nativeQuery = true)
	void agregarCursoAAlumno(@Param("alumnoId") Long alumnoId, @Param("cursoId") Long cursoId);

	
}
