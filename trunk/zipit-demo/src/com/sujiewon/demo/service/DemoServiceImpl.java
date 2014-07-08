package com.sujiewon.demo.service;

import com.sujiewon.demo.dao.DemoDao;
import com.sujiewon.demo.dao.DemoDaoImpl;

public class DemoServiceImpl implements DemoService {
	
	private DemoDao demoDao = DemoDaoImpl.getInstance();
	private static DemoService instance = null;
	
	private DemoServiceImpl(){
		
	}
	public static DemoService getInstance(){
		if(instance == null){
			synchronized(DemoServiceImpl.class){
				if(instance == null){
					instance = new DemoServiceImpl();
				}
			}
		}
		return instance;
	}
}
