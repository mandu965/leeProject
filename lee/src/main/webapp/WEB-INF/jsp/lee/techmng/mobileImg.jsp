<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi,minimal-ui">
<title>Insert title here</title>

<script type="text/javascript">
var currentOS;
          var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));
         
          if (mobile) {
               // ����������Ʈ�� �ҷ��ͼ� OS�� �����մϴ�.
               var userAgent = navigator.userAgent.toLowerCase();
               if (userAgent.search("android") > -1)
                    currentOS = "android";
               else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1)
                              || (userAgent.search("ipad") > -1))
                    currentOS = "ios";
               else
                    currentOS = "else";
          } else {
              // ������� �ƴ� ��
               currentOS = "nomobile";
          }
          //alert(currentOS);
          
          //location.href="/WEB-INF/jsp/lee/techmng/mobileimgView.html";
          //http://1.245.161.247:7080/lee/techmng/tech01.do
          //location.href="http://1.245.161.247.7080/.naver.com";
</script>
</head>
<body>

sadf
<img src="<c:url value="/wcom/images/sub/a_1.jpg"/>" width="100%" alt="2"/>
<br><br><br><br><br><br><br><br><br>
<img src="<c:url value="/wcom/images/sub/a_2.jpg"/>" width="100%" alt="2"/>
<img src="<c:url value="/wcom/images/sub/a_3.jpg"/>" width="100%" alt="2"/>
</body>
</html>