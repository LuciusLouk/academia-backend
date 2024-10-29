package com.deluxe.util;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUpload {

	private final Path rootLocation = Paths.get("src", "main", "resources", "static", "images");
	/**
	 * 
	 * @param tipo (imagen, pdf etc)
	 * @return Obtiene la ruta donde se guardará el archivo dentro del proyecto spring
	 */
	public Path obtenerRutaDeGuardado(char tipo) {
		/****** Genero un nombre único para el archivo que se guardará en la ruta ********/
		// Obtener la fecha y hora actual
        LocalDateTime now = LocalDateTime.now();
        
        // Formatear la fecha y hora actual como una cadena
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String formattedDateTime = now.format(formatter);
        
        // Generar un identificador único aleatorio
        String uniqueID = UUID.randomUUID().toString();
        
        
        /********************/
        switch(tipo) {
	        case 'a':{
	            // Concatenar la fecha y hora formateadas con el identificador único
	            String uniqueName = formattedDateTime + "_" + uniqueID + ".png";
	        	 // Crear una ruta para la carpeta donde se almacenarán los archivos
	            Path directorioImagenes = Paths.get("src", "main", "resources", "static", "images");
	            // Obtener la ruta completa del archivo
	            return directorioImagenes.resolve(uniqueName);
	        }
	        case 'b':{
	        	 // Concatenar la fecha y hora formateadas con el identificador único
	            String uniqueName = formattedDateTime + "_" + uniqueID + ".pdf";
	        	 // Crear una ruta para la carpeta donde se almacenarán los archivos
	            Path directorioImagenes = Paths.get("src", "main", "resources", "static", "images");
	            // Obtener la ruta completa del archivo
	            return directorioImagenes.resolve(uniqueName);
	        }
	        case 'c':{
	        	 // Concatenar la fecha y hora formateadas con el identificador único
	            String uniqueName = formattedDateTime + "_" + uniqueID + ".mp4";
	        	 // Crear una ruta para la carpeta donde se almacenarán los archivos
	            Path directorioImagenes = Paths.get("src", "main", "resources", "static", "images");
	            // Obtener la ruta completa del archivo
	            return directorioImagenes.resolve(uniqueName);
	        }
	        default:return null;
        }
        
       
	}
	
	/**
	 * @param base64
	 * @return Devuelve un texto en base 64 sin encabezado
	 */
	public String obtenerBase64SinEncabezado(String base64) {

        String base64SinEncabezado = "";
		int indexComa = base64.indexOf(","); // Encuentra la posición de la coma
        if (indexComa != -1) {
            base64SinEncabezado = base64.substring(indexComa + 1); // Obtiene la subcadena después de la coma

        } else {
            // Manejo de caso donde no se encuentra la coma
            base64SinEncabezado = base64;
        }
		return base64SinEncabezado;
	}
	
	/**
	 * Establece la ruta de la imagen que será enviada al front 
	 * @param imagen
	 * @return
	 */
	public String cargarImagen(String imagen) {
		String ruta = imagen.replace("src\\main\\resources\\static\\images\\", "http://localhost:8080/files/");

	    return ruta;
	}
}
