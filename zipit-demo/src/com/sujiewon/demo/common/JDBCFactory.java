package com.sujiewon.demo.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCFactory {
	private static JDBCFactory instance = null;
	
	private String driverClass;
	private String url;
	private String username;
	private String password;
	
	private JDBCFactory(){
		PropertyFactory pf = new PropertyFactory();
		pf.setPropertyFile("jdbc.properties");
		try {
			pf.loadProperty();
			driverClass = pf.getProperty("driverClass");
			url = pf.getProperty("url");
			username = pf.getProperty("username");
			password = pf.getProperty("password");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCFactory getInstance(){
		if(instance == null){
			synchronized(JDBCFactory.class){
				if(instance == null){
					instance = new JDBCFactory();
				}
			}
		}
		return instance;
	}
	
	public Connection getConnection() throws Exception{
		Class.forName(driverClass);
		return DriverManager.getConnection(url, username, password);
	}

	public void close(Connection conn, PreparedStatement pstmt) throws SQLException {
		this.close(conn, pstmt, null);
	}
	
	public void close(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException {
		if(conn != null){try {conn.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
		if(pstmt != null){try {pstmt.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
		if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
	}
	
}
