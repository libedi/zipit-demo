package com.sujiewon.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

public interface DemoDao {

	/**
	 * 지번주소리스트 1안 조회
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	JSONArray getJibunAddressList1(HashMap<String, Object> paramMap) throws Exception;
	
	/**
	 * 지번주소리스트 2안 조회
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	JSONArray getJibunAddressList2(HashMap<String, Object> paramMap) throws Exception;

	

}
