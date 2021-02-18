package com.dto;

public class PatientDTO {

	private String doctorcode;
	private String name;
	private String birth;
	private String gender;
	private String cellphone;
	private String patientcode;
	private String patientPagingText;
	
	public String getPatientPagingText() {
		return patientPagingText;
	}
	public void setPatientPagingText(String patientPagingText) {
		this.patientPagingText = patientPagingText;
	}
	public String getDoctorcode() {
		return doctorcode;
	}
	public void setDoctorcode(String doctorcode) {
		this.doctorcode = doctorcode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}
	public String getPatientcode() {
		return patientcode;
	}
	public void setPatientcode(String patientcode) {
		this.patientcode = patientcode;
	}
	
}
