package com.muestra_mysql.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The persistent class for the personas database table.
 * 
 */
@Entity
@Table(name = "personas")
public class Persona implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Persona.FIND_ALL";
	public static final String FIND_BY_DOCUMENT = "Persona.FIND_BY_DOCUMENT";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona")
	private Integer idPersona;

	private String documento;

	private String email;

	private int estatura;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;

	private String genero;

	@Column(name = "grupo_sanguineo")
	private String grupoSanguineo;

	@Column(name = "id_ciudad")
	private Integer idCiudad;

	@Column(name = "id_tipo_documento")
	private Integer idTipoDocumento;

	private int peso;

	@Column(name = "primer_apellido")
	private String primerApellido;

	@Column(name = "primer_nombre")
	private String primerNombre;

	@Column(name = "segundo_apellido")
	private String segundoApellido;

	@Column(name = "segundo_nombre")
	private String segundoNombre;

	private BigDecimal telefono;

	public Persona() {
	}

	public Integer getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(Integer idPersona) {
		this.idPersona = idPersona;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEstatura() {
		return this.estatura;
	}

	public void setEstatura(int estatura) {
		this.estatura = estatura;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getGrupoSanguineo() {
		return this.grupoSanguineo;
	}

	public void setGrupoSanguineo(String grupoSanguineo) {
		this.grupoSanguineo = grupoSanguineo;
	}

	public Integer getIdCiudad() {
		return this.idCiudad;
	}

	public void setIdCiudad(Integer idCiudad) {
		this.idCiudad = idCiudad;
	}

	public Integer getIdTipoDocumento() {
		return this.idTipoDocumento;
	}

	public void setIdTipoDocumento(Integer idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}

	public int getPeso() {
		return this.peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public BigDecimal getTelefono() {
		return this.telefono;
	}

	public void setTelefono(BigDecimal telefono) {
		this.telefono = telefono;
	}

}