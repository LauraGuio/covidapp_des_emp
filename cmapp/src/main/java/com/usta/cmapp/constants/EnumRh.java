package com.usta.cmapp.constants;

public enum EnumRh {
	ABPOS("AB+", "ABPOS"), ABNEG("AB-", "ABNEG"), APOS("A+", "APOS"), ANEG("A-", "ANEG"), BPOS("B+", "BPOS"),
	BNEG("B-", "BNEG"), OPOS("O+", "OPOS"), ONEG("O-", "ONEG");

	private String id;
	private String desc;

	private EnumRh(String id, String desc) {
		this.id = id;
		this.desc = desc;
	}

	public String getId() {
		return id;
	}

	public String getDesc() {
		return desc;
	}

}
