<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title></title>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-ui-1.9.2.custom.min.js" />"></script>
	<script type="text/javascript">
	$(function(){
		
		alert('첨부파일을 다운로드하지 못하였습니다.');
		history.back();
	});
	</script>
</head>
<body>
</body>
</html>