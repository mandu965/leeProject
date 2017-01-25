<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!-- decorator사용, web.xml에 설정하고, decorator.xml만들어서 작성한다. -->
<%@taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>

<!DOCTYPE html>
<html>

<head>
<title>LeeProject</title>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi,minimal-ui">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" /> 
<meta name="viewport" content="height=device-height, width=device-width"/>


<%-- <link rel="stylesheet" type="text/css" href="<c:url value="/wcom/css/leeWork.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/jquery-ui-1.8.16.custom.css"/>" />
	
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-1.6.2.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-ui-1.8.16.custom.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/js/util.js" />" ></script>
<script type="text/javascript" src="<c:url value="/wcom/js/dialog.js" />" ></script>
<script type="text/javascript" src="<c:url value="/wcom/js/comm.js" />" ></script>

<script type="text/javascript" src="<c:url value="/wcom/jquery/jquery-1.11.2.min.js" />"></script> --%>

<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/css/leeWork.css" />" />
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/jquery-ui-1.9.2.custom.css"/>" />


<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-2.1.4.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery-ui-1.9.2.custom.min.js" />"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	
	
	//게시판 일련번호를 이용하여 각 팝업창 구분
	var array = new Array();
	<c:forEach items="${mainNoticeList}" var = "mainNoticeList" varStatus="idx">
	array['${idx.count}'] = ${mainNoticeList.blt_rsrc_sno};
	window.open("", "mainNoticeView", "scrollbars=yes,toolbar=no,status=no,menubar=no,width=${mainNoticeList.popup_width},height=${mainNoticeList.popup_height},top=${mainNoticeList.popup_y_pos},left=${mainNoticeList.popup_x_pos}");
	var f = document.mainNotice;
	f.blt_rsrc_sno.value = '${mainNoticeList.blt_rsrc_sno}';
	f.target = 'mainNoticeView';
	f.action = '<c:url value="/comm/pop/mainNotice.do"/>';
	f.method = 'get';
	f.submit();
	</c:forEach>
	 
	
	
	 //공지사항 팝업 쿠키검사
    /*   cookiedata = document.cookie;
      var id="#pop";
      var val_id="pop";
      for(var i=1; i<array.length; i++){
      id = "#pop"+array[i];
      val_id ="pop"+array[i];
      if (cookiedata.indexOf(val_id+"="+val_id) < 0 ){
    	  $(id).draggable(); 
         $(id).show();
       }else { 
        $(id).hide(); 
       }; 
      } */
      
      $(".topImg").mouseover(function(){
    	  var img = "/lee/wcom/images/top/"+this.id+"_on.gif";
    	  var top = "#" + this.id;
    	  $(top).attr('src', img);
  	});
      $(".topImg").mouseleave(function(){
    	  var img = "/lee/wcom/images/top/"+this.id+"_off.gif";
    	  var top = "#" + this.id;
    	  $(top).attr('src', img);
	  	});
	
});
//공지사항 팝업닫기 세션생성 
function setCookie( name, value, expiredays ) { 
  	 var todayDate = new Date(); 
  	 todayDate.setDate( todayDate.getDate() + expiredays ); 
  	 document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";" ;

}
//공지사항 팝업닫기	
 function popupClose(obj, gubun) {
	  var id="";
	  var val_id="";
     id = "#pop" + obj;
	  val_id = "pop" + obj;
	  if(gubun==1){setCookie(val_id, val_id , 1 );} 
      $(id).hide(); 
   } 

</script>
<decorator:head />



</head>
<body class="s_bg">
<div class="LEE_WRAPPER"><!-- LEE_WRAPPER, 틀을 마련해준다 -->
<form id="mainNotice" name="mainNotice" action="" method="post">
<input type="hidden" id="blt_rsrc_sno" name="blt_rsrc_sno" value=""/>

</form>
<c:if test="${ not empty mainNoticeList}">
		  <c:forEach items="${mainNoticeList }" var = "mainNoticeList" varStatus="idx">
			  <div id="pop${mainNoticeList.blt_rsrc_sno}" style="display:none; width:${mainNoticeList.popup_width}px; height:${mainNoticeList.popup_height}px; border:1px solid #73aad3; background:white; position:absolute; top:${mainNoticeList.popup_y_pos}px; left:${mainNoticeList.popup_x_pos}px; text-align:left; z-index:30;">
		         
		          <table class="view wfull" cellpadding="0" cellspacing="0" >
					<colgroup>
						<col width="w50p"/>
						<col class="w35p" />
					</colgroup>
					<tbody>
						<tr><th height="30px" colspan="2" style="text-align: center; border-bottom:1px solid #73aad3; background:#73aad3; color:white;">공 지 사 항</th></tr>
					</tbody>
				</table>
				
				<div style="overflow-y:auto; overflow-x: auto;width:${mainNoticeList.popup_width}px; height:${mainNoticeList.popup_height-80}px;">
			         <table class="view wfull">
						<colgroup>
							<col width="w75p"/>
							<col class="w25p" />
						</colgroup>
		
						<tbody>
							<tr>
								<td id="contents" colspan="2" style="padding-top:20px;">${mainNoticeList.bbs_contents}</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				 <table class="view wfull" cellpadding="0" cellspacing="0" >
					<colgroup>
						<col width="w75p"/>
						<col class="w25p" />
					</colgroup>
	
					<tbody>
						<tr ><th height="20px" colspan="2" style="background:#73aad3;">&nbsp;</th></tr>
						<tr>
							<th style="text-align:left;padding:10px;padding-left:20px;">
							  <a href="#" onclick="popupClose('${mainNoticeList.blt_rsrc_sno}', '1')">&nbsp;&nbsp;<b>하루동안 이 창을 열지 않음(X)</b></a>
							</th>
							<th style="text-align:center">
							  <a href="#" onclick="popupClose('${mainNoticeList.blt_rsrc_sno}', '0')"><img src="<c:url value ="/wcom/se2/img/photoQuickPopup/btn_close.png"/>"/></a> 
							</th>
						</tr>
					</tbody>
				</table>
		     </div>
		</c:forEach>
	</c:if>
	
	
	
    <!-- top : S -->
	 	<div  class="LEE_TOP">
			<page:apply-decorator id="top" name="top"></page:apply-decorator>
		</div>
		<!-- top : E -->
	
 
        <!-- content : S -->
		<div class="LEE_MAIN">
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