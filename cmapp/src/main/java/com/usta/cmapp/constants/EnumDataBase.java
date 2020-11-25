package com.usta.cmapp.constants;

public enum EnumDataBase {
	MYSQL(1, "Mysql"), POSTGRESQL(2, "Postgresql");

	private int id;
	private String descripcion;

	private EnumDataBase(int id, String descripcion) {
		this.id = id;
		this.descripcion = descripcion;
	}

	public int getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

}
