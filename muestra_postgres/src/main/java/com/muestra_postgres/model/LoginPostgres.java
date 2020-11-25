package com.muestra_postgres.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the login database table.
 * 
 */
@Entity
@Table(name="login")
public class LoginPostgres implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "LoginPostgres.FIND_ALL";

	public static final String FIND_USER_CREDENTIALS_POSTGRESQL = "LoginPostgres.FIND_USER_CREDENTIALS_POSTGRESQL";

	public static final String FIND_USER = "LoginPostgres.FIND_USER";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_login")
	private Integer idLogin;

	private String clave;

	private String usuario;

	public LoginPostgres() {
	}

	public Integer getIdLogin() {
		return this.idLogin;
	}

	public void setIdLogin(Integer idLogin) {
		this.idLogin = idLogin;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}