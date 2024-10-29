package com.deluxe.service.imp;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.AbstractFileResolvingResource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.deluxe.entity.Archivo;
import com.deluxe.entity.Seccion;
import com.deluxe.repository.IArchivoRepository;
import com.deluxe.service.IArchivoService;
import com.deluxe.util.FileUpload;

import jakarta.annotation.Resource;

@Service
public class ArchivoServiceImp implements IArchivoService {
	@Autowired
	IArchivoRepository archivoRepository;

	private final Path rootLocation = Paths.get("src", "main", "resources", "static", "images");
	@Override
	public List<Archivo> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Archivo getByID(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Archivo setObject(Archivo o) {
		if(!o.getTipo().equals("youtube")) {
			//Cliente trae como foto de perfil un texto en base64
			/******************/
			//Antes de guardar debo cambiar el base64 por la ruta donde se guardará la imagen
			String base64=o.getPath();
			//decodifico el texto en base64 a bytes (procuro quitarle el encabezado)
			byte[] bytesDecodificados = Base64.decodeBase64(FileUpload.obtenerBase64SinEncabezado(base64));
			
			//Convierto el texto decodificado en un string ?????????? no se usa?
	        //String textoDecodificado = new String(bytesDecodificados);
	        /*****************/
	        
	        /******* Crear la imagen en proyecto *********/
			
			//TODO Eliminar esta aberración y usar enumerado en el switch de obtenerRuta...
			char tipo;
			if(o.getTipo().equals("imagen"))
				tipo='a';
			else if(o.getTipo().equals("documento"))
				tipo='b';
			else if(o.getTipo().equals("video"))
				tipo='c';
			else
				tipo ='d';
	        Path rutaCompleta = FileUpload.obtenerRutaDeGuardado(tipo);
	        try (OutputStream stream = Files.newOutputStream(rutaCompleta)) {
	            stream.write(bytesDecodificados);
	            System.out.println("Imagen guardada en: " + rutaCompleta);
	        } catch (IOException e) {
	            System.err.println("Error al guardar la imagen: " + e.getMessage());
	        }
	        /**************/
	        //establecer foto de perfil en cuenta y guardar cliente
	        o.setPath(rutaCompleta.toString());
		}
		return archivoRepository.save(o);
	}

	@Override
	public void modObject(Archivo o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delObject(Long id) {
		archivoRepository.deleteById(id);
		
	}

	@Override
	public List<Archivo> getArchivosDeSeccion(Long idSeccion) {
		try {
			List<Archivo> archivos = archivoRepository.findBySeccionId(idSeccion);
			for (Archivo archivo : archivos) {
				archivo.setSeccion(new Seccion());
				
			}
			return archivos;
			
		} catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<Archivo>();
		}
	}

	@Override
	public void eliminarArchivosDeSeccion(Long idSeccion) {
		List<Archivo> archivos = archivoRepository.findBySeccionId(idSeccion);
		if(archivos.size()>0) {
			for (Archivo archivo : archivos) {
				archivoRepository.deleteById(archivo.getId());
				
			}
		}
	}
	
}
