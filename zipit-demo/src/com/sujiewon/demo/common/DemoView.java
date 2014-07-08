package com.sujiewon.demo.common;

import javax.servlet.http.HttpServletRequest;

public class DemoView {
	private HttpServletRequest req;
	private String path;
	
	public DemoView(HttpServletRequest req) {
		super();
		this.req = req;
	}

	public DemoView(HttpServletRequest req, String path) {
		super();
		this.req = req;
		this.path = path;
	}

	public Object getModel(String modelName) {
		return this.req.getAttribute(modelName);
	}

	public void setModel(String modelName, Object modelObject) {
		this.req.setAttribute(modelName, modelObject);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
