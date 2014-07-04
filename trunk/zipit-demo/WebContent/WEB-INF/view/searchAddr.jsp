<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소정제 데모화면</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.11.0.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div id="wrap">
		<header>
			<h1 class="blind">주소검색</h1>
			<nav>
				<ul>
					<li class="eq0 on"><a href="javascript:fn_crossTap('A1');"><span class="blind">지번주소1</span></a></li>
					<li class="eq0"><a href="javascript:fn_crossTap('A2');"><span class="blind">지번주소2</span></a></li>
					<li class="eq1"><a href="javascript:fn_crossTap('B1');"><span class="blind">도로명주소1</span></a></li>
					<li class="eq1"><a href="javascript:fn_crossTap('B2');"><span class="blind">도로명주소2</span></a></li>
				</ul>
			</nav>
		</header>
		<div id="container">
			<div class="search-address">
			    <form>
				    <div class="select-eq0">
				    	<select id="sido" class="select-eq0" onchange="javascript:fn_callSelect();">
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
						<input id="dongName" type="text" class="key-word" maxLength="20" style="width: 130px; ime-mode:active;"  placeholder="읍면동리">
						<input id="bunji1" type="text"  placeholder="번지1" maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}"> - 
						<input id="bunji2" type="text" placeholder="번지2"  maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}" >
						<a href="#toggle" class="toggle"><span class="blind">열기/닫기</span></a>
						<input type="submit" value="검색" class="submit">
				    </div>
				</form>
				<div class="result" style="display: none;">
					<div class="wrap">
					<div id="dbSearch">
					
					</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>