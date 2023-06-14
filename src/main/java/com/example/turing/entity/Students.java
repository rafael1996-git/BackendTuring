package com.example.turing.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "students")
public class Students implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NonNull
	@Column( nullable = false)
	private String nombre;
	@NonNull
	@Column( nullable = false)
	private String apellido;
	
	@NonNull
	@Column( nullable = false)
	private String email;
	@NonNull
	@JsonFormat(pattern = "dd-MM-yyyy")
	@Column( nullable = false)
	private Date fecha_nacimiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Students(Long id, String nombre, String apellido, String email, Date fecha_nacimiento) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public Students() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

}
