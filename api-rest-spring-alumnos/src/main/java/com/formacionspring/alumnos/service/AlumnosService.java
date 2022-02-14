package com.formacionspring.alumnos.service;

import java.util.List;

import com.formacionspring.alumnos.entity.Alumnos;
import com.formacionspring.alumnos.entity.Comunidad;


public interface AlumnosService {
	
	//GUARDAR EN ARRAYLIST EL ARTICULO
	public List<Alumnos> findAll();
	
	//BUSCAR ID
	public Alumnos findById(Long id);
	
	//BORAR ARTICULO
	public void delete (Long id);
	
	//GUARDAR ARTICULO PARA ACTUALIZAR
	public Alumnos save(Alumnos alumno);
	
	//BUSCAR COMUNIDAD 
	public List<Comunidad> findAllComunidades();

}