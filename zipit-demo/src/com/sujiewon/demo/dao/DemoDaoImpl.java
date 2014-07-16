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
				sql.append("	and SIDO = ? \n");
			}
			if(!bunji1.isEmpty()){
				sql.append("	and ( (NULLIF(S_MAINBJ, '')::INT <= NULLIF(?, '')::INT and NULLIF(E_MAINBJ, '')::INT >= NULLIF(?, '')::INT ) \n");
				sql.append("		or NULLIF(S_MAINBJ, '')::INT = NULLIF(?, '')::INT ) \n");
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
			jdbc.close(conn, pstmt, rs);
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
				sql.append("	and SIDO = ? \n");
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
			jdbc.close(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	@Override
	public JSONArray getDoroAddressList1(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try{
			conn = jdbc.getConnection();
			
			String newZipCode = paramMap.get("newZipCode").toString();
			String sido 	= paramMap.get("sido").toString();
			String sigungu 	= paramMap.get("sigungu").toString();
			String road 	= paramMap.get("road").toString();
			String bldNm 	= paramMap.get("bldNm").toString();
			String bldMain 	= paramMap.get("bldMain").toString();
//			String bldSub 	= paramMap.get("bldSub").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append(SqlQuery.selectDoro1Tb);
			sql.append("where \n");
			sql.append("	SIDO_NM = ? \n");
			if(!newZipCode.isEmpty()){
				sql.append("	and NEW_ZIPCODE = ? \n");
			}
			if(!sigungu.isEmpty()){
				sql.append("	and SIGUNGU_NM = ? \n");
			}
			if(!road.isEmpty()){
				sql.append("	and RD_NM like '%' || ? || '%' \n");
			}
			if(!bldNm.isEmpty()){
				sql.append("	and BLD_NM like '%' || ? || '%' \n");
			}
			if(!bldMain.isEmpty()){
				sql.append("	and BLD_MAIN_NO = ? \n");
			}
			sql.append("order by SIDO_NM, SIGUNGU_NM, UM_NM, substring(RD_NM from '[^0-9]+'), substring(RD_NM from '([0-9]+)')::INT, BLD_MAIN_NO::INT, BLD_NM ");
			
			// Query debug log
			if(log.isDebugEnabled()){
				log.debug("Query\n" + sql.toString());
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, sido);
			if(!newZipCode.isEmpty()){
				pstmt.setString(index++, newZipCode);
			}
			if(!sigungu.isEmpty()){
				pstmt.setString(index++, sigungu);
			}
			if(!road.isEmpty()){
				pstmt.setString(index++, road);
			}
			if(!bldNm.isEmpty()){
				pstmt.setString(index++, bldNm);
			}
			if(!bldMain.isEmpty()){
				pstmt.setString(index++, bldMain);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("UNQ_BLD_MNO", 		rs.getString("UNQ_BLD_MNO"));
				resultObj.put("UNQ_BLD_MNO_SEQ", 	rs.getString("UNQ_BLD_MNO_SEQ"));
				resultObj.put("RD_NM_CD", 			rs.getString("RD_NM_CD"));
				resultObj.put("EMD_SRNO", 			rs.getString("EMD_SRNO"));
				resultObj.put("SIDO_NM", 			rs.getString("SIDO_NM"));
				resultObj.put("SIGUNGU_NM", 		rs.getString("SIGUNGU_NM"));
				resultObj.put("UM_NM", 				rs.getString("UM_NM"));
				resultObj.put("RD_NM", 				rs.getString("RD_NM"));
				resultObj.put("UNDER_GUBUN", 		rs.getString("UNDER_GUBUN"));
				resultObj.put("BLD_MAIN_NO", 		rs.getString("BLD_MAIN_NO"));
				resultObj.put("BLD_SUB_NO", 		rs.getString("BLD_SUB_NO"));
				resultObj.put("BLD_NM", 			rs.getString("BLD_NM"));
				resultObj.put("SASEHAM", 			rs.getString("SASEHAM"));
				resultObj.put("NEW_ZIPCODE", 		rs.getString("NEW_ZIPCODE"));
				resultList.put(resultObj);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			jdbc.close(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	@Override
	public JSONArray getDoroAddressList2(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try{
			conn = jdbc.getConnection();
			
			String newZipCode = paramMap.get("newZipCode").toString();
			String sido 	= paramMap.get("sido").toString();
			String sigungu 	= paramMap.get("sigungu").toString();
			String road 	= paramMap.get("road").toString();
			String bldMain 	= paramMap.get("bldMain").toString();
//			String bldSub 	= paramMap.get("bldSub").toString();
			String range	= paramMap.get("range").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append(SqlQuery.selectDoro2Tb);
			sql.append("where \n");
			sql.append("	SIDO_NM = ? \n");
			if(!newZipCode.isEmpty()){
				sql.append("	and NEW_ZIPCODE = ? \n");
			}
			if(!sigungu.isEmpty()){
				sql.append("	and SIGUNGU_NM = ? \n");
			}
			if(!road.isEmpty()){
				sql.append("	and RD_NM like '%' || ? || '%' \n");
			}
			if(!bldMain.isEmpty()){
				sql.append("	and ( (NULLIF(S_MAINBN, '')::INT <= NULLIF(?, '')::INT and NULLIF(E_MAINBN, '')::INT >= NULLIF(?, '')::INT) \n");
				sql.append("		or NULLIF(S_MAINBN, '')::INT = NULLIF(?, '')::INT ) \n");
			}
			if(!range.isEmpty()){
				sql.append("	and RD_RANGE not ilike '%' || ? \n");
			}
			sql.append("order by SIDO_NM, SIGUNGU_NM, UM_NM, substring(RD_NM from '[^0-9]+'), substring(RD_NM from '([0-9]+)')::INT, S_MAINBN::INT ");
			
			// Query debug log
			if(log.isDebugEnabled()){
				log.debug("Query\n" + sql.toString());
			}
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 1;
			pstmt.setString(index++, sido);
			if(!newZipCode.isEmpty()){
				pstmt.setString(index++, newZipCode);
			}
			if(!sigungu.isEmpty()){
				pstmt.setString(index++, sigungu);
			}
			if(!road.isEmpty()){
				pstmt.setString(index++, road);
			}
			if(!bldMain.isEmpty()){
				pstmt.setString(index++, bldMain);
				pstmt.setString(index++, bldMain);
				pstmt.setString(index++, bldMain);
			}
			if(!range.isEmpty()){
				pstmt.setString(index++, range);
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("NEW_ZIPCODE", 		rs.getString("NEW_ZIPCODE"));
				resultObj.put("NEW_ZIPCODE_SEQ", 	rs.getString("NEW_ZIPCODE_SEQ"));
				resultObj.put("SIDO_NM", 			rs.getString("SIDO_NM"));
				resultObj.put("SIGUNGU_NM", 		rs.getString("SIGUNGU_NM"));
				resultObj.put("UM_NM", 				rs.getString("UM_NM"));
				resultObj.put("RD_NM", 				rs.getString("RD_NM"));
				resultObj.put("RD_NUMBER", 			rs.getString("RD_NUMBER"));
				resultObj.put("UN_YN", 				rs.getString("UN_YN"));
				resultObj.put("S_MAINBN", 			rs.getString("S_MAINBN"));
				resultObj.put("S_SUBBN", 			rs.getString("S_SUBBN"));
				resultObj.put("E_MAINBN", 			rs.getString("E_MAINBN"));
				resultObj.put("E_SUBBN", 			rs.getString("E_SUBBN"));
				resultObj.put("RD_RANGE", 			rs.getString("RD_RANGE"));
				resultList.put(resultObj);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			jdbc.close(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	@Override
	public JSONArray getSigunguList1(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try{
			conn = jdbc.getConnection();
			
			String sido = paramMap.get("sido").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select \n");
			sql.append("	distinct SIGUNGU_NM \n");
			sql.append("from \n");
			sql.append("	DORO1_2AN \n");
			sql.append("where \n");
			sql.append("	SIDO_NM = ? \n");
			sql.append("order by 1 ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("SIGUNGU_NM", rs.getString("SIGUNGU_NM"));
				resultList.put(resultObj);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			jdbc.close(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
	@Override
	public JSONArray getSigunguList2(HashMap<String, Object> paramMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JSONObject resultObj = null;
		JSONArray resultList = new JSONArray();
		
		try{
			conn = jdbc.getConnection();
			
			String sido = paramMap.get("sido").toString();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select \n");
			sql.append("	distinct SIGUNGU_NM \n");
			sql.append("from \n");
			sql.append("	DORO1_1AN \n");
			sql.append("where \n");
			sql.append("	SIDO_NM = ? \n");
			sql.append("order by 1 ");
			
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sido);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resultObj = new JSONObject();
				resultObj.put("SIGUNGU_NM", rs.getString("SIGUNGU_NM"));
				resultList.put(resultObj);
			}
		} catch (Exception e){
			e.printStackTrace();
			throw e;
		} finally{
			jdbc.close(conn, pstmt, rs);
		}
		
		return resultList;
	}
	
}
