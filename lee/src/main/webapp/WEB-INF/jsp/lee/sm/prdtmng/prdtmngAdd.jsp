<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<%-- <c:set var="CTX_PATH" value="/sm/freeboard/" scope="request"/> --%>


<html>
<head>
	<!-- 첨부파일 모듈 : S -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/uploadify.css" />" />
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.include-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.uploadify.v2.1.4.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/swfobject.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/js/lee.attach.js" />"></script>
	<!-- 첨부파일 모듈 : E -->

<script type="text/javascript" src="<c:url value="/wcom/se2/js/HuskyEZCreator.js" />" charset="utf-8"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	$(".buttonset").buttonset();
	
	//$("#lg_cate_sno").combobox();
	//$("#cate_sno").combobox();
	
	
	
	// Smart Editor v2.0
	 var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
		oAppRef: oEditors,
		elPlaceHolder: "contents",
		sSkinURI: "../../wcom/se2/SmartEditor2Skin.html",	
		htParams : {bUseToolbar : true,
			fOnBeforeUnload : function(){
				//로딩 전에 수행할 로직
			}
		}, //boolean
		fOnAppLoad : function(){
			//로딩이 끝난 후에 수행할 로직
			//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
		},
		fCreator: "createSEditor2"
	}); 
	
	// 취소
	$("#prdtmngCancel")
		.button({icons:{primary:'ui-icon-closethick'}})
		.click(function(){
			$("#boardSearchForm").attr('action','<c:url value="/sm/prdtmng/prdtmngList.do"/>').submit();
			return false;
	});
	
	// 저장
	$("#prdtmngAdd")
		.button({icons:{primary:'ui-icon-disk'}})
		.click(function(){
			// Smart Editor V2.0 의 버그중 하나로(IE만 해당)
			// 사진 업로드후 보이지않는 특수문자(물음표)가 태그에 추가되서, 이걸 삭제해줌.
			var sHTML = oEditors.getById["contents"].getIR();
			var questMask = unescape("%uFEFF");
			sHTML = sHTML.split(questMask).join("");
								
			$("#prdt_expl").val(sHTML);
			$("#prdtmngForm").attr('action','<c:url value="/sm/prdtmng/prdtmngAdd.do"/>').submit();
			return false;
	});
	
	$().lee_defaultAttachInit('attach', 'attach-queue', 'product', 100*1024*1024);
});


</script>

</head>
<body>

	<form:form commandName ="prdtmngVO" id="prdtmngForm" name="prdtmngForm" action="" method="post">
	
	
		<table class="view wfull">
		 <colgroup>
			<col class="w15p"/> 
			<col class="w35p"/>
			<col class="w15p"/> 
			<col class="w35p"/>
		</colgroup>
		 <tbody>
				 <tr>
				   <th>대분류</th>
				   <td>
					   <form:select path="lg_cate_sno">
					       <form:option value="0">-선택</form:option>
						   <c:forEach items="${lgcateList}" var="lgcateList">
						      <form:option value="${lgcateList.value}"><c:out value="${lgcateList.text }"/></form:option>
						   </c:forEach>
					   </form:select>
				   </td>
				   
				   <th>중분류</th>
				   <td>
					   <form:select path="cate_sno">
					       <form:option value="0">-선택</form:option>
						   <c:forEach items="${cateList}" var="cateList">
						      <form:option value="${cateList.value}"><c:out value="${cateList.text }"/></form:option>
						   </c:forEach>
					   </form:select>
				   </td>
				 </tr>
				 
				 <tr>
				   <th>상품명</th>
				   <td><form:input path="prdt_nm"/></td>
				   <th>상품가격</th>
				   <td><form:input path="prdt_price"/></td>
				 </tr>
				 
				  <tr>
				   <th>설명</th>
				   <td colspan="3"><textarea name="contents" id="contents" rows="10" cols="50" style="width:98%; height:412px; display:none;"></textarea></td>
				   <form:hidden path="prdt_expl"/>
				 </tr>
				 
				<tr>
				   <th>사용여부</th>
				   <td colspan="3" class="buttonset">
					   <form:radiobutton path="use_yn" value="Y" label="Y"/>
					   <form:radiobutton path="use_yn" value="N" label="N"/>
				   </td>
				 </tr>
				 
				 <tr>
				   <th>상품사진</th>
				   <td colspan="3">
					   <div id="attach-queue"></div>
					   <input id="attach" type="file"/>
				   </td>
				 </tr>
		 
		 </tbody>
		
		</table>
	
	</form:form>
	
	<div>
		<button id="prdtmngCancel">취소</button>
	    <button id="prdtmngAdd">저장</button>
	</div>
</body>
</html>