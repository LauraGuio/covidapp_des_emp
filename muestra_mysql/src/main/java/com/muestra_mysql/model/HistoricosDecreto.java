package com.muestra_mysql.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the historicos_decretos database table.
 * 
 */
@Entity
@Table(name = "historicos_decretos")
public class HistoricosDecreto implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "HistoricosDecreto.FIND_ALL";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_historico")
	private Integer idHistorico;

	private String descripcion;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_decreto")
	private Date fechaDecreto;

	@Column(name = "id_ciudad")
	private java.math.BigInteger idCiudad;

	@Column(name = "id_decreto")
	private java.math.BigInteger idDecreto;

	private String url;

	public HistoricosDecreto() {
	}

	public Integer getIdHistorico() {
		return this.idHistorico;
	}

	public void setIdHistorico(Integer idHistorico) {
		this.idHistorico = idHistorico;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaDecreto() {
		return this.fechaDecreto;
	}

	public void setFechaDecreto(Date fechaDecreto) {
		this.fechaDecreto = fechaDecreto;
	}

	public java.math.BigInteger getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(java.math.BigInteger idCiudad) {
		this.idCiudad = idCiudad;
	}

	public java.math.BigInteger getIdDecreto() {
		return this.idDecreto;
	}

	public void setIdDecreto(java.math.BigInteger idDecreto) {
		this.idDecreto = idDecreto;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}