<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<script type="text/javascript">
$(document).ready(function() {
	var submenu=new Array();
	 var menu_tmps = new Array();
    var sub_tmps=document.getElementById("sub_menu").getElementsByTagName("ul"); //현재 사이즈 : 6
   
    for (var i=0, len=sub_tmps.length; i<len; i++) {
        if (sub_tmps[i].className=="localNav")
           submenu.push(sub_tmps[i]); 
     }
    
    menu_tmps=document.getElementById("sub_menu").getElementsByTagName("a"); 
    
    for (var i=0, len=menu_tmps.length; i<len; i++) {
        if (menu_tmps[i].className=="globalNav") {
           menu_tmps[i].onmouseover=function() {        	      
              for (var j=0, sublen=submenu.length; j<sublen; j++)
            	  {//mousOver, non
            	   submenu[j].style.display="none";
            	  }
              
              document.getElementById(this.id+"_sub").style.display="block";
           };

           menu_tmps[i].onfocus=menu_tmps[i].onmouseover;
        }
     } 
	
});
	</script>


	<h1 class="Top_logo"><a href="<c:url value="/main/main.do"/>"><img src="/lee/wcom/images/logo/logo.gif" alt="/" /></a></h1>  <!-- 현재 로고그림 width 270 --> 
	<ul class="Top_center" id="sub_menu">
		
			<li class="menu1" ><a href="<c:url value="/intro/aM01.do"/>" class="globalNav" id="menu1"><img src="/lee/wcom/images/top/top_m1_on.gif" /></a> <!-- 이미지 넓이 134 -->
			  <ul  id="menu1_sub" class="localNav">
			    <li class=""><a href="<c:url value="/intro/aM01.do" />">소개&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="/intro/aM02.do" />">참여&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="/intro/aM03.do" />">행사</a></li>
			  </ul>
			</li>
			
			<li class=" menu2" ><a href="<c:url value="/board/notice/boardList.do?bbs_sno=1"/>" class="globalNav" id="menu2"><img src="/lee/wcom/images/top/top_m2_on.gif" /></a>
			  <ul  id="menu2_sub" class="localNav">
			    <li class=""><a href="<c:url value="/board/notice/boardList.do?bbs_sno=1" />">공지사항&nbsp;&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="/board/notice/boardList.do?bbs_sno=2" />">자유게시판&nbsp;&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="#" />">게시판</a></li>
			  </ul>
			</li>
			
		 	<li class="menu3" ><a href="<c:url value="/prdt/prdtList.do"/>" class="globalNav" id="menu3"><img src="/lee/wcom/images/top/top_m3_on.gif" /></a>
			  <ul id="menu3_sub"  class="localNav">
			    <li class=""><a href="<c:url value="/prdt/prdtList.do" />">상품&nbsp;&nbsp;&nbsp;</a></li>
			    <li class=""><a href="#">오픈준비중&nbsp;&nbsp;&nbsp;</a></li>
			    <li class=""><a href="#">오픈준비중</a></li>
			  </ul>
			</li>
			
			<li class="menu4" ><a href="<c:url value="/techmng/tech01.do"/>" class="globalNav" id="menu4"><img src="/lee/wcom/images/top/top_m4_on.gif" /></a>
			  <ul id="menu4_sub"  class="localNav">
			    <li class=""><a href="<c:url value="#" />">Outer&nbsp;&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="#" />">Top&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="#" />">Bottom</a></li>
			  </ul>
			</li>
			
			<li class="menu5"><a href="<c:url value="#"/>" class="globalNav" id="menu5"><img src="/lee/wcom/images/top/top_m5_on.gif" /></a>
			  <ul  id="menu5_sub" class="localNav">
			    <li class=""><a href="<c:url value="#" />">모자&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="#" />">가방&nbsp;&nbsp;</a></li>
			    <li class=""><a href="<c:url value="#" />">신발</a></li>
			  </ul>
			</li>
			
			<li class="menu6" ><a href="<c:url value="#"/>" class="globalNav" id="menu6"><img src="/lee/wcom/images/top/top_m6_on.gif" /></a>
			  <ul  id="menu6_sub" class="localNav">
			    <li class=""><a href="<c:url value="#" />">menu6-1</a></li>
			    <li class=""><a href="<c:url value="#" />">menu6-2</a></li>
			    <li class=""><a href="<c:url value="#" />">menu6-3</a></li>
			  </ul>
			</li> 
			
		
	</ul>
 	<!-- <div class="top_search ">
		<div class="top_search_bar ">
			<form id="unifiedSearchForm" name="unifiedSearchForm" action="" method="post">
				<input type="text" id="searchTerm" name="searchTerm" /> 뭔지 모르겠다 
				<input type="text" id="searchTerm" name="searchTerm" style="width: 170px"/>
			</form>
		</div>
		<div class="top_search_btn"><img src="/lee/wcom/images/top/btn_search.gif" onclick="unifiedSearch()" style="cursor:pointer;"/></div>
		"top_search_btn" 의미 없음, 가독성
	</div> -->
	
	<div class="global_menu">
		<ul>
		<li><a href="<c:url value="#"/>">A</a></li>
			<li><a href="<c:url value="#"/>">B</a></li>
			<c:choose>
			
				<c:when test="${not empty lee_loginSession }">
				   <li><a href="<c:url value="/mp/orderList.do"/>">마이페이지</a></li>
				   
				   <li><span><c:out value="${lee_loginSession.usr_nm}"/></span> <c:out value="${lee_loginSession.usr_id}"/></li>
				   
				       <c:if test="${lee_loginSession.usr_auth_cd == '103'}">
				           <li><a href="<c:url value="/sm/usrmng/usrmngList.do"/>">시스템관리</a></li>
				       </c:if>
				    
					<li><a href="<c:url value="/login/logout.do"/>">로그아웃</a></li>
					<li class="end"><a href="<c:url value="/usr/usrDelete.do"/>">회원탈퇴</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="<c:url value="/login/login.do"/>">로그인</a></li>
					<li><a href="<c:url value="/usr/usrJoin.do"/>">회원가입</a></li>
				</c:otherwise>
			</c:choose>
		</ul>
		</div>
