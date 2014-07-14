package com.sujiewon.demo.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sujiewon.demo.controller.DemoController;

public class DemoServlet extends HttpServlet {

	private static final long serialVersionUID = -866124219962611447L;
	private DemoController demoController = null;
	private DemoView demoView = null;
	private Log log = LogFactory.getLog(getClass());

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
		
		if(log.isInfoEnabled()){
			log.info("[Request Mode]: " + mode);
		}
		
		try{
			if(mode.equals("searchAddressFormTypeA1")){			// 주소검색화면 (지번주소 1안)
				demoView = this.demoController.searchAddressFormTypeA1(request, response);
			} else if(mode.equals("searchAddressFormTypeA2")){	// 주소검색화면 (지번주소 2안)
				demoView = this.demoController.searchAddressFormTypeA2(request, response);
			} else if(mode.equals("searchAddressFormTypeB1")){	// 주소검색화면 (도로명주소 1안)
				demoView = this.demoController.searchAddressFormTypeB1(request, response);
			} else if(mode.equals("searchAddressFormTypeB2")){	// 주소검색화면 (도로명주소 2안)
				demoView = this.demoController.searchAddressFormTypeB2(request, response);
			} else if(mode.equals("searchAddressA1")){			// 주소검색 (지번주소 1안)
				demoView = this.demoController.searchAddressA1(request, response);
			} else if(mode.equals("searchAddressA2")){			// 주소검색 (지번주소 2안)
				demoView = this.demoController.searchAddressA2(request, response);
			} else if(mode.equals("searchAddressB1")){			// 주소검색 (도로명주소 1안)
				demoView = this.demoController.searchAddressB1(request, response);
			} else if(mode.equals("searchAddressB2")){			// 주소검색 (도로명주소 2안)
				demoView = this.demoController.searchAddressB2(request, response);
			} else if(mode.equals("getSigunguList1")){			// 시/도 별 시/군/구 조회
				demoView = this.demoController.getSigunguList1(request, response);
			} else if(mode.equals("getSigunguList2")){			// 시/도 별 시/군/구 조회
				demoView = this.demoController.getSigunguList2(request, response);
			} else {
				
			}
		} catch (ServletException e){
			throw e;
		} catch (IOException e){
			throw e;
		} catch (Exception e){
			e.printStackTrace();
		}
		
		request.getRequestDispatcher(demoView.getPath()).forward(request, response);
	}

}
