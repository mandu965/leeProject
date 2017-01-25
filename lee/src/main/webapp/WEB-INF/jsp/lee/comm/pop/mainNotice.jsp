<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-2.1.4.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-ui-1.9.2.custom.min.js" />"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		
		
	}); 
	
	//공지사항 팝업닫기 세션생성 
	 function setCookie( name, value, expiredays ) { 
	   	 var todayDate = new Date(); 
	   	 todayDate.setDate( todayDate.getDate() + expiredays ); 
	   	 document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" ;

	 }
	//쿠키 생성을 제어하는 함수
	 function controlCookie() {
	   setCookie("popup1","done", 1);
	   self.close();
	  return
	 }
</script>
</head>

<body>
${boardvo.bbs_title}<br>
${boardvo.bbs_contents}<br>
<!-- <a href="#" onclick="javascript:controlCookie()">&nbsp;&nbsp;<b>하루동안 이 창을 열지 않음(X)</b></a> -->
<a href="javascript:controlCookie()">&nbsp;&nbsp;<b>하루동안 이 창을 열지 않음(X)</b></a>
</body>
</html>


