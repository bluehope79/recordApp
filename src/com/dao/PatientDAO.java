package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dto.PatientDTO;

public class PatientDAO {

	Connection conn;
	
	public PatientDAO(Connection conn) {
		this.conn = conn;
	}
	
	public List<PatientDTO> getReadData(String name, String birth, String gender, String cellphone, int start, int end) {
		
		List<PatientDTO> lists = new ArrayList<PatientDTO>();
		String sql;
		ResultSet rs;
		PreparedStatement pstmt;
		String name2, birth2, gender2, cellphone2 ;  
		Integer end2 = 200; 
		
		try { 
			name2 = "%" + name + "%"; 
			birth2 = "%" + birth + "%"; 				
			gender2 = "%" + gender + "%"; 
			cellphone2 = "%" + cellphone + "%"; 
			
			/*sql = "select * from "
					+ "(select rownum rnum, data.* from "
					+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
					+ "from patient where name like ? and birth like ? and gender like ? and cellphone like ? order by patientcode desc) data) "
					+ "where rnum >= ? and rnum <= ?";*/
			
			if (name != "" && birth == "" && gender == "" && cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql1"+sql);
				
				pstmt.setString(1, name);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth != "" && gender == "" && cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (birth like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql2"+sql);
				pstmt.setString(1, birth);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth == "" && gender != "" && cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (gender like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql3"+sql);
				pstmt.setString(1, gender);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth == "" && gender == "" && cellphone !="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (cellphone like cellphone )  "
						+ " order by patientcode desc) data)  "
						+ " where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql4"+sql);
				pstmt.setString(1, cellphone);
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" || birth != "" || gender == "" || cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and birth like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql5"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth != "" && gender != "" && cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (birth like ? and gender like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, birth);
				pstmt.setString(2, gender);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				System.out.println("sql56"+sql);
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth == "" && gender != "" && cellphone !="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (cellphone like ? and gender like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql7"+sql);
				pstmt.setString(1, gender);
				pstmt.setString(2, cellphone);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" && birth == "" && gender == "" && cellphone !="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and cellphone like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql8"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, cellphone);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" && birth != "" && gender != "" && cellphone =="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and birth like ?  and gender like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql9"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, gender);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name == "" && birth != "" && gender != "" && cellphone !="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (birth like ? and gender like ?  and cellphone like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql10"+sql);
				pstmt.setString(1, birth);
				pstmt.setString(2, gender);
				pstmt.setString(3, cellphone);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" && birth != "" && gender == "" && cellphone !="" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and birth like ?  and cellphone like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql11"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, cellphone);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" && birth == "" && gender != "" && cellphone != "" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and gender like ?  and cellphone like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql12"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, gender);
				pstmt.setString(3, cellphone);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			}else if (name != "" && birth != "" && gender != "" && cellphone != "" ){
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ " from patient where (name like ? and birth like ? and gender like ? and cellphone like ? )  "
						+ " order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql13"+sql);
				pstmt.setString(1, name);
				pstmt.setString(2, birth);
				pstmt.setString(3, gender);
				pstmt.setString(4, cellphone);
				pstmt.setInt(5, start);
				pstmt.setInt(6, end);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					
					PatientDTO dto = new PatientDTO();
					
					dto.setDoctorcode(rs.getString("doctorcode"));
					dto.setName(rs.getString("name"));
					dto.setBirth(rs.getString("birth"));
					dto.setGender(rs.getString("gender"));
					dto.setCellphone(rs.getString("cellphone"));
					dto.setPatientcode(rs.getString("patientcode"));
					
					lists.add(dto);
					
				}
				
				rs.close();
				pstmt.close();
			} 
			
			
			
		} catch(Exception e) {
			System.out.println("PatientDAO - getReadData : " + e.toString());
		}
		
		return lists;
		
	}
	
	public String getMaxPatientCode() {
		
		String sql;
		String maxCode = "";
		ResultSet rs;
		PreparedStatement pstmt;
		
		try {
			
			sql = "select max(patientcode) as max from patient";
			
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				maxCode = rs.getString("max");
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientDAO - getMaxPatientCode : " + e.toString());
		}
		
		return maxCode;
		
	}

	public int insertPatientData(PatientDTO dto) {

		String sql;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			conn.setAutoCommit(false);
			
			sql = "insert into patient values(?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getDoctorcode());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getBirth());
			pstmt.setString(4, dto.getGender());
			pstmt.setString(5, dto.getCellphone());
			pstmt.setString(6, dto.getPatientcode());

			result = pstmt.executeUpdate();
			
			conn.commit();
			
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientDAO - insertPatientData : " + e.toString());
			if(conn!=null) try{conn.rollback();}catch(SQLException sqle){}
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {}
		}
		
		return result;
		
	}

	public int getDataCount(String name, String birth, String gender,String cellphone) {

		String sql;
		ResultSet rs;
		int result = 0;
		PreparedStatement pstmt;
		
		try {
			
			name = "%" + name + "%"; 
			birth = "%" + birth + "%"; 
			gender = "%" + gender + "%"; 
			cellphone = "%" + cellphone + "%"; 
			
			sql = "select count(*) as count from patient where name like ? and birth like ? and gender like ? and cellphone like ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, birth);
			pstmt.setString(3, gender);
			pstmt.setString(4, cellphone);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				result = rs.getInt("count");
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch(Exception e) {
			System.out.println("PatientDAO - getDataCount : " + e.toString());
		}
		
		return result;
		
	}
}
