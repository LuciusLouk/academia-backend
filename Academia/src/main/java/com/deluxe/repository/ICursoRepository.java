package com.deluxe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Archivo;
import com.deluxe.entity.Curso;

import jakarta.transaction.Transactional;

public interface ICursoRepository extends JpaRepository<Curso, Long> {

	@Query("SELECT c FROM Curso c WHERE c.docente.id= :id")
    public List<Curso> findByDocenteId(@Param("id") Long id);

	@Query("SELECT c FROM Curso c WHERE c.docente.id= :doc_id AND c.id= :cur_id")
    public Curso findOneByDocenteId(@Param("doc_id") Long docId, @Param("cur_id")Long curId);
	@Query("SELECT c FROM Curso c JOIN c.alumnos a WHERE a.id = :alumnoId AND c.id = :cursoId")
	Curso findOneByAlumnoId(@Param("alumnoId") Long alumnoId, @Param("cursoId") Long cursoId);

	
	@Transactional
	@Modifying
	@Query("UPDATE Curso c SET c.activo = CASE WHEN c.activo = true THEN false ELSE true END WHERE c.id = :id")
	public void updateActivo(@Param("id") Long id);
	

	@Query("SELECT c FROM Curso c WHERE c.activo= true")
    public List<Curso> findByActivo();
}
