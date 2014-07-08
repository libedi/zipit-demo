package com.sujiewon.demo.service;

import com.sujiewon.demo.dao.DemoDao;
import com.sujiewon.demo.dao.DemoDaoImpl;

public class DemoServiceImpl implements DemoService {
	
	private DemoDao demoDao = null;
	private static DemoService instance = null;
	
	private DemoServiceImpl(){
		demoDao = DemoDaoImpl.getInstance();
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
