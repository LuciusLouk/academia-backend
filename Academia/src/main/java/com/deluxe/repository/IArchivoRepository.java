package com.deluxe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deluxe.entity.Archivo;

public interface IArchivoRepository extends JpaRepository<Archivo, Long>{

	@Query("SELECT a FROM Archivo a WHERE a.seccion.id= :id")
    public List<Archivo> findBySeccionId(@Param("id") Long id);
}
