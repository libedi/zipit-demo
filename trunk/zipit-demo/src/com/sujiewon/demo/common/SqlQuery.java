package com.sujiewon.demo.common;

public interface SqlQuery {
	String selectJibun1Tb = "select NEW_ZIPCODE, SIDO, GUGUN, DONG, RI, SANYN, S_MAINBJ, S_SUBBJ, E_MAINBJ, E_SUBBJ from JIBUN_1AN \n";
	String selectJibun2Tb = "select NEW_ZIPCODE, NEW_ZIPCODE_SEQ, SIDO, GUGUN, DONG, RI from JIBUN_2AN \n";
	String selectDoro1Tb = "select UNQ_BLD_MNO, UNQ_BLD_MNO_SEQ, RD_NM_CD, EMD_SRNO, SIDO_NM, SIGUNGU_NM, UM_NM, RD_NM, UNDER_GUBUN, BLD_MAIN_NO, BLD_SUB_NO, BLD_NM, SASEHAM, NEW_ZIPCODE from DORO1_2AN \n";
	String selectDoro2Tb = "select NEW_ZIPCODE, NEW_ZIPCODE_SEQ, SIDO_NM, SIGUNGU_NM, UM_NM, RD_NM, RD_NUMBER, UN_YN, S_MAINBN, S_SUBBN, E_MAINBN, E_SUBBN, RD_RANGE from DORO1_1AN \n";
}
