package com.formacionspring.alumnos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionspring.alumnos.dao.AlumnosDao;
import com.formacionspring.alumnos.entity.Alumnos;
import com.formacionspring.alumnos.entity.Comunidad;

@Service
public class AlumnosServiceImpl implements AlumnosService {

	@Autowired
	private AlumnosDao alumnoDao;
	

	
	@Override 	//ARRAY TODOS LOS ALUMNOS
	public List<Alumnos> findAll() {
		return (List<Alumnos>) alumnoDao.findAll();
	}

	
	
	@Override 	//ALUMNO BUSCADO POR ID
	public Alumnos findById(Long id) {
		return  alumnoDao.findById(id).orElse(null);
	}

	@Override	//ALUMNO BORRADO POR ID
	public void delete(Long id) {
		alumnoDao.deleteById(id);	
	}

	@Override	//METODO PARA GUARDAR ALUMNO
	public Alumnos save(Alumnos alumno) {
		return alumnoDao.save(alumno);
	}

	@Override	//ARRAY TODAS LAS COMUNIDADES
	public List<Comunidad> findAllComunidades() {
		return alumnoDao.findAllRegiones();
	}
	
}
