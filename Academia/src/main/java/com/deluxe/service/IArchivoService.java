package com.deluxe.service;

import java.util.List;

import com.deluxe.entity.Archivo;

public interface IArchivoService extends IGenericCRUDService<Archivo>{

	public List<Archivo> getArchivosDeSeccion(Long idSeccion);
	
	public void eliminarArchivosDeSeccion(Long idSeccion);
}
