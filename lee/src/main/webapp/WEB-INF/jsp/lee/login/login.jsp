<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
$(document).ready(function() {
	$("#loginID").focus();
	
	$("#loginBtn")
	.button({icons:{primary:'ui-icon-check'}})
	.click(function() {
		$("#loginForm").attr('action', '<c:url value="/login/loginPro.do"/>').submit();
		return false;
	});
	
	// 엔터키 입력시 취소버튼 안눌리도록 임의로 제어
	$("input[type=text]").keydown(function(e) {
		if(event.keyCode == 13) {
			$("#loginBtn").click();
			return false;	
		}
	});
	
});

</script>
</head>
<body>
<div class="div_center">
	<form id="loginForm" name ="loginForm" action="" method="post">
	<table>
	<tr><td colspan="2">로그인</td>
	</tr>
	<tr><td>아이디</td><td><input type="text" id="loginID" name="loginID" value="admin"></td></tr>
	<tr><td>비밀번호</td><td><input type="password" id ="loginPWD" name="loginPWD" value="1"></td></tr> 
	</table>
	</form>
	<div>
	 <button id="loginBtn">로그인</button>
	</div>
</div>
</body>
</html>