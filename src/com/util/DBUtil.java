package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	public static Connection getConnection() throws Exception{

		String url = "jdbc:oracle:thin:@127.0.0.1:1521:TestDB";

		String user = "naosoft";

		String password = "1234";

		return getConnection(url, user, password);

	}

	

	public static Connection getConnection(String url,String user,String password) throws Exception{

		Connection conn = null;

		// 3. 드라이버로딩

		Class.forName("oracle.jdbc.driver.OracleDriver");

		// 4.디비접속	

		conn = DriverManager.getConnection(url, user, password);

		return conn;

	}

	// close 하는 메소드

	public static void close(Connection conn, PreparedStatement ps){

		if (ps != null) {

			try {

				ps.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		if (conn != null) {

			try {

				conn.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

	}

	public static void close(Connection conn, PreparedStatement ps, ResultSet rs){

		if (rs != null) {

			try {

				rs.close();

			} catch (SQLException e) {

				e.printStackTrace();

			}

		}

		close(conn, ps);

	}
	
}
