$(function(){
	// 취소버튼 이벤트
	$("#cancelBtn").click(function(){
		self.close();
	});
	
	// 숫자필드 입력
	$(".number").keyup(function(event){
		var value = $(this).val();
		if(isNaN(value)){
			alert("숫자만 입력해주세요.");
			value = value.substring(0, value.length-1);
			$(this).val(value);
			$(this).focus();
		}
	});
	
	// 주소조회 엔터키 이벤트
	$(".search-address :text").keyup(function(){
		var keycode = (event.keyCode ? event.keyCode : event.which);	// firefox 크로스 브라우징 처리
		if(keycode == 13){
			searchAddress();
			return;
		}
	});
	
});


// 탭 이동
function moveTab(tab){
	var path = "";
	if(tab == 'A1'){
		path = "searchAddressFormTypeA1";
	} else if(tab == 'A2'){
		path = "searchAddressFormTypeA2";
	} else if(tab == 'B1'){
		path = "searchAddressFormTypeB1";
	} else if(tab == 'B2'){
		path = "searchAddressFormTypeB2";
	} 
	
	location.href = "zipitDemo?mode=" + path;
}