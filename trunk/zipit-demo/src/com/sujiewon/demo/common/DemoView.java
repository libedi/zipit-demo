package com.sujiewon.demo.common;

import java.util.HashMap;
import java.util.Map;

public class DemoView {
	private Map<String, Object> model;
	private String path;
	
	public DemoView() {
		super();
	}

	public DemoView(String path) {
		super();
		this.path = path;
	}

	public Map<String, Object> getModel() {
		return model;
	}

	public void setModel(String key, Object value) {
		this.model = new HashMap<String, Object>();
		this.model.put(key, value);
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
}
