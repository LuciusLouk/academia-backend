package com.deluxe.controller;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deluxe.entity.Archivo;
import com.deluxe.entity.Seccion;
import com.deluxe.service.IArchivoService;
import com.deluxe.service.ISeccionService;

@RestController
//@RequestMapping(value="files")
@CrossOrigin(origins = {"http://localhost:4200/"})
public class ArchivoController {
	
	@Autowired
	IArchivoService archivoServiceImp;
	
	@PostMapping(value="archivo")
	public void registrarArchivo(@RequestBody Archivo archivo) {
		if(archivo.getPath()==null) {
			archivo.setPath("-");
		}
		archivoServiceImp.setObject(archivo);
	}

	@GetMapping(value="files/archivos/{id}")
	public List<Archivo> obtenerArchivos(@PathVariable(value="id")Long id){
		return archivoServiceImp.getArchivosDeSeccion(id);
	}
	
	@GetMapping(value="archivo/eliminar/{id}")
	public void eliminarArchivo(@PathVariable(value="id")Long id){
		archivoServiceImp.delObject(id);
	}
	
	private final Path rootLocation = Paths.get("src", "main", "resources", "static", "images");	

    @GetMapping(value="files/{filename:.+}")
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                String contentType = "application/octet-stream"; // Default to binary stream
                if (filename.endsWith(".jpg") || filename.endsWith(".jpeg") || filename.endsWith(".png")) {
                    contentType = "image/jpeg"; // or "image/png"
                } else if (filename.endsWith(".pdf")) {
                    contentType = "application/pdf";
                } else if (filename.endsWith(".mp4")) {
                    contentType = "video/mp4";
                }
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .header(HttpHeaders.CONTENT_TYPE, contentType)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	
	
}
