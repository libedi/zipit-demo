package com.sujiewon.demo.service;

import java.util.HashMap;

import org.json.JSONArray;

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
	
	@Override
	public JSONArray getJibunAddressList1(HashMap<String, Object> paramMap) throws Exception {
		return this.demoDao.getJibunAddressList1(paramMap);
	}
	
	@Override
	public JSONArray getJibunAddressList2(HashMap<String, Object> paramMap) throws Exception {
		return this.demoDao.getJibunAddressList2(paramMap);
	}
	
	@Override
	public JSONArray getDoroAddressList1(HashMap<String, Object> paramMap) throws Exception {
		return this.demoDao.getDoroAddressList1(paramMap);
	}
	
	@Override
	public JSONArray getDoroAddressList2(HashMap<String, Object> paramMap) throws Exception {
		// 제외할 range 코드값
		String range = "";
		String bldMainNum = paramMap.get("bldMain").toString();
		if(bldMainNum != null && !bldMainNum.isEmpty()){
			if(Integer.parseInt(bldMainNum) % 2 == 1){
				range = "EVEN";
			} else {
				range = "ODD";
			}
		}
		paramMap.put("range", range);
		return this.demoDao.getDoroAddressList2(paramMap);
	}
	
	@Override
	public JSONArray getSigunguList1(HashMap<String, Object> paramMap) throws Exception {
		return this.demoDao.getSigunguList1(paramMap);
	}
	
	@Override
	public JSONArray getSigunguList2(HashMap<String, Object> paramMap) throws Exception {
		return this.demoDao.getSigunguList2(paramMap);
	}
	
}
