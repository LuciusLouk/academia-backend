package com.deluxe.service;

import java.util.List;

import com.deluxe.entity.Seccion;

public interface ISeccionService extends IGenericCRUDService<Seccion>{

	public List<Seccion> getSeccionesDeUnidad(Long idUnidad);
}
