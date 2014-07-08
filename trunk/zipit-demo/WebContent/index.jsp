<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>주소정제 데모화면</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
// jquery method
$(function(){
	$("#btn").click(function(){
		openPopup();
	});
});

//팝업 오픈
function openPopup(){
	var width = 1020;
	var height = 800;
	var posX = (screen.availWidth - width) / 2;
	var posY = (screen.availHeight - height) / 2;
	var popUrl = "${pageContext.request.contextPath }/zipitDemo?mode=searchAddressFormTypeA1";
	var option = "resizable=no, scrollbars=yes, status=no, " + "width=" + width + ",height=" + height + ",top=" + posY + ",left=" + posX;
	
	window.open(popUrl, "", option);
}
</script>
</head>
<body>
<button type="button" id="btn" name="btn">주소검색</button>
</body>
</html>