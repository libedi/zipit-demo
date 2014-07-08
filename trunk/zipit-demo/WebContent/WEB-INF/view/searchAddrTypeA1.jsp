<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소정제 데모화면</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div id="wrap">
		<header>
			<nav>
				<a href="javascript:moveTab('A1');" class="eq0 on"></a>
				<a href="javascript:moveTab('A2');" class="eq0"></a>
				<a href="javascript:moveTab('B1');" class="eq1"></a>
				<a href="javascript:moveTab('B2');" class="eq1"></a>
			</nav>
		</header>
		<div id="container">
			<div class="search-address">
			    <form>
			    	<label for="sido" class="danger">* 시/도</label>
			    	<select id="sido">
						<option value="">시/도 </option>
						<option value="서울">서울</option>
						<option value="부산">부산</option>
						<option value="대구">대구</option>
						<option value="광주">광주</option>
						<option value="인천">인천</option>
						<option value="대전">대전</option>
						<option value="울산">울산</option>
						<option value="세종">세종</option>
						<option value="경기">경기</option>
						<option value="강원">강원</option>
						<option value="충남">충남</option>
						<option value="충북">충북</option>
						<option value="경남">경남</option>
						<option value="경북">경북</option>
						<option value="전남">전남</option>
						<option value="전북">전북</option>
						<option value="제주">제주</option>
					</select>
					<label for="dongName" class="danger">* 읍면동리</label>
					<input type="text" id="dongName" maxLength="20" style="width: 130px; ime-mode:active;" placeholder="읍면동리">
					<label for="bunji1" class="danger">* 번지</label>
					<input type="text" id="bunji1" placeholder="번지1" maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}"> - 
					<input type="text" id="bunji2" placeholder="번지2" maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}" >
					<a href="#">
						<img src="${pageContext.request.contextPath }/images/searchIcon.png"/>
					</a>
				</form>
				<div class="result" style="display: none;">
					<div class="wrap">
					<div id="dbSearch">
					
					</div>
					</div>
				</div>
			</div>
			(01) 나머지 주소를 입력해주세요.
			<div class="obj-1">
				<p class="title">검색된 주소</p>
				<div class="form">
			       <form onSubmit="fn_GetRefinde(); return false;">
					<input id="dbZipcd" type="text" style="width: 47px;" readonly="readonly">
					<input id="dbAddr1" type="text" style="width: 268px;" readonly="readonly">
					<input id="dbAddr2" type="text" style="width: 268px;" maxLength="50">
					<input type="submit" value="검증" class="btn-box-blue">
				   </form>	
				</div>
				<p class="desc">예) “OOO아파트 OOO동 OOO호”, 또는 “OOO-OO번지”</p>
			</div>
			
		</div>
	</div>
	<a href="javascript:self.close();" class="close"></a>
</body>
</html>