package com.formacionspring.alumnos.entity;
import java.io.Serializable;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="alumnado")
public class Alumnos implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String nombre;
	
	@Column(nullable=false)
	private String apellido;
	
	@Column(unique=true)
	private String dni;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private int telefono;
	
	@Column(nullable=false)
	private String direccion;
	
	@Column(nullable=false)
	private int cp;
	
	private String imagen;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="comunidad")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Comunidad comunidad;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getCp() {
		return cp;
	}

	public void setCp(int cp) {
		this.cp = cp;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public Comunidad getComunidad() {
		return comunidad;
	}

	public void setComunidad(Comunidad comunidad) {
		this.comunidad = comunidad;
	}
	
	
	
	
	
	
	
}
