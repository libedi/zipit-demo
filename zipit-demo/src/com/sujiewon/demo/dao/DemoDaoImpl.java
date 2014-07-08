package com.sujiewon.demo.dao;

import com.sujiewon.demo.common.JDBCFactory;

public class DemoDaoImpl implements DemoDao {
	
	private JDBCFactory jdbc = null;
	private static DemoDao instance = null;
	
	private DemoDaoImpl(){
		jdbc = JDBCFactory.getInstance();
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
