<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>


<%-- <c:set var="man" value='m' scope="request" />
<c:set var="woman" value='w' scope="request" /> --%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<script type ="text/javascript">

$(document).ready(function() {
	  
			
	//카테고리 등록
	 $("#cateAddBtn")
	.button({icons:{primary:'ui-icon-disk'}})
	.click(function(){
		$("#cateAddForm").attr('action','<c:url value="/sm/catemng/catemngAdd.do"/>').submit();
		
	});

});
</script>

</head>
<body>
	카테고리 등록

	<form:form commandName="CateVO" id="cateAddForm" name="cateAddForm" action="" method="post">

		
        사용자 성별 : 
                <form:radiobutton path="cate_sex" value="M" label="남자"/>
				<form:radiobutton path="cate_sex" value="W" label="여자"/><br>
		분    류:		
		        <form:radiobutton path="cate_kind" value="Outer" label="Outer"/>
				<form:radiobutton path="cate_kind" value="Top" label="Top"/>
				<form:radiobutton path="cate_kind" value="Bottom" label="Bottom"/>
				<form:radiobutton path="cate_kind" value="Access" label="Access"/><br>
				
	    카테고리명 : <form:input path="cate_nm"/><br>
        
        
        
    </form:form>
	<br>

	 <div class="buttonRight">
		<button id="cateAddBtn">저장</button>
	 </div> 

</body>
</html>
