package com.muestra_mysql.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tipos_documentos database table.
 * 
 */
@Entity
@Table(name = "tipos_documentos")
public class TiposDocumento implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "TiposDocumento.FIND_ALL";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_documento")
	private Integer idTipoDocumento;

	private String descripcion;

	public TiposDocumento() {
	}

	public Integer getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}