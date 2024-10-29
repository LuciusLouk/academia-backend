package com.deluxe.service.imp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.deluxe.dto.CursoDTO;
import com.deluxe.entity.Alumno;
import com.deluxe.entity.Curso;
import com.deluxe.entity.Docente;
import com.deluxe.entity.Unidad;
import com.deluxe.enumerado.Role;
import com.deluxe.mapper.ICursoMapper;
import com.deluxe.repository.IAlumnoRepository;
import com.deluxe.repository.ICursoRepository;
import com.deluxe.repository.IDocenteRepository;
import com.deluxe.service.ICursoService;
import com.deluxe.service.IUnidadService;
import com.deluxe.util.FileUpload;

@Service
public class CursoServiceImp implements ICursoService {

	@Autowired
	ICursoRepository cursoRepository;
	@Autowired
	IAlumnoRepository alumnoRepository;
	ICursoMapper cursoMapperImp;
	
	@Autowired
	IUnidadService unidadServiceImp;
	@Autowired
    IDocenteRepository docenteRepository;

	private final Path rootLocation = Paths.get("src", "main", "resources", "static", "images");
	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = cursoRepository.findAll();
		for (Curso curso : cursos) {

			curso.setImagen(FileUpload.cargarImagen(curso.getImagen()));
			curso.setDocente(new Docente());

			curso.setAlumnos(new ArrayList<>());
		}	
		return cursos;
	}

	@Override
	public Curso getByID(Long id) {
		Curso c = cursoRepository.findById(id).orElse(null);
		c.setImagen(FileUpload.cargarImagen(c.getImagen()));
		c.setDocente(null);

		c.setAlumnos(new ArrayList<>());
		return c;
	}

	@Override
	public Curso setObject(Curso o) {
		//Cliente trae como foto de perfil un texto en base64
				/******************/
				//Antes de guardar debo cambiar el base64 por la ruta donde se guardará la imagen
				String base64=o.getImagen();
				//decodifico el texto en base64 a bytes (procuro quitarle el encabezado)
				byte[] bytesDecodificados = Base64.decodeBase64(FileUpload.obtenerBase64SinEncabezado(base64));
				
				//Convierto el texto decodificado en un string ?????????? no se usa?
		        //String textoDecodificado = new String(bytesDecodificados);
		        /*****************/
		        
		        /******* Crear la imagen en proyecto *********/
				
				
		        Path rutaCompleta = FileUpload.obtenerRutaDeGuardado('a');
		        try (OutputStream stream = Files.newOutputStream(rutaCompleta)) {
		            stream.write(bytesDecodificados);
		        } catch (IOException e) {
		            System.err.println("Error al guardar la imagen: " + e.getMessage());
		        }
		        /**************/
		        //establecer foto de perfil en cuenta y guardar cliente
		        o.setImagen(rutaCompleta.toString());
		return cursoRepository.save(o);
	}

	@Override
	public void modObject(Curso o) {
		if(o.getImagen().equals("")) {
			Curso curso = cursoRepository.findById(o.getId()).orElse(null);
			o.setImagen(curso.getImagen());		
		}else {
			//Antes de guardar debo cambiar el base64 por la ruta donde se guardará la imagen
			String base64=o.getImagen();
			//decodifico el texto en base64 a bytes (procuro quitarle el encabezado)
			byte[] bytesDecodificados = Base64.decodeBase64(FileUpload.obtenerBase64SinEncabezado(base64));
			
			//Convierto el texto decodificado en un string ?????????? no se usa?
	        //String textoDecodificado = new String(bytesDecodificados);
	        /*****************/
	        
	        /******* Crear la imagen en proyecto *********/
			
			
	        Path rutaCompleta = FileUpload.obtenerRutaDeGuardado('a');
	        try (OutputStream stream = Files.newOutputStream(rutaCompleta)) {
	            stream.write(bytesDecodificados);
	        } catch (IOException e) {
	            System.err.println("Error al guardar la imagen: " + e.getMessage());
	        }
	        /**************/
	        o.setImagen(rutaCompleta.toString());
		}
		cursoRepository.save(o);
	}

	@Override
	public void delObject(Long id) {
		//Curso curso = getByID(id);  // Obtén el curso por ID
	    
        // Elimina el curso
        //cursoRepository.delete(curso); 
	        
	}

	@Override
	public List<Curso> obtenerCursoPorDocente(Long id) {
		List<Curso> cursos = cursoRepository.findByDocenteId(id);
		for (Curso curso : cursos) {
			curso.setImagen(FileUpload.cargarImagen(curso.getImagen()));
			curso.setDocente(new Docente());
			curso.setUnidades(new ArrayList<>());
			curso.setAlumnos(new ArrayList<>());
		}	
		return cursos;
	}
	@Override
	public List<Curso> obtenerCursosActivos() {
		List<Curso> cursos =cursoRepository.findByActivo();
		for (Curso curso : cursos) {
			curso.setImagen(FileUpload.cargarImagen(curso.getImagen()));
			curso.setDocente(new Docente());

			curso.setUnidades(new ArrayList<>());
			curso.setAlumnos(new ArrayList<>());
		}	
		return cursos;
	}

	@Override
	public void activarCurso(Long id) {
		cursoRepository.updateActivo(id);
	}

	@Override
	public List<CursoDTO> obtenerCursoPorDocenteDTO(Long id) {
		return null;
		// TODO Auto-generated method stub
		//return cursoMapperImp.convertirListaCursoAlistaCursoDto(obtenerCursoPorDocente(id));
	}

	@Override
	public List<Curso> obtenerCursoPorAlumno(Long id) {
		// TODO Auto-generated method stub
		Alumno alumno = alumnoRepository.findById(id).orElse(null);
		List<Curso> cursos = alumno.getBiblioteca();
		for (Curso curso :cursos) {
			curso.setImagen(FileUpload.cargarImagen(curso.getImagen()));
			curso.setDocente(new Docente());
			curso.setUnidades(new ArrayList<>());

			curso.setAlumnos(new ArrayList<>());
		}
		return cursos;
	}

	@Override
	public Curso obtenerCursoPorIds(Long userId, Long curId, String rol) {
		Curso curso = new Curso();
		if(rol.equals("DOCENTE")) {
			curso = cursoRepository.findOneByDocenteId(userId, curId);
			
		}else if(rol.equals("ALUMNO")) {
			curso= cursoRepository.findOneByAlumnoId(userId, curId);
		}

		if(curso!=null) {
			curso.setImagen(FileUpload.cargarImagen(curso.getImagen()));
			curso.setDocente(new Docente());
			curso.setAlumnos(new ArrayList<>());	
			curso.setUnidades(new ArrayList<>());	}
		return curso;
	}
}
