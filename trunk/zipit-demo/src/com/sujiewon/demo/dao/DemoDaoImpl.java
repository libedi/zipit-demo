package com.sujiewon.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import com.sujiewon.demo.common.JDBCFactory;
import com.sujiewon.demo.common.SqlQuery;

public class DemoDaoImpl implements DemoDao {
	
	private JDBCFactory jdbc = null;
	private static DemoDao instance = null;
	private Log log = LogFactory.getLog(getClass());
	
	private DemoDaoImpl(){
		jdbc = JDBCFactory.getInstance();
	}
	public static DemoDao getInstance(){
		if(instance == null){
			synchronized(DemoDaoImpl.class){
				if(instance == null){
					instance = new DemoDaoImpl();
				}
			}
		}
		return instance;
	}
	
	@Override
	public JSONArray getJibunAddressList1(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try {
			conn = jdbc.getConnection();
			
			String dongName		= paramMap.get("dongName").toString();
			String newZipCode 	= paramMap.get("newZipCode").toString();
			String sido 		= paramMap.get("sido").toString();
			String bunji1		= paramMap.get("bunji1").toString();
//			String bunji2		= paramMap.get("bunji2").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append(SqlQuery.selectJibun1Tb);
			sql.append("where \n");
			sql.append("	( DONG LIKE '%' || ? || '%' or RI LIKE '%' || ? || '%' ) \n");
			if(!newZipCode.isEmpty()){
				sql.append("	and NEW_ZIPCODE = ? \n");
			}
			if(!sido.isEmpty()){
				sql.append("	and SIDO like '%' || ? || '%' \n");
			}
			if(!bunji1.isEmpty()){
				sql.append("	and ( NULLIF(S_MAINBJ, '')::INT <= NULLIF(?, '')::INT and COALESCE(NULLIF(E_MAINBJ, ''),'99999')::INT >= NULLIF(?, '')::INT ) \n");
			}
			sql.append("order by SIDO, GUGUN, DONG, RI, NULLIF(S_MAINBJ,'')::INT, NULLIF(S_SUBBJ,'')::INT ");
			
			// Query debug log
			if(log.isDebugEnabled()){
				log.debug("Query\n" + sql.toString());
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, dongName);
			pstmt.setString(index++, dongName);
			if(!newZipCode.isEmpty()){
				pstmt.setString(index++, newZipCode);
			}
			if(!sido.isEmpty()){
				pstmt.setString(index++, sido);
			}
			if(!bunji1.isEmpty()){
				pstmt.setString(index++, bunji1);
				pstmt.setString(index++, bunji1);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("NEW_ZIPCODE", 	rs.getString("NEW_ZIPCODE"));
				resultObj.put("SIDO", 			rs.getString("SIDO"));
				resultObj.put("GUGUN", 			rs.getString("GUGUN"));
				resultObj.put("DONG", 			rs.getString("DONG"));
				resultObj.put("RI", 			rs.getString("RI"));
				resultObj.put("SANYN", 			rs.getString("SANYN"));
				resultObj.put("S_MAINBJ", 		rs.getString("S_MAINBJ"));
				resultObj.put("S_SUBBJ", 		rs.getString("S_SUBBJ"));
				resultObj.put("E_MAINBJ", 		rs.getString("E_MAINBJ"));
				resultObj.put("E_SUBBJ", 		rs.getString("E_SUBBJ"));
				resultList.put(resultObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(conn != null){try {conn.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
			if(pstmt != null){try {pstmt.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
		}
		
		return resultList;
	}
	
	@Override
	public JSONArray getJibunAddressList2(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try{
			conn = jdbc.getConnection();
			
			String dongName		= paramMap.get("dongName").toString();
			String newZipCode 	= paramMap.get("newZipCode").toString();
			String sido 		= paramMap.get("sido").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append(SqlQuery.selectJibun2Tb);
			sql.append("where \n");
			sql.append("	( DONG LIKE '%' || ? || '%' or RI LIKE '%' || ? || '%' ) \n");
			if(!newZipCode.isEmpty()){
				sql.append("	and NEW_ZIPCODE = ? \n");
			}
			if(!sido.isEmpty()){
				sql.append("	and SIDO like '%' || ? || '%' \n");
			}
			sql.append("order by SIDO, GUGUN, DONG, RI ");
			
			// Query debug log
			if(log.isDebugEnabled()){
				log.debug("Query\n" + sql.toString());
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, dongName);
			pstmt.setString(index++, dongName);
			if(!newZipCode.isEmpty()){
				pstmt.setString(index++, newZipCode);
			}
			if(!sido.isEmpty()){
				pstmt.setString(index++, sido);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("NEW_ZIPCODE", 		rs.getString("NEW_ZIPCODE"));
				resultObj.put("NEW_ZIPCODE_SEQ", 	rs.getString("NEW_ZIPCODE_SEQ"));
				resultObj.put("SIDO", 				rs.getString("SIDO"));
				resultObj.put("GUGUN", 				rs.getString("GUGUN"));
				resultObj.put("DONG", 				rs.getString("DONG"));
				resultObj.put("RI", 				rs.getString("RI"));
				resultList.put(resultObj);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			if(conn != null){try {conn.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
			if(pstmt != null){try {pstmt.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
			if(rs != null){try {rs.close();} catch (SQLException e) {e.printStackTrace();throw e;}}
		}
		
		return resultList;
	}
	
}
