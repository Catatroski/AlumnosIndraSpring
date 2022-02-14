package com.formacionspring.alumnos.controllers;
import com.formacionspring.alumnos.entity.Alumnos;
import com.formacionspring.alumnos.service.AlumnosService;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AlumnosController {
	
	@Autowired
	private AlumnosService servicio;
	
	
	
	//------------------------------------------------
	
	//VISUALIZAR TODOS LOS CLIENTES
	
	
	@GetMapping("/alumnos")
	public List<Alumnos> cliente() {
		return servicio.findAll();
		
	}
	
	//------------------------------------------------
	

	//VISUALIZAR CLIENTES POR ID CON ERRORES
	
	
	@GetMapping("/alumnos/{id}")
	public ResponseEntity<?> alumnosShow(@PathVariable Long id){
		Alumnos alumnos = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumnos = servicio.findById(id);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar consulta a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (alumnos == null) {
			response.put("Mensaje, ","El alumno con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Alumnos>(alumnos	, HttpStatus.OK);
		
	}
	
	//-----------------------------------------------------------------------------------------------------------------------------
	
	
	
	//ACTUALIZAR ARTICULOS
	
	
	@PutMapping("/alumnos/{id}")
	public ResponseEntity<?> updateCliente(@RequestBody Alumnos alumnos, @PathVariable Long id) {
		
		Alumnos alumnoUpdated = servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumnoUpdated.setNombre(alumnos.getNombre());
			alumnoUpdated.setApellido(alumnos.getApellido());
			alumnoUpdated.setEmail(alumnos.getEmail());
			alumnoUpdated.setDireccion(alumnos.getDireccion());
			alumnoUpdated.setCp(alumnos.getCp());
			alumnoUpdated.setComunidad(alumnos.getComunidad());
			
			servicio.save(alumnoUpdated);
			
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El alumno ha sido actualizado :D");
		response.put("cliente", alumnoUpdated);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	// ELIMINAR ALUMNOS
	
	@DeleteMapping("/alumnos/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> deleteArticuloMostrado(@PathVariable  Long id) {
		Alumnos alumnoBorrado= servicio.findById(id);
		Map<String, Object> response = new HashMap<>();
		
		try {	
			
			if (alumnoBorrado == null) {
				response.put("Mensaje, ","El alumno con ID: ".concat(id.toString().concat(" no existe en la base de datos")));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			
			servicio.delete(id);
			
		}
			catch (DataAccessException e) {
			response.put("Mensaje", "Error al borrar en la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage())); 
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		
		response.put("Mensaje","El alumno ha sido borrado :D");
		response.put("cliente", alumnoBorrado);
		 
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	//INSERTAR ARTICULOS
	
	
	@PostMapping("/alumnos")
	public ResponseEntity<?> saveCliente(@RequestBody Alumnos alumnos) {
		Alumnos alumnoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			alumnoNew = servicio.save(alumnos);
		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar insert a la base de datos :(");
			response.put("Error", e.getMessage().concat("_").concat(e.getMostSpecificCause().getMessage()));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("Mensaje","El alumno ha sido creado correctamente :D");
		response.put("Cliente", alumnoNew);
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	
	
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//INSERTAR IMAGENES
	
	@PostMapping("/alumnos/uploads")
	public ResponseEntity<?> uploadImagen(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		
		Map<String, Object> response = new HashMap<>();
		Alumnos alumnos = servicio.findById(id);
		
		if(!archivo.isEmpty()) {
			//String nombreArchivo = archivo.getOriginalFilename();
			String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" "," ");
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("Mensaje", "Error al insertar imagen en la base de datos :(");
				response.put("Error", e.getMessage().concat("_").concat(e.getCause().getMessage())); 
			}
				
			String nombreFotoAnterior = alumnos.getImagen();
			
			if(nombreFotoAnterior !=null && nombreFotoAnterior.length()>0) {
				
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}	
			}
			
			alumnos.setImagen(nombreArchivo);
			
			servicio.save(alumnos);
			
			response.put("articulo", alumnos);
			response.put("mensaje", "Has subido correctamente la imagen :D " + nombreArchivo);
		} else {
			response.put("archivo", "No se pudo encontrar");
		}
		
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}

	
	//----------------------------------------------------------------------------------------------------------------------------
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	//DESCARGAR IMAGENES EN EL NAVEGADOR
	
	@GetMapping("/alumnos/uploads/imgs/{nombreImagen:.+}")
	public ResponseEntity<Resource> verImagen(@PathVariable String nombreImagen){
		Path rutaArchivo = Paths.get("uploads").resolve(nombreImagen).toAbsolutePath();
		
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException ("Error no se puede cargar la imagen: "+nombreImagen);
		}
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\""+recurso.getFilename()+"\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	
	//----------------------------------------------------------------------------------------------------------------------------
	
	
	
	
}