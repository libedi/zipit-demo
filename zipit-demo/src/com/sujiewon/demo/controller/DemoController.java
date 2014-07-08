package com.sujiewon.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
	public DemoView searchAddressFormTypeA1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	public DemoView searchAddressFormTypeA2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	public DemoView searchAddressFormTypeB1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
	public DemoView searchAddressFormTypeB2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = this.defaultPath + "searchAddrTypeB2.jsp";
		return new DemoView(request, path);
	}
}
