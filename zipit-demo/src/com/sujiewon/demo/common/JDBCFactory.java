package com.sujiewon.demo.common;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

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
			driverClass = pf.getProperty("driverClassName");
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
	
}
