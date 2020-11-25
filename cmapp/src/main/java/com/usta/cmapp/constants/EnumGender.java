package com.usta.cmapp.constants;

public enum EnumGender {
	FEMALE("FEMALE"), MALE("MALE"), OTHER("OTHER");

	private String desc;

	EnumGender(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}
}
