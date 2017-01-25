<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
</head>
<body>
<script type="text/javascript">
$("#menu2_Leftsub").show();

$(document).ready(function() {
	$("#fileTestBtn").click(function() {
		$("#fileTestForm").attr('action', '<c:url value="/a/fileTest.do"/>').submit();
		return false;
	});
	
	
});

function fnCheckImageType(objInputFile) {
	alert(objInputFile.value);
}

</script>
a-2
파일 업로드 테스트 

<form id="fileTestForm" name ="fileTestForm" enctype="multipart/form-data" action="" method="post">
 <input id="file" name="file" type="file"/>
 
</form>

 <button id="fileTestBtn">전송</button>
</body>
</html>


