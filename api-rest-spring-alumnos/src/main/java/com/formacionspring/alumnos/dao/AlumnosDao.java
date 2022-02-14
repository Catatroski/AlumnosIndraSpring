package com.formacionspring.alumnos.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.formacionspring.alumnos.entity.Alumnos;
import com.formacionspring.alumnos.entity.Comunidad;

@Repository
public interface AlumnosDao extends CrudRepository<Alumnos, Long> {
	@Query("from Comunidad")
	public List<Comunidad> findAllRegiones();
}
