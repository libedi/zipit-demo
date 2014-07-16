(function($){
	// 셀렉트박스 스타일 적용
	$.fn.extend({
		customStyle : function(options) {
			var agt = navigator.userAgent.toLowerCase();
			if (agt.indexOf("msie 6") == -1) {
				return this.each(function() {
					var currentSelected = $(this).find(':selected');
					$(this).after(
							'<span class="customStyleSelectBox"><span class="customStyleSelectBoxInner">'
									+ currentSelected.text() + '</span></span>')
							.css({
								position : 'absolute',
								opacity : 0,
								fontSize : $(this).next().css('font-size')
							});
					var selectBoxSpan = $(this).next();
					var selectBoxWidth = parseInt($(this).width())
							- parseInt(selectBoxSpan.css('padding-left'))
							- parseInt(selectBoxSpan.css('padding-right'));
					var tempSelectBoxWidth = parseInt($(this).width())
							- parseInt(selectBoxSpan.css('padding-top'))
							- parseInt(selectBoxSpan.css('padding-bottom'));
					var selectBoxSpanInner = selectBoxSpan.find(':first-child');
					selectBoxSpan.css({
						display : 'inline-block'
					});
					selectBoxSpanInner.css({
						width : selectBoxWidth,
						heigth : tempSelectBoxWidth,
						display : 'inline-block'
					});
					var selectBoxHeight = parseInt(selectBoxSpan.height())
							+ parseInt(selectBoxSpan.css('padding-top'))
							+ parseInt(selectBoxSpan.css('padding-bottom'));
					$(this).height(selectBoxHeight).change(
							function() {
								selectBoxSpanInner.text(
										$(this).find(':selected').text()).parent().addClass('changed');
							});
				});
			}
		}
	});
})(jQuery);

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
	
	// 셀렉트박스 스타일 적용
	$(".search-select").customStyle();
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
