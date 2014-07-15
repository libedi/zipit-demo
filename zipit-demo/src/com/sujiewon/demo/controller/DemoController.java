package com.sujiewon.demo.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import zipit.rfnCustCommonAddrList;

import com.sujiewon.demo.common.AbstractCommonController;
import com.sujiewon.demo.common.DemoView;
import com.sujiewon.demo.service.DemoService;
import com.sujiewon.demo.service.DemoServiceImpl;

public class DemoController extends AbstractCommonController {
	
	private DemoService demoService = DemoServiceImpl.getInstance();
	private Log log = LogFactory.getLog(getClass());
	private String defaultPath = "/WEB-INF/view/";
	
	/**
	 * 주소검색 화면 (지번주소 1안)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public DemoView searchAddressFormTypeA1(HttpServletRequest request, HttpServletResponse response) {
		String path = this.defaultPath + "searchAddrTypeA1.jsp";
		return new DemoView(request, path);
	}
	
	/**
	 * 주소검색 화면 (지번주소 2안)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public DemoView searchAddressFormTypeA2(HttpServletRequest request, HttpServletResponse response) {
		String path = this.defaultPath + "searchAddrTypeA2.jsp";
		return new DemoView(request, path);
	}
	
	/**
	 * 주소검색 화면 (도로명주소 1안)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public DemoView searchAddressFormTypeB1(HttpServletRequest request, HttpServletResponse response) {
		String path = this.defaultPath + "searchAddrTypeB1.jsp";
		return new DemoView(request, path);
	}
	
	/**
	 * 주소검색 화면 (도로명주소 2안)
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public DemoView searchAddressFormTypeB2(HttpServletRequest request, HttpServletResponse response) {
		String path = this.defaultPath + "searchAddrTypeB2.jsp";
		return new DemoView(request, path);
	}

	/**
	 * 주소검색 (지번주소 1안)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView searchAddressA1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray searchAddress = null;
		
		try {
			searchAddress = this.demoService.getJibunAddressList1(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", searchAddress);
		
		return view;
	}

	/**
	 * 주소검색 (지번주소 2안)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView searchAddressA2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray searchAddress = null;
		
		try {
			searchAddress = this.demoService.getJibunAddressList2(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", searchAddress);
		
		return view;
	}

	/**
	 * 주소검색 (도로명주소 1안)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView searchAddressB1(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray searchAddress = null;
		
		try {
			searchAddress = this.demoService.getDoroAddressList1(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", searchAddress);
		
		return view;
	}

	/**
	 * 주소검색 (도로명주소 2안)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView searchAddressB2(HttpServletRequest request,	HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray searchAddress = null;
		
		try {
			searchAddress = this.demoService.getDoroAddressList2(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", searchAddress);
		
		return view;
	}
	
	/**
	 * 시/도 별 시/군/구 조회 1안
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView getSigunguList1(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray sigunguList = null;
		
		try {
			sigunguList = this.demoService.getSigunguList1(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", sigunguList);
		return view;
	}
	
	/**
	 * 시/도 별 시/군/구 조회 2안
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public DemoView getSigunguList2(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		JSONArray sigunguList = null;
		
		try {
			sigunguList = this.demoService.getSigunguList2(paramMap);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", sigunguList);
		return view;
	}

	/**
	 * 주소정제
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public DemoView refineAddress(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = this.defaultPath + "jsonResult.jsp";
		DemoView view = new DemoView(request, path);
		HashMap<String, Object> paramMap = this.makeParamMap(request);
		Map<String, Object> resultMap = null;
		
		JSONObject refineDataObj = new JSONObject();
		JSONObject retDataObj = null;
		JSONArray retDataList = null;
		
		List<Object> tempDataList = null;
		Map<String, Object> tempDataMap = null;
		
		try {
			// 파라미터 셋팅
			String zipCode 	= paramMap.get("zipCode").toString();
			String addr1 	= paramMap.get("addr1").toString();
			String addr2 	= paramMap.get("addr2").toString();
			String encoding	= paramMap.get("encoding").toString();
			String mode		= paramMap.get("mode").toString();
			
			// 주소정제솔루션 호출
			rfnCustCommonAddrList rfn = new rfnCustCommonAddrList();
			resultMap = rfn.getRfnAddrMap(zipCode, "", addr1, addr2, encoding, mode);
			
			if(log.isDebugEnabled()){
				log.debug(resultMap.toString());
			}
			
			// 결과데이터
			refineDataObj.put("RCD3", (String) resultMap.get("RCD3"));
			refineDataObj.put("RMG3", (String) resultMap.get("RMG3"));
			
			if(resultMap.get("DATA") != null){
				tempDataList = (List<Object>) resultMap.get("DATA");
			}

			retDataList = new JSONArray();
			if(tempDataList != null && !tempDataList.isEmpty()){
				for(Object retData : tempDataList){
					tempDataMap = (Map<String, Object>) retData;
					
					retDataObj = new JSONObject();
					retDataObj.put("NODE", 	(String) tempDataMap.get("NODE"));
					retDataObj.put("ZIP", 	(String) tempDataMap.get("ZIP"));	// 지번 우편번호 - deprecated
					retDataObj.put("ZIPR", 	(String) tempDataMap.get("ZIPR"));	// 도로명 우편번호 - deprecated
					retDataObj.put("ZPRN", 	(String) tempDataMap.get("ZPRN"));	// 새우편번호
					retDataObj.put("ZIPMS",	(String) tempDataMap.get("ZIPMS"));
					retDataObj.put("ZIPRS",	(String) tempDataMap.get("ZIPRS"));
					retDataObj.put("JADM", 	(String) tempDataMap.get("JADM"));
					retDataObj.put("JADS", 	(String) tempDataMap.get("JADS"));
					retDataObj.put("NADM", 	(String) tempDataMap.get("NADM"));
					retDataObj.put("NADS", 	(String) tempDataMap.get("NADS"));
					
					retDataList.put(retDataObj);
				}
			}
			refineDataObj.put("DATA", retDataList);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		view.setModel("result", refineDataObj);
		return view;
	}

}
