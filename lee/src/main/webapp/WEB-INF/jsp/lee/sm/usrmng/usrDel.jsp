<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
$(document).ready(function() {
	$("#usrDelBtn").click(function() {
		$("#usrDelForm").attr('action', '<c:url value="/usr/usrDeletePro.do"/>').submit();
		return false;
	});
});

</script>
</head>
<body>
<form id="usrDelForm" name ="usrDelForm" action="" >   <!-- method="post" -->
<table>
<tr><td colspan="2">회원탈퇴</td>
</tr>
<tr><td>아이디</td><td><input type="text" id="delID" name="delID"></td></tr>
<tr><td>비밀번호</td><td><input type="text" id ="delPWD" name="delPWD"></td></tr> 
</table>
</form>
<div>
 <button id="usrDelBtn">회원탈퇴</button>
</div>

</body>
</html>