package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.dto.DoctorDTO;

public class DoctorDAO {

	Connection conn;
	
	public DoctorDAO(Connection conn) {
		this.conn = conn;
	}
	
	public DoctorDTO getReadDataOne(String id, String pw) {
		
		DoctorDTO dto = new DoctorDTO();
		String sql;
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {

			sql = "select doctorcode,id,name,doctorpartcode,"
					+ "(select codename from commcode where codeclassify = '01' and code = doctorpartcode) as doctorpartname "
					+ "from doctor where id=? and pwd=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setDoctorcode(rs.getString("doctorcode"));
				dto.setId(rs.getString("id"));
				dto.setName(rs.getString("name"));
				dto.setDoctorpartcode(rs.getString("doctorpartcode"));
				dto.setDoctorpartname(rs.getString("doctorpartname"));
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("DoctorDAO - getReadDataOne : " + e.toString());
		}

		return dto;
		
	}
	
}
