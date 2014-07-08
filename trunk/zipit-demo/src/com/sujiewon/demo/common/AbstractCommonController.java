package com.sujiewon.demo.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractCommonController {
	/**
	 * makeParamMap<br/>
	 * - request 로 넘어온 parameter를 HashMap으로 생성
	 * @param request
	 * @return HashMap<K,V>
	 */
	public HashMap<String, Object> makeParamMap(HttpServletRequest request){
		HashMap<String, Object> requestMap = new HashMap<String, Object>();
		Map<String, String[]> paramsMap = request.getParameterMap();
		for(Map.Entry<String, String[]> entry : paramsMap.entrySet()){
			String key = entry.getKey();
			String[] value = entry.getValue();
			if(value.length > 1){				// 리스트로 넘어오는 파라미터는 String 배열로 넘겨준다.
				requestMap.put(key, value);
			} else {							// 단일 파라미터는 String 으로 넘겨준다.
				requestMap.put(key, value[0]);
			}
		}
		
		return requestMap;
	}
}
