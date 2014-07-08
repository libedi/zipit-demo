$(function(){
	// 취소버튼 이벤트
	$("#cancelBtn").click(function(){
		self.close;
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