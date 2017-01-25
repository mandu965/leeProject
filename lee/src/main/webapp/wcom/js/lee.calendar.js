$.fn.dateFormatText=function(){
	$(this).attr('maxlength',10); //길이제한 속성 추가
	$(this).keydown(function(){
		if( !( (event.keyCode >= 48 && event.keyCode<=57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode==8 || event.keyCode==9 || event.keyCode==46 || event.keyCode==189||event.keyCode==109||(event.keyCode >= 37 && event.keyCode<=40))  ){
			alert("숫자만 입력해주세요.");
			event.returnValue=false;
		}
	});
	$(this).blur(function(){
		
		$(this).val( $(this).val().replace(/-/gi,''));
		if($(this).val().length == 8){
			str = $(this).val();
			str = str.substr(0,4)+"-"+str.substr(4,2)+"-"+str.substr(6,2);
			$(this).val(str);
		}
		
	});
	
};

$.datepicker.setDefaults({
	monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	dayNamesMin: ['일','월','화','수','목','금','토'],
	dateFormat: 'yy-mm-dd',
	changeMonth: true, // True if month can be selected directly, false if only prev/next
	changeYear: true, //True if year can be selected directly, false if only prev/next
	showAnim: "blind", // slideDown/fadeIn/blind/bounce/clip/drop/fold/slide
	showOptions: {direction: 'horizontal'}, // Options for enhanced animations
	showMonthAfterYear:true,
	duration: 200  //'fast', // Duration of display/closure

});