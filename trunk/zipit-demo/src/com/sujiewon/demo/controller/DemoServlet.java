package com.sujiewon.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = -866124219962611447L;
	private DemoController demoController = new DemoController(); 

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.requestProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.requestProcess(req, resp);
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response){
		String mode = request.getParameter("mode");
		
		if(mode != null){
			try{
				if(mode.equals("searchAddressForm")){
					this.demoController.searchAddressForm(request, response);
					
				} else if(mode.equals("")){
					
				} else {
					
				}
			} catch(Exception e){
				e.printStackTrace();
			}
			
		} else {
			
		}
	}

}
