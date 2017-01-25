<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>


<%-- <c:set var="man" value='m' scope="request" />
<c:set var="woman" value='w' scope="request" /> --%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
<script type ="text/javascript">

$(document).ready(function() {
	
	$("#usr_birth").datepicker({defaultDate: new Date()});
	//$("#usr_birth").dateFormatText();
	
	
	//$("#usr_birth").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
	
            
		$("#req_gubun_cd1").click(function(){
				
				if($(this).is(':checked')){

					$("#req_gubun_txt").text("국회의원");
				}else{
					$("#req_gubun_txt").text("유관기관");
				}
			});
			
		//회원가입
	$("#usrAddBtn")
	.button({icons:{primary:'ui-icon-check'}})
	.click(function(){
		$("#usrAddForm").attr('action','<c:url value="/usr/usrAdd.do"/>').submit();
		
	});

});
</script>

</head>
<body>
	회원가입

	<form:form commandName="UsrVO" id="usrAddForm" name="usrAddForm" action="" method="post">

		
        성   명 : <form:input path="usr_nm" /><br>
        아이디 : <form:input path="usr_id" /><br>
        비밀번호:<form:input path="usr_pw"  type="password"/><br>
        성별 : 
                <form:radiobutton path="usr_sex" value="M" label="남자"/>
				<form:radiobutton path="usr_sex" value="W" label="여자"/><br>
				
        생년월일 : <form:input path="usr_birth" /><br>
        전화번호 : <form:input path="phoneNumber1" />-<form:input path="phoneNumber2" />-<form:input path="phoneNumber3" /><br>
        주소 : <form:input path="usr_addr" /><br>
        
        
    </form:form>
	<br>

	 <div class="buttonRight">
		<button id="usrAddBtn">가입</button>
	 </div> 

</body>
</html>
