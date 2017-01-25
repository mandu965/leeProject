<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/progressbar.css" />" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("#circleProgressBar")
	.button({icons:{primary:'ui-icon-check'}})
	.click(function() {
		 $("body").append( $("<div id='bgdiv'></div>").css(
			{background: '#333333',
			 width:'100%',
			 height:'100%',
			 position:'absolute',
			 opacity:'0.6',
			 'top':'0',
			 'left':'0',
			 'z-index':'10',
			 'display':'none'
			 }
		 ));
		 
				 $().CircleProgress(); 
				 $("#bgdiv").show("fast");
				 $("#progress").css("z-index","15").show("slow");
	});
	
	// 엔터키 입력시 취소버튼 안눌리도록 임의로 제어
	$("input[type=text]").keydown(function(e) {
		if(event.keyCode == 13) {
			$("#loginBtn").click();
			return false;	
		}
	});
	
		/////////////////////concat : S////////////////////////////////
		var str01 ="Hello ";
		var str02 ="world ";
		var str03 ="javascript";
		console.log(str01.concat(str02));
		console.log(str01.concat(str02, str03));
		//alert(str01.concat(str02));
		//alert(str01.concat(str02, str03));
		/////////////////////concat : E////////////////////////////////
		
		/////////////////////slice : S////////////////////////////////
		var str04 ="Hello world javascript";
		
		//alert(str04.slice(0)); //Hello world javascript
		//alert(str04.slice(6)); // world javascript
		//alert(str04.slice(6,11)); //world
		//alert(str04.slice(-1)); //t
		/////////////////////slice : E////////////////////////////////
		
		/////////////////////join : S////////////////////////////////
		var str05 =['A', 'B', 'C', 'D', 'E'];
	
		//alert(str05 + '...............' + str05.join(""));
		//alert(str05 + '...............' + str05.join("and"));
		/////////////////////join : E////////////////////////////////
		
		/* for (var idx in str05) {
			alert(idx + " " + str05[idx]);
		} */
		
/////////////////////map : S////////////////////////////////
	/* 	var array = [1.1, 2.1, 3.1, 4.1, 5.1, 6.1, 7.1, 8.1, 9.1, 10.1];
		
		 var array10 = array.map(function (elements){
			 return elements * 10;
		 });
		 var arrayMath = array.map(Math.ceil);
		alert(array10);
		alert(arrayMath); */
/////////////////////map : E////////////////////////////////

/////////////////////filtered : S////////////////////////////////
	/* 	function isBigEnough(value) {
			  return value >= 10;
			}
			var filtered = [12, 5, 8, 130, 44].filter(isBigEnough);
			filtered is [12, 130, 44]
			alert(filtered); */
/////////////////////filtered : E////////////////////////////////


			var number = [0,1,2,3,4,5];
			function add(acc, value) {
				  return acc + value;
				}
			var sum = number.reduce(add, 0); //add(add(add(add(add(0, 1), 2), 3), 4), 5)

		 // arr.reduce(callback[, initialValue]), left-to-right, array.reduce(callback[, initialValue]);
		 
		// var total = number.reduce(function(a, b) { alert("a="+a+"b="+b); return a + b;});
	//var total = number.reduce(function(a, b) {return a + b;});
//alert(sum + "..."+total);
		 	 
	
});
function chkNumber(){
	if( !( (event.keyCode >= 48 && event.keyCode<=57) || (event.keyCode >= 96 && event.keyCode <= 105) || event.keyCode==8 )  ){
		alert("숫자만 입력해주세요.");
		event.returnValue=false;
	} 
}

function chkEnglish(){
	if( !( (event.keyCode >= 65 && event.keyCode<=90) || (event.keyCode >= 97 && event.keyCode <= 122) || event.keyCode==8 || event.keyCode==16 || event.keyCode==20)  ){
		alert("영어만 입력해주세요.");
		event.returnValue=false;
	} 
}

function openWin()
{
myWindow=window.open('','','width=200,height=100');
myWindow.document.write("<p>이창을 띄운 페이지가 어떻게 변했나요?</p>");
myWindow.focus();
myWindow.opener.document.write("<p>버튼이 사라지고 제가 나옵니다.</p>");
}
</script>
</head>
<body>
<div class="rotating progress" id="progress" style="diplay:none;position:fixed;top:150px;left:600px;"></div> 				
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/progressbar.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/js/lee.progressBar.js" />"></script>
<button id="circleProgressBar">circleProgressBar</button>

영어 체크 : <input type="text" onkeydown="chkEnglish()" /><br>
숫자 체크 : <input type="text" onkeydown="chkNumber()" /><br>

<input type="button" value="눌러 주세요" onclick="openWin()" /><br>

<c:set var="number" value="451000"/>
숫자1: <fmt:formatNumber value="${number}" type="number" var="numberType" />${numberType}<br>
숫자2: <fmt:formatNumber value="${number}" type="number" var="numberType" groupingUsed="false"/>${numberType}<br>
숫자3: <fmt:formatNumber value="${number}" type="currency" var="currencyType"/>${currencyType}<br>
숫자4: <fmt:formatNumber value="0.3" type="percent" var="val3"/>${val3}<br>
숫자5: <fmt:formatNumber value="12345.678123" pattern=".00" var="val4"/>${val4}<br><!--.00 3번째 자리에서 반올림  -->

<c:set var="str" value="Hello Jstl World "/>
${fn:contains(str, 'hello')}<br>
${fn:containsIgnoreCase(str, 'hello')}<br>
${fn:indexOf(str, 'Jstl')}<br>
${fn:replace(str, 'Jstl', 'JstlRplace')}<br>
<%-- ${fn:split(str, ' ')}<br> --%>
${fn:startsWith(str, 'Hello')}<br>
${fn:endsWith(str, 'World ')}<br>
${fn:trim(str)}<br>
${fn:substring(str, 6,10)}<br>




</body>
</html>