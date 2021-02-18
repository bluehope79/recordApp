package com.dto;

public class PatientFileDTO {

	private String patientcode;
	private String patientname;
	private String doctorcode;
	private String doctorname;
	private String wavfilename;
	private String txtfilename;
	private String wavfileloc;
	private String txtfileloc;
	private String insertdate;
	private String seq;
	private String patientPagingText;
	
	public String getPatientPagingText() {
		return patientPagingText;
	}
	public void setPatientPagingText(String patientPagingText) {
		this.patientPagingText = patientPagingText;
	}
	public String getPatientcode() {
		return patientcode;
	}
	public void setPatientcode(String patientcode) {
		this.patientcode = patientcode;
	}
	public String getPatientname() {
		return patientname;
	}
	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}
	public String getDoctorcode() {
		return doctorcode;
	}
	public void setDoctorcode(String doctorcode) {
		this.doctorcode = doctorcode;
	}
	public String getDoctorname() {
		return doctorname;
	}
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}
	public String getWavfilename() {
		return wavfilename;
	}
	public void setWavfilename(String wavfilename) {
		this.wavfilename = wavfilename;
	}
	public String getTxtfilename() {
		return txtfilename;
	}
	public void setTxtfilename(String txtfilename) {
		this.txtfilename = txtfilename;
	}
	public String getWavfileloc() {
		return wavfileloc;
	}
	public void setWavfileloc(String wavfileloc) {
		this.wavfileloc = wavfileloc;
	}
	public String getTxtfileloc() {
		return txtfileloc;
	}
	public void setTxtfileloc(String txtfileloc) {
		this.txtfileloc = txtfileloc;
	}
	public String getInsertdate() {
		return insertdate;
	}
	public void setInsertdate(String insertdate) {
		this.insertdate = insertdate;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	
}
