package com.dto;

public class DoctorDTO {

	private String doctorcode;
	private String id;
	private String pwd;
	private String name;
	private String doctorpartcode;
	private String doctorpartname;
	
	public String getDoctorpartname() {
		return doctorpartname;
	}
	public void setDoctorpartname(String doctorpartname) {
		this.doctorpartname = doctorpartname;
	}
	public String getDoctorcode() {
		return doctorcode;
	}
	public void setDoctorcode(String doctorcode) {
		this.doctorcode = doctorcode;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoctorpartcode() {
		return doctorpartcode;
	}
	public void setDoctorpartcode(String doctorpartcode) {
		this.doctorpartcode = doctorpartcode;
	}
	
}
