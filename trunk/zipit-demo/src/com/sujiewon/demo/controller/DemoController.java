package com.sujiewon.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoController {
	
	private String defaultPath = "/WEB-INF/view/";
	
	/**
	 * 주소검색 화면
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void searchAddressForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = this.defaultPath + "searchAddr.jsp";
		request.getRequestDispatcher(path).forward(request, response);
	}
}
