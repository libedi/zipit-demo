<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소정제 데모화면</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/common.js"></script>
<script type="text/javascript">
$(function(){
	// 주소조회 버튼 이벤트
	$("#searchBtn").click(function(){
		searchAddress();
	});
});

// 주소조회
function searchAddress(){
	// 화면 초기화
	initSearchAddress();
	
	var dongName = $("#dongName").val();
	var newZipCode = $("#newZipCode").val();
	var sido = $("#sido > option:selected").val();
	var bunji1 = $("#bunji1").val();
	var bunji2 = $("#bunji2").val();
	
	if(dongName == ''){
		alert("읍면동리 명을 입력해주세요.");
		$("#dongName").focus();
		return;
	}
	
	var param = {
		mode : 'J',
		dongName : dongName,
		newZipCode : newZipCode,
		sido : sido,
		bunji1 : bunji1,
		bunji2 : bunji2
	};
	
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath }/zipitDemo?mode=searchAddressA1",
		data : param,
		dataType : "json"
	}).done(function(data){
		callbackSearch(data);
	});
}
// 주소조회 콜백함수
function callbackSearch(data){
	var html = "";
	html += '<table>';
	html += '<col style="width:16%""></col>';
	html += '<thead>';
	html += '<tr>';
	html += '<th>새우편번호</th>';
	html += '<th>시도</th>';
	html += '<th>군구</th>';
	html += '<th>읍/면/동</th>';
	html += '<th>리</th>';
	html += '<th>번지</th>';
	html += '</tr>';
	html += '</thead>';
	html += '<tbody>';
	
	var len = data.length;
	
	if(len > 0){
		for(var i=0; i<len; i++){
			var row = data[i];
			
			var addr = row.SIDO + ' ' + row.GUGUN + ' ' + row.DONG;
			if(row.RI != null && row.RI != ''){
				addr += ' ' + row.RI;
			}
			var detailAddr = $("#bunji1").val();
			if($("#bunji2").val() != ''){
				detailAddr += '-' + $("#bunji2").val() + ' ';
			}
			
			html += '<tr onclick="javascript:applyAddress(\'' + row.NEW_ZIPCODE + '\', \'' + addr + '\', \'' + detailAddr + '\');">';
			html += "<td>" + row.NEW_ZIPCODE + "</td>";
			html += "<td>" + row.SIDO + "</td>";
			html += "<td>" + row.GUGUN + "</td>";
			html += "<td>" + row.DONG + "</td>";
			html += "<td>" + row.RI + "</td>";
			html += "<td>";
			if(row.SANYN != null && row.SANYN == '1'){
				html += '산 ';
			}
			html += row.S_MAINBJ;
			if(row.S_SUBBJ != null && row.S_SUBBJ != ''){	// 부번지
				html += '-' + row.S_SUBBJ;
			}
			if(row.E_MAINBJ != null && row.E_MAINBJ != ''){
				html += ' ~ ' + row.E_MAINBJ;
			}
			if(row.E_SUBBJ != null && row.E_SUBBJ != ''){
				html += '-' + row.E_SUBBJ;
			}
			html += '</td></tr>';
		}
	} else {
		html += "<tr><td colspan='6'>해당 주소가 없습니다.</td></tr>";
	}
	
	html += '</tbody></table>';
	$("#dbSearch").html(html);
	$(".result").show();
}

// 조회한 주소 적용하기
function applyAddress(zipCd, addr1, addr2){
	$("#dbZipcd").val(zipCd);
	$("#dbAddr1").val(addr1);
	if(addr2 != ''){
		$("#dbAddr2").val(addr2 + " ");
	}
	$("#dbAddr2").focus();
	$(".result").hide();
	$("#dbSearch").empty();
}

// 조회된 주소 초기화
function initSearchAddress(){
	$("#dbZipcd").val('');
	$("#dbAddr1").val('');
	$("#dbAddr2").val('');
	$("#respMsg").html('&nbsp;');
}
</script>
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
			    	<input type="text" class="number" id="newZipCode" maxLength="6" style="width: 80px; ime-mode:active;"  placeholder="새우편번호">
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
					<input type="text" id="dongName" maxLength="20" style="width: 130px; ime-mode:active;" placeholder="읍면동리" />
					<input type="text" class="number" id="bunji1" placeholder="번지1" maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}" /> - 
					<input type="text" class="number" id="bunji2" placeholder="번지2" maxLength="5" style="ime-mode:disabled; width: 40px;"onKeypress="onlyNumber();" onkeydown="javascript:if(event.keyCode=='13'){fn_GetSearch();}" />
					<button type="button" class="search" id="searchBtn">
						<img src="${pageContext.request.contextPath }/images/search_icon.png"/>
					</button>
				</form>
				<div class="result" style="display: none;">
					<div class="wrap">
						<div id="dbSearch">
						
						</div>
					</div>
				</div>
			</div>
			<div class="title">1. 나머지 주소를 입력해주세요.</div>
			<div class="border">
				<p>* 검색된 주소</p>
				<div>
					<form>
						<input type="text" id="dbZipcd" style="width: 47px;" readonly />
						<input type="text" id="dbAddr1" style="width: 268px;" readonly />
						<input type="text" id="dbAddr2" style="width: 268px;" maxLength="50" />
						<button type="button" class="blue-btn">검증</button>
				   </form>	
				</div>
				<p class="desc">예) “OOO아파트 OOO동 OOO호”, 또는 “OOO-OO번지”</p>
			</div>
			<div class="border">
				<p id="respMsg" class="msg danger">&nbsp;</p>
			</div>
			<div class="title">2. 선택할 주소</div>
			<div id="refinedAddress">
				<ul class="border refineSearch">
					<li>&nbsp;</li>
					<li class="odd">&nbsp;</li>
					<li>&nbsp;</li>
					<li class="odd">&nbsp;</li>
				</ul>
			</div>
			<div class="title">3. 주소확인</div>
			<ul class="border">
				<li>
					<span class="option">
						<input type="radio" id="chk0" name="chkRadio" />
						<label for="chk0">표준 도로명주소</label>
					</span>
					<input type="text" id="roadZip" style="width: 47px;" readonly />
					<input type="text" id="roadAddr1" style="width: 216px;" readonly />
					<input type="text" id="roadAddr2" style="width: 276px;" readonly />
				</li>
				<li>
					<span class="option">
						<input type="radio" id="chk1" name="chkRadio" />
						<label for="chk1">표준 지번주소</label>
					</span>
					<input type="text" id="jibunZip" style="width: 47px;" readonly />
					<input type="text" id="jibunAddr1" style="width: 216px;" readonly />
					<input type="text" id="jibunAddr2" style="width: 276px;" readonly />
				</li>
				<li>
					<span class="option">
						<input type="radio" id="chk2" name="chkRadio" />
						<label for="chk2">입력주소</label>
					</span>
					<input type="text" id="inputZip" style="width: 47px;" readonly />
					<input type="text" id="inputAddr1" style="width: 216px;" readonly />
					<input type="text" id="inputAddr2" style="width: 276px;" readonly />
				</li>
			</ul>
			<div class="box-right">
				<button type="button" class="blue-btn" id="confirmBtn">확인</button>
				<button type="button" id="cancelBtn">취소</button>
			</div>
		</div>
	</div>
	<a href="javascript:self.close();" class="close"></a>
</body>
</html>