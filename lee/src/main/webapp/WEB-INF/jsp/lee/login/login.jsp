<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js" charset="utf-8"></script>
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
	//alert('${state}');
	
	//var naver_id_login = new naver_id_login("등록한 ClientID 값", "등록한 Callback URL 값");
	/* var naver_id_login = new naver_id_login('6HVXRD8fmNFMmotqsgzg', '<c:url value="http://1.245.161.247:5080/lee/main/main.do"/>');
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white", 2,40);
	naver_id_login.setDomain(".service.com");
	naver_id_login.setState(state);
	naver_id_login.setPopup();
	naver_id_login.init_naver_id_login(); */
	
});

function naverLogin(){
	
	var client_id = '6HVXRD8fmNFMmotqsgzg';
	//var client Secret = 'T0JMoEBjA9';
	var redirect_uri = '<c:url value="http://1.245.161.247:5080/lee/main/main.do"/>';
	var state = '${state}';
	var url = 'https://nid.naver.com/oauth2.0/authorize?client_id='+client_id+'&response_type=code';
	url+= '&redirect_uri='+redirect_uri;
	url+= '&state='+state;
	
	location.href = url;
	//location.href=redirect_uri;
	//location.href = '<c:url value="/comm/attach/attachBbsDown.do?atch_file_sno='+$().getAnchorValue($(this),0)+'&atch_file_no='+$().getAnchorValue($(this),1)+'"/>';
	//https://nid.naver.com/oauth2.0/authorize?client_id={클라이언트 아이디}&response_type=code&redirect_uri={개발자 센터에 등록한 콜백 URL(URL 인코딩)}&state={상태 토큰}
	
}

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
	 <!-- <a href="javascript:naverLogin();" >네이버로그인</a> -->
	 <a href="javascript:naverLogin();"><img src="/lee/wcom/images/btn/naverLoginBtn.PNG" vspace="16" /></a>
	 <div id="naver_id_login"></div>
	</div>
</div>
</body>
</html>