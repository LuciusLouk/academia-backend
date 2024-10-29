package com.deluxe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Seccion;

public interface ISeccionRepository  extends JpaRepository<Seccion, Long>{
	@Query("SELECT s FROM Seccion s WHERE s.unidad.id= :id")
    public List<Seccion> findByUnidadId(@Param("id") Long id);
}
