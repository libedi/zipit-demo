package com.sujiewon.demo.common;

public interface SqlQuery {
	String selectJibun1Tb = "select NEW_ZIPCODE, SIDO, GUGUN, DONG, RI, SANYN, S_MAINBJ, S_SUBBJ, E_MAINBJ, E_SUBBJ from JIBUN_1AN \n";
	String selectJibun2Tb = "select NEW_ZIPCODE, NEW_ZIPCODE_SEQ, SIDO, GUGUN, DONG, RI from JIBUN_2AN \n";
}
