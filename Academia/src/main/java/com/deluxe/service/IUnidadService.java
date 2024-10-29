package com.deluxe.service;

import java.util.List;

import com.deluxe.entity.Unidad;

public interface IUnidadService  extends IGenericCRUDService<Unidad>{
	
	public List<Unidad> getUnidadesCurso(Long idCurso);
	
	public Unidad getUnidad(Long cid, Long uid,Long did, String rol);
}
