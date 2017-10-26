<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!-- decorator사용, web.xml에 설정하고, decorator.xml만들어서 작성한다. -->
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>

<head>
<title>LeeProject</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 

<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/css/leeWork.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/jquery-ui-1.9.2.custom.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/jquery-ui-1.9.2.custom.min.css"/>" />


<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-2.1.4.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-ui-1.9.2.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/js/lee.dialog.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/js/lee.util.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/js/lee.naverLogin.js" />"></script>

<decorator:head />

</head>
<body class="s_bg">
<div class="LEE_WRAPPER"><!-- LEE_WRAPPER, 틀을 마련해준다 -->
    <!-- top : S -->
		<div  class="LEE_TOP">
			<page:apply-decorator id="top" name="top"></page:apply-decorator>
		</div>
		<!-- top : E -->
		
		<!-- left : S -->
		<div class="LEE_LEFT">
			<div id="left_content">
			     <ul id="subMenuList" class="subMenuList">
				     <li><a href="<c:url value="/prdt/prdtList.do"/>">상품</a></li>
					     <ul class="sub" id="menu2_Leftsub">
	                        <li><a href="<c:url value="/a/aM02.do"/>">menu2-1</a></li>
	                        <li><a href="<c:url value="#"/>">menu2-2</a></li>
	                    </ul>
				     </li>
				     <li><a href="<c:url value="/prdt/prdtList.do"/>">상품</a></li>
	              </ul>
              </div>
		</div>
		<!-- left : E -->
    
        <!-- content : S -->
		<div class="LEE_CENTER">
  			<decorator:body />       
  			<div class="space30"></div>
    	</div>
		<!-- content : E -->
</div>
    	<!-- bottom: S -->
		<div class="LEE_BOTTOM">
			<page:apply-decorator id="bottom" name="bottom"></page:apply-decorator>
		</div>
		<!-- bottom : E -->
</body>
</html>