package com.sujiewon.demo.common;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {
	
	private String propertyFile;
	
	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}
	
	public String getProperty(String keyName) throws IOException{
		Properties props = new Properties();
		
		String value = "";
		if( getClass().getClassLoader().getResourceAsStream(propertyFile) != null ) {
			props.load( getClass().getClassLoader().getResourceAsStream(propertyFile) );
		} else {
			throw new FileNotFoundException();
		}
		value = props.getProperty(keyName).trim();
		
		return value;
	}
}
