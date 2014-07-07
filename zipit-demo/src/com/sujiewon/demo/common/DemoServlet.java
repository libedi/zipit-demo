package com.sujiewon.demo.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sujiewon.demo.controller.DemoController;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = -866124219962611447L;
	private DemoController demoController = null;
	private DemoView demoView = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.requestProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.requestProcess(req, resp);
	}
	
	private void requestProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String mode = request.getParameter("mode");
		demoController = new DemoController();
		
		if(mode.equals("searchAddressFormTypeA1")){			// 주소검색 (지번주소 1안)
			demoView = this.demoController.searchAddressFormTypeA1(request, response);
		} else if(mode.equals("searchAddressFormTypeA2")){	// 주소검색 (지번주소 2안)
			demoView = this.demoController.searchAddressFormTypeA2(request, response);
		} else if(mode.equals("searchAddressFormTypeB1")){	// 주소검색 (도로명주소 1안)
			demoView = this.demoController.searchAddressFormTypeB1(request, response);
		} else if(mode.equals("searchAddressFormTypeB2")){	// 주소검색 (도로명주소 2안)
			demoView = this.demoController.searchAddressFormTypeB2(request, response);
		} else {
			
		}
		
		request.getRequestDispatcher(demoView.getPath()).forward(request, response);
	}

}
