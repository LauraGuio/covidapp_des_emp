package com.muestra_mysql.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the departamentos database table.
 * 
 */
@Entity
@Table(name = "departamentos")
//@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM Departamento d")
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Departamento.FIND_ALL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_departamento")
	private Integer idDepartamento;

	private String nombre;

	public Departamento() {
	}

	public Integer getIdDepartamento() {
		return this.idDepartamento;
	}

	public void setIdDepartamento(Integer idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}