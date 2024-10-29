package com.deluxe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Unidad;
public interface IUnidadRepository  extends JpaRepository<Unidad, Long>{

	@Query("SELECT u FROM Unidad u WHERE u.curso.id= :id")
    public List<Unidad> findByCursoId(@Param("id") Long id);
	

	@Query("SELECT u FROM Unidad u WHERE u.curso.id= :cid AND u.id = :uid")
	public Unidad findByCursoAndUnidadId(@Param("cid")Long cid,@Param("uid")Long uid);

	@Query("SELECT u FROM Unidad u WHERE u.curso.id= :cid AND u.id = :uid AND u.curso.docente.id= :did")
	public Unidad findByCursoAndUnidadAndDocenteId(@Param("cid")Long cid,@Param("uid")Long uid,@Param("did")Long did);

	@Query("SELECT u FROM Unidad u JOIN u.curso.alumnos a WHERE u.curso.id= :cid AND u.id = :uid AND a.id= :did")
	public Unidad findByCursoAndUnidadAndAlumnoId(@Param("cid")Long cid,@Param("uid")Long uid,@Param("did")Long did);


}
