package com.muestra_postgres.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the empresa_login database table.
 * 
 */
@Entity
@Table(name = "empresa_login")
public class EmpresaLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "EmpresasLogin.FIND_ALL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa_login")
	private Integer idEmpresaLogin;

	private Integer estado;

	@Column(name = "fecha_creacion")
	private Timestamp fechaCreacion;

	@Column(name = "fecha_retiro")
	private Timestamp fechaRetiro;

	@Column(name = "id_empresa")
	private Integer idEmpresa;

	@Column(name = "id_login")
	private Integer idLogin;

	public EmpresaLogin() {
	}

	public Integer getIdEmpresaLogin() {
		return this.idEmpresaLogin;
	}

	public void setIdEmpresaLogin(Integer idEmpresaLogin) {
		this.idEmpresaLogin = idEmpresaLogin;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaRetiro() {
		return this.fechaRetiro;
	}

	public void setFechaRetiro(Timestamp fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public Integer getIdEmpresa() {
		return this.idEmpresa;
	}

	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Integer getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

}