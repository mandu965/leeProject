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

<decorator:head />

</head>

  			<decorator:body />

</body>
</html>