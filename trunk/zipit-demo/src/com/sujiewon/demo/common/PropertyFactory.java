package com.sujiewon.demo.common;

import java.io.IOException;
import java.util.Properties;

public class PropertyFactory {
	
	private Properties props;
	private String propertyFile;
	
	public PropertyFactory() {
		props = new Properties();
	}

	public void setPropertyFile(String propertyFile) {
		this.propertyFile = propertyFile;
	}
	
	public void loadProperty() throws IOException{
		props.load(getClass().getClassLoader().getResourceAsStream(propertyFile));
	}
	
	public String getProperty(String keyName){
		return props.getProperty(keyName).trim();
	}
}
