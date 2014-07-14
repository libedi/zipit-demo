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
	// 시/도 셀렉트 이벤트
	$("#sido").change(function(){
		var sido = $("#sido > option:selected").val();
		if(sido != ''){
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath }/zipitDemo?mode=getSigunguList2",
				data : {
					sido : $("#sido > option:selected").val()
				},
				dataType : "json"
			}).done(function(data){
				applySigungu(data);
			});
		} else {
			$("#sigungu").empty();
			$("#sigungu").html('<option value="">시/군/구</option>');
		}
	});
	
	// 주소조회 버튼 이벤트
	$("#searchBtn").click(function(){
		searchAddress();
	});
});

// 시/도별 시/군/구 적용
function applySigungu(data){
	var len = data.length;
	
	var html = '<option value="">시/군/구</option>';
	for(var i=0; i<len; i++){
		html += '<option value="">' + data[i].SIGUNGU_NM + '</option>';
	}
	$("#sigungu").empty();
	$("#sigungu").html(html);
}

// 주소조회
function searchAddress(){
	var newZipCode = $("#newZipCode").val();
	var sido = $("#sido > option:selected").val();
	var sigungu = $("#sigungu > option:selected").val();
	var road = $("#road").val();
	var bldMain = $("#bldMain").val();
	var bldSub = $("#bldSub").val();
	
	// 입력값 유효성 검사
	if(sido == ''){
		alert("시/도 를 선택해주세요.");
		$("#sido").focus();
		return;
	}
	if(road != '' && road.length < 2){
		alert("도로명을 2글자 이상 입력하세요.");
		$("#road").focus();
		return;
	}
	
	var param = {
		newZipCode : newZipCode,
		sido : sido,
		sigungu : sigungu,
		road : road,
		bldMain : bldMain,
		bldSub : bldSub
	};
	
	$.ajax({
		type : "post",
		url : "${pageContext.request.contextPath }/zipitDemo?mode=searchAddressB2",
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
	html += '<col></col>';
	html += '<col></col>';
	html += '<col></col>';
	html += '<col></col>';
	html += '<col></col>';
	html += '<col></col>';
	html += '<col style="width: 20%;"></col>';
	html += '<thead>';
	html += '<tr>';
	html += '<th>새우편번호</th>';
	html += '<th>시도</th>';
	html += '<th>시군구</th>';
	html += '<th>읍/면</th>';
	html += '<th>도로명</th>';
	html += '<th>건물번호</th>';
	html += '<th>도로명 Range</th>';
	html += '</tr>';
	html += '</thead>';
	html += '<tbody>';
	
	var len = data.length;
	
	if(len > 0){
		for(var i=0; i<len; i++){
			var row = data[i];
			
			// 기본주소
			var addr = row.SIDO_NM + ' ' + row.SIGUNGU_NM + ' ';
			if(row.UM_NM != null && row.UM_NM != ''){
				addr += row.UM_NM + ' ';
			}
			addr += row.RD_NM;
			// 지하여부
			var bldNum = '';
			if(row.UNDER_GUBUN == '1'){
				bldNum = '지하 ';
			} else if(row.UNDER_GUBUN == '2'){
				bldNum = '공중 ';
			}
			bldNum += row.RD_NUMBER;
			
			html += '<tr onclick="javascript:applyAddress(\'' + row.NEW_ZIPCODE + '\',\'' + addr + '\',\'' + row.UN_YN + '\',\'' + $("#bldMain").val() + '\',\'' + $("#bldSub").val() + '\');">';
			html += '<td>' + row.NEW_ZIPCODE + '</td>';
			html += '<td>' + row.SIDO_NM + '</td>';
			html += '<td>' + row.SIGUNGU_NM + '</td>';
			html += '<td>' + row.UM_NM + '</td>';
			html += '<td>' + row.RD_NM + '</td>';
			html += '<td>' + bldNum + '</td>';
			html += '<td>' + row.RD_RANGE + '</td>';
			html += '</tr>';
		}
	} else {
		html += '<tr><td colspan="7">해당 주소가 없습니다.</td></tr>';
	}
	
	html += '</tbody></table>';
	$("#dbSearch").html(html);
	$(".result").show();
}

// 조회한 주소 적용하기
function applyAddress(newZipCode, addr, under, bldMainNo, bldSubNo){
	//지하여부 없을땐 지상으로 기본 셋팅
	if(under == ''){
		under = '0';
	}
	// 필드값 셋팅
	$("#dbZipcd").val(newZipCode);
	$("#dbAddr1").val(addr);
	$("#DBUnderCD").val(under);
	$("#DBUnderCD").focus();
	$("#dbBldMain").val(bldMainNo);
	$("#dbBldSub").val(bldSubNo);
	
	if(bldMainNo == ""){
		$("#dbBldMain").focus();
	} else{
		$("#dbAddr2").focus();
	}
	
	$("#dbSearch").html('');
	$(".result").hide();
}
</script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/style.css">
</head>
<body>
	<div id="wrap">
		<header>
			<nav>
				<a href="javascript:moveTab('A1');" class="eq0"></a>
				<a href="javascript:moveTab('A2');" class="eq0"></a>
				<a href="javascript:moveTab('B1');" class="eq1"></a>
				<a href="javascript:moveTab('B2');" class="eq1 on"></a>
			</nav>
		</header>
		<div id="container">
			<div class="search-address">
			    <form>
			    	<input type="text" class="number" id="newZipCode" maxLength="5" style="width: 65px; ime-mode:active;" placeholder="새우편번호" />
			    	<select id="sido">
						<option value="">시/도 </option>
						<option value="서울특별시">서울</option>
						<option value="부산광역시">부산</option>
						<option value="대구광역시">대구</option>
						<option value="광주광역시">광주</option>
						<option value="인천광역시">인천</option>
						<option value="대전광역시">대전</option>
						<option value="울산광역시">울산</option>
						<option value="세종특별자치시">세종</option>
						<option value="경기도">경기</option>
						<option value="강원도">강원</option>
						<option value="충청남도">충남</option>
						<option value="충청북도">충북</option>
						<option value="경상남도">경남</option>
						<option value="경상북도">경북</option>
						<option value="전라남도">전남</option>
						<option value="전라북도">전북</option>
						<option value="제주특별자치도">제주</option>
					</select>
					<select id="sigungu">
						<option value="">시/군/구</option>
					</select>
					<input type="text" id="road" maxLength="20" style="width: 130px; ime-mode:active;" placeholder="도로명 입력" />
					<input type="text" class="number" id="bldMain" placeholder="번호1" maxLength="5" style="ime-mode:disabled; width: 50px;" /> - 
					<input type="text" class="number" id="bldSub" placeholder="번호2" maxLength="5" style="ime-mode:disabled; width: 50px;" />
					<button type="button" class="search" id="searchBtn">
						<img src="${pageContext.request.contextPath }/images/search_icon.png" alt="검색"/>
					</button>
					<div class="danger" style="font-size: 12px;">* 필수입력항목 : [시/도] + [도로명 또는 건물명]</div>
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
						<input id="dbZipcd" type="text" style="width: 47px;" readonly />
						<input id="dbAddr1"  type="text" style="width: 314px;" readonly />
						<select id="DBUnderCD" name="DBUnderCD">
							<option value="0"  selected>지상</option>
							<option value="1"  >지하</option>
							<option value="2"  >공중</option>
						</select>
						<input type="text" class="number" id="dbBldMain" maxLength="5" style="ime-mode:disabled; width: 47px;" /> - 
						<input type="text" class="number" id="dbBldSub" maxLength="5" style="ime-mode:disabled; width: 47px;" />
						<br>
						<input id="dbAddr2" type="text" maxLength="50" style="width: 608px;" />
						<button type="button" class="blue-btn" onclick="javascript:fn_GetRefinde();">검증</button>
				   </form>	
				</div>
				<p class="desc">예) “OOO아파트 OOO동 OOO호”, 또는 “OOO-OO번지”</p>
			</div>
			<div class="border">
				<p class="msg danger">&nbsp;</p>
			</div>
			<div class="title">2. 선택할 주소</div>
			<ul class="border refineSearch">
				<li>&nbsp;</li>
				<li class="odd">&nbsp;</li>
				<li>&nbsp;</li>
				<li class="odd">&nbsp;</li>
			</ul>
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
						<input type="radio" id="chk1" name="chkRadio" style="display:none;" />
						<label for="chk1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;표준 지번주소</label>
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