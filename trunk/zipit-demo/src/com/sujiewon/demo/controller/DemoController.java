package com.sujiewon.demo.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;

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

}
