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
	
	public List<PatientDTO> getReadData(String name, String birth, String gender, String cellphone, int start, int end,String drcode) {
		
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
			
			
			System.out.println("name.length"+name.length());
			System.out.println("gender.length"+gender.length());
			System.out.println("gender.length"+birth.length());
			System.out.println("cellphone.length"+cellphone.length());
			
			
			System.out.println("drcode"+drcode);
			System.out.println("name"+name);
			System.out.println("brith"+birth);
			System.out.println("gender"+gender);
			System.out.println("cellphone"+cellphone);
			
			System.out.println("name2"+name2);
			System.out.println("brith2"+birth2);
			System.out.println("gender2"+gender2);
			System.out.println("cellphone2"+cellphone2); 
			
			if (name.length() > 2 && birth2 == "%%" && gender2 == "%%" && cellphone2 =="%%" ){
				System.out.println("1@=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and   name like ?  order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql1"+sql);
				
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
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
			}else if (name2 == "%%" && birth.length() > 2 && gender2 == "%%" && cellphone2 =="%%" ){
				System.out.println("2@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and   birth  like ?  order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql2"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, birth2);
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
			}else if (name2 == "%%" && birth2 == "%%" && gender.length() > 2 && cellphone2 =="%%" ){
				System.out.println("3@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and   gender  like ?  order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql3"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, gender2);
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
			}else if (name2 == "%%" && birth2 == "%%" && gender2 == "%%" && cellphone.length() > 2 ){
				System.out.println("4@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and   cellphone  like ?  order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql4"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, cellphone2);
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
			}else if (name.length() > 2 &&  birth.length() > 2 &&  gender2 == "%%" && cellphone2 =="%%" ){
				System.out.println("5@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  name  like ? and   birth  like ?)   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql5"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, birth2);
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
			}else if (name2 == "%%" && birth.length() > 2 && gender.length() > 2 && cellphone2 =="%%" ){
				System.out.println("7@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  birth  like ? and   gender  like ?)   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, birth2);
				pstmt.setString(3, gender2);
				pstmt.setInt(4, start);
				pstmt.setInt(5, end);
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
			}else if (name2 == "%%" && birth2 == "%%" && gender.length()>2 && cellphone.length() >2 ){
				System.out.println("8@=================================");
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  gender  like ? and   cellphone  like ?)   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql7"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, gender2);
				pstmt.setString(3, cellphone2);
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
			}else if (name.length() > 2  && birth2 == "%%" && gender2== "%%" && cellphone.length() >2 ){
				System.out.println("#=================================");
				
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  name  like ? and   cellphone  like ?)   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				System.out.println("sql8"+sql);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, cellphone2);
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
			}else if (name.length() > 2   && birth.length() > 2 && gender.length() > 2 && cellphone2 =="%%" ){
				System.out.println("2#=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  name  like ? and  birth  like ?  and   gender  like ?)   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql9"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, birth2);
				pstmt.setString(4, gender2);
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
			}else if (name2 == "%%" && birth.length() > 2 && gender.length() > 2 && cellphone.length() > 2 ){
				System.out.println("3#=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  (  birth  like ?  and   gender  like ? and cellphone  like ? )   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql10"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, birth2);
				pstmt.setString(3, gender2);
				pstmt.setString(4, cellphone2);
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
			}else if (name.length() > 2 && birth.length() > 2 && gender2 == "%%" && cellphone.length() > 2 ){
				System.out.println("4#=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  ( name like ? and   birth  like ?   and cellphone  like ? )   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql11"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, birth2);
				pstmt.setString(4, cellphone2);
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
			}else if (name.length() > 2 && birth2 == "%%" && gender.length() > 2 && cellphone.length() > 2 ){
				System.out.println("5#=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  ( name like ? and   gender  like ?   and cellphone  like ? )   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql12"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, gender2);
				pstmt.setString(4, cellphone2);
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
			}else if (name.length() > 2 && birth.length() > 2 && gender.length() > 2 && cellphone.length() > 2 ){
				System.out.println("6#=================================");

				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  ( name like ? and   birth  like ?   and gender  like ? and cellphone  like ? )   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				pstmt = conn.prepareStatement(sql);
				System.out.println("sql14"+sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, birth2);
				pstmt.setString(4, gender2);
				pstmt.setString(5, cellphone2);
				pstmt.setInt(6, start);
				pstmt.setInt(7, end);
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
			} else {
				
				sql = "select * from "
						+ "(select rownum rnum, data.* from "
						+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
						+ "from patient where  doctorcode = ? and  ( name like ? and   birth  like ?   and gender  like ?  and cellphone  like ? )   order by patientcode desc) data) "
						+ "where rnum >= ? and rnum <= ?";
				
				System.out.println("sql######"+sql);
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, drcode);
				pstmt.setString(2, name2);
				pstmt.setString(3, birth2);
				pstmt.setString(4, gender2);
				pstmt.setString(5, cellphone2);
				pstmt.setInt(6, start);
				pstmt.setInt(7, end);
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
			
			/*sql = "select * from "
			+ "(select rownum rnum, data.* from "
			+ "(select doctorcode,name,(substr(birth,1,4) || '-' || substr(birth,5,2) || '-' || substr(birth,7,2)) as birth,gender,cellphone,patientcode "
			+ "from patient where name like ? or birth like ? or gender like ? or cellphone like ? order by patientcode desc) data) "
			+ "where rnum >= ? and rnum <= ?";
			pstmt = conn.prepareStatement(sql);
			System.out.println("sql14"+sql);
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
			pstmt.close();*/
			
			
			
			
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
