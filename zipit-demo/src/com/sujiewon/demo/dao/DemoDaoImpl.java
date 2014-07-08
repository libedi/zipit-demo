package com.sujiewon.demo.dao;

import com.sujiewon.demo.common.JDBCFactory;

public class DemoDaoImpl implements DemoDao {
	
	private JDBCFactory jdbc = JDBCFactory.getInstance();
	private static DemoDao instance = null;
	
	private DemoDaoImpl(){
		
	}
	public static DemoDao getInstance(){
		if(instance == null){
			synchronized(DemoDaoImpl.class){
				if(instance == null){
					instance = new DemoDaoImpl();
				}
			}
		}
		return instance;
	}
}
