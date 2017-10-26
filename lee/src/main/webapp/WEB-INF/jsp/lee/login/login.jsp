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
	
	var naver_id_login = new naver_id_login("6HVXRD8fmNFMmotqsgzg", "http://1.245.161.247:5080/lee/main/main.do");
	naver_id_login.setButton("white", 1,40); //네이버 아이디로 로그인 버튼 설정
	naver_id_login.init_naver_id_login();
	
});

function naverLogin(){
	
	var client_id = '6HVXRD8fmNFMmotqsgzg';
	//var client Secret = 'T0JMoEBjA9';
	var redirect_uri = '<c:url value="http://1.245.161.247:5080/lee/main/main.do"/>';
	var state = '${state}';
	var url = 'https://nid.naver.com/oauth2.0/authorize?client_id='+client_id+'&response_type=code';
	url+= '&redirect_uri='+encodeURI(redirect_uri);
	url+= '&state='+state;
	
	location.href = url;
	//호출후
	//http://1.245.161.247:5080/lee/main/main.do?code=iR9Ib4mI3YeCvAV0&state=lsqkj48o9in5nk95rlr7ofh3v2
			
	//접근 토큰 발급 요청문 형식
	//https://nid.naver.com/oauth2.0/token?client_id={클라이언트 아이디}&client_secret={클라이언트 시크릿}&grant_type=authorization_code&state={상태 토큰}&code={인증 코드}
    //https://nid.naver.com/oauth2.0/token?client_id=6HVXRD8fmNFMmotqsgzg&client_secret=T0JMoEBjA9&grant_type=authorization_code&state=lsqkj48o9in5nk95rlr7ofh3v2&code=ck1fbwnG5Ykjbyc1
		
/*     		{
"access_token":"AAAAOWHOLgrVEQ5kJtSfl49kPTVfU4D/AJfS8hXF+W5UaqODqbJR3VuvLhZ6Wri8IuR/zR/YIF1rIfoPj7mZ4eDoPvI=",
"refresh_token":"rtipmLyrpXljHJxipCSCvBK82okNpBmerBjXowLFTVfipAq9ESSp9iiUAokASV3Htw0JEEMgXwaRqQcGFkqPkAQS8J3zP0P5GW0Qe2TbzFiiWis7Eh6l8rzsaGsLqx4ETHj2fd",
"token_type":"bearer",
"expires_in":"3600"
} */
//Authorization: bearer AAAAOWHOLgrVEQ5kJtSfl49kPTVfU4D/AJfS8hXF+W5UaqODqbJR3VuvLhZ6Wri8IuR/zR/YIF1rIfoPj7mZ4eDoPvI
    	//Authorization: {토큰 타입] {접근 토큰]
	//접근 토큰 갱신 요청문형식
	//https://nid.naver.com/oauth2.0/token?grant_type=refresh_token&client_id={클라이언트 아이디}&client_secret={클라이언트 시크릿}&refresh_token={갱신 토큰}
		
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
<!-- 	 <script type="text/javascript">
	 var naver_id_login = new naver_id_login("6HVXRD8fmNFMmotqsgzg", "http://1.245.161.247:5080/lee/main/main.do");
	naver_id_login.setButton("green", 3,40); //네이버 아이디로 로그인 버튼 설정
	//naver_id_login.init_naver_id_login();
	
	naver_id_login.setPopup(); //Popup형태의 인증 진행
	naver_id_login.init_naver_id_login();
</script> -->

<!-- 네이버아디디로로그인 초기화 Script -->
<!-- <script type="text/javascript">
	var naver_id_login = new naver_id_login("6HVXRD8fmNFMmotqsgzg", encodeURI("http://1.245.161.247:5080/lee/main/main.do"));
	var state = naver_id_login.getUniqState();
	naver_id_login.setButton("white", 3,40);
	naver_id_login.setDomain(".service.com");
	naver_id_login.setState(state);
	//naver_id_login.setPopup();
	naver_id_login.init_naver_id_login();
</script> -->
<!-- // 네이버아이디로로그인 초기화 Script -->

<!-- 네이버아디디로로그인 Callback페이지 처리 Script -->
<!-- <script type="text/javascript">
	// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
	function naverSignInCallback() {
		// naver_id_login.getProfileData('프로필항목명');
		// 프로필 항목은 개발가이드를 참고하시기 바랍니다.
		alert(naver_id_login.getProfileData('email'));
		alert(naver_id_login.getProfileData('nickname'));
		alert(naver_id_login.getProfileData('age'));
	}

	// 네이버 사용자 프로필 조회
	naver_id_login.get_naver_userprofile("naverSignInCallback()");
</script> -->
<!-- //네이버아디디로로그인 Callback페이지 처리 Script -->
	</div>
</div>
</body>
</html>