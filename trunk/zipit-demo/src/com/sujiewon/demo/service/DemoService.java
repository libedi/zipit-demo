package com.sujiewon.demo.service;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;

public interface DemoService {

	/**
	 * 지번주소 조회 1안
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	JSONArray getJibunAddressList1(HashMap<String, Object> paramMap) throws Exception;
	
	/**
	 * 지번주소 조회 2안
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	JSONArray getJibunAddressList2(HashMap<String, Object> paramMap) throws Exception;

}
