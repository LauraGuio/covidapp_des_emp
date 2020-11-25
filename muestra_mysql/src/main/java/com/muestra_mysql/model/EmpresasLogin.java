package com.muestra_mysql.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the empresas_logins database table.
 * 
 */
@Entity
@Table(name = "empresas_logins")
public class EmpresasLogin implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "EmpresasLogin.FIND_ALL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_empresa_login")
	private Integer idEmpresaLogin;

	private int estado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_cancelado")
	private Date fechaRetiro;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;

	@Column(name = "id_empresa")
	private java.math.BigInteger idEmpresa;

	@Column(name = "id_login")
	private java.math.BigInteger idLogin;

	public EmpresasLogin() {
	}

	public Integer getIdEmpresaLogin() {
		return idEmpresaLogin;
	}

	public void setIdEmpresaLogin(Integer idEmpresaLogin) {
		this.idEmpresaLogin = idEmpresaLogin;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Date getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(Date fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public java.math.BigInteger getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(java.math.BigInteger idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public java.math.BigInteger getIdLogin() {
		return idLogin;
	}

	public void setIdLogin(java.math.BigInteger idLogin) {
		this.idLogin = idLogin;
	}

}