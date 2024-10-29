
package com.deluxe.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.deluxe.dto.CursoDTO;
import com.deluxe.entity.Curso;


//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ICursoMapper {

	/*
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nombre", target = "nombre")
	@Mapping(source = "duracionMeses", target = "duracionMeses")
	@Mapping(source = "precio", target = "precio")
	@Mapping(source = "cupo", target = "cupo")
	@Mapping(source = "cantidadEgresados", target = "cantidadEgresados")
	@Mapping(source = "descuento", target = "descuento")
	@Mapping(source = "modalidad", target = "modalidad")
	@Mapping(source = "activo", target = "activo")
	@Mapping(source = "fechaInicio", target = "fechaInicio")
	@Mapping(source = "imagen", target = "imagen")
	CursoDTO convertirMateriaAmateriaDto(Curso curso);

	
	//@Mapping(target = "domicilio", ignore = true)  // en este caso no lo uso porque muestro todos los atributos.
	Curso convertirMateriaDtoAmateria(CursoDTO cursoDto);
	
	List <CursoDTO> convertirListaCursoAlistaCursoDto(List<Curso>listaCursos);*/
}
