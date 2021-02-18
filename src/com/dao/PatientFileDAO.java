package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.PatientFileDTO;

public class PatientFileDAO {
	
	Connection conn;
	
	public PatientFileDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<PatientFileDTO> getReadData(String patientcode, int start, int end) {
		
		List<PatientFileDTO> lists = new ArrayList<PatientFileDTO>();
		String sql;
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			
			sql = "select * from ( "
					+ "select rownum rnum, data.* from ( "
					+ "select (select name from patient where patientcode = patientfile.patientcode) as patientname, doctorcode,"
					+ "(select name from doctor where doctorcode = patientfile.doctorcode) as doctorname,wavfilename,txtfilename,wavfileloc,txtfileloc,insertdate,seq "
					+ "from patientfile where patientcode=? order by TO_NUMBER(seq) desc ) data ) where rnum>=? and rnum<=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patientcode);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				PatientFileDTO dto = new PatientFileDTO();
				
				dto.setPatientname(rs.getString("patientname"));
				dto.setDoctorcode(rs.getString("doctorcode"));
				dto.setDoctorname(rs.getString("doctorname"));
				dto.setWavfilename(rs.getString("wavfilename"));
				dto.setTxtfilename(rs.getString("txtfilename"));
				dto.setWavfileloc(rs.getString("wavfileloc"));
				dto.setTxtfileloc(rs.getString("txtfileloc"));
				dto.setInsertdate(rs.getString("insertdate"));
				dto.setSeq(rs.getString("seq"));
				
				lists.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientFileDAO - getReadData : " + e.toString());
		}
		
		return lists;
		
	}	
	
	public int insertPatientFileData(PatientFileDTO dto) {
		
		String sql;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			conn.setAutoCommit(false);
			
			sql = "insert into patientfile values(?,?,?,?,?,?,sysdate,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPatientcode());
			pstmt.setString(2, dto.getDoctorcode());
			pstmt.setString(3, dto.getWavfilename());
			pstmt.setString(4, dto.getTxtfilename());
			pstmt.setString(5, dto.getWavfileloc());
			pstmt.setString(6, dto.getTxtfileloc());
			pstmt.setString(7, dto.getSeq());
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientFileDAO - insertPatientFileData : " + e.toString());
			if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {}
		}
		
		return result;
		
	}
	
	public int getMaxSeq(String patientcode) {
		
		String sql;
		ResultSet rs;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			sql = "select nvl(max(TO_NUMBER(seq)),0) as max from patientfile where patientcode=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patientcode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {

				result = rs.getInt("max");
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientFileDAO - getMaxSeq : " + e.toString() + " ," + e.getMessage());
		}
		
		return result;
		
	}

	public int deleteData(String patientcode, String seq) {
		
		String sql;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			conn.setAutoCommit(false);
			
			sql = "delete from patientfile where patientcode=? and seq=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patientcode);
			pstmt.setString(2, seq);
			
			result = pstmt.executeUpdate();
			
			conn.commit();
			
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientFileDAO - deleteData : " + e.toString());
			if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {}
		}
		
		return result;
		
	}

	public int getDataCount(String patientcode) {
		
		String sql;
		ResultSet rs;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			sql = "select count(*) as count from patientfile where patientcode=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, patientcode);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("count");
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientFileDAO - getDataCount : " + e.toString());
		}
		
		return result;
		
	}

}
