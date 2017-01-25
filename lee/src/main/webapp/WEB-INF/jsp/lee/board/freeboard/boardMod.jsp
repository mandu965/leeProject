<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<c:set var="CTX_PATH" value="/board/freeboard/" scope="request"/>
<!DOCTYPE>
<html>
<head>

<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/uploadify.css" />" />
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.include-min.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.uploadify.v2.1.4.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/swfobject.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/se2/js/HuskyEZCreator.js" />" charset="utf-8"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.validate.js" />"></script>
<script type="text/javascript">

$(document).ready(function(){
	
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
	
	// 저장
	$("#boardBtnMod")
		.button({icons:{primary:'ui-icon-wrench'}})
		.click(function(){
			// Smart Editor V2.0 의 버그중 하나로(IE만 해당)
			// 사진 업로드후 보이지않는 특수문자(물음표)가 태그에 추가되서, 이걸 삭제해줌.
			var sHTML = oEditors.getById["contents"].getIR();
			var questMask = unescape("%uFEFF");
			sHTML = sHTML.split(questMask).join("");
								
			$("#bbs_contents").val(sHTML);
			$("#boardForm").attr('action','<c:url value="${CTX_PATH}boardMod.do"/>').submit();
			return false;
	});
	
	// 취소
	$("#boardBtnCancel")
		.button({icons:{primary:'ui-icon-closethick'}})
		.click(function(){
			$("#boardSearchForm").attr('action','<c:url value="${CTX_PATH}boardList.do"/>').submit();
			return false;
	});
	
	
	
});

</script>
</head>
<body>

	
	<div id="kwork_contents">
		
		
		<!-- section : S -->
		<div class="section">
			<div class="detail_table">
			
				<!-- view : S -->
				<div class="form_table">
					<form:form commandName="boardVO" id="boardForm" name="boardForm"  method="post">
						<form:hidden path="blt_rsrc_sno"/>
						<form:hidden path="bbs_sno"/>
						<table class="view wfull">
							<colgroup>
								<col class="w15p" />
								<col class="w35p" />
								<col class="w15p" />
								<col class="w35p" />
							</colgroup>
							<tbody>
								<tr>
									<th>제목</th>
									<td colspan="3">
										<form:input path="bbs_title" id="bbs_title" cssClass="w200"/>
									</td>
									<%-- <form:input path="bbs_title" id="bbs_title" value="${board.bbs_title}" readonly="true" cssClass="w400"/> --%>
								</tr>
								<tr>
									<th>내용</th>  
									<td colspan="3">
										<textarea name="contents" id="contents" rows="10" cols="50" style="width:98%; height:412px; display:none;">${boardVO.bbs_contents}</textarea>
										<form:hidden path="bbs_contents" id="bbs_contents"/>
									</td>
								</tr>
								
								<!-- <tr>
									<th>첨부파일</th>
									<td>
										<div id="attach-queue"></div>
										<input id="attach" type="file"/>
									</td>
								</tr> -->
						 	</tbody>
						</table>
					</form:form>
				</div>
				<!-- view : E -->
				
				<!-- button : S -->
				<div class="buttons">
					<div class="buttonRight">
						<button id="boardBtnMod">저장</button>
						<%-- <c:if test="${ckf:isAdmin()}"> --%>
							<button id="boardBtnCancel">취소</button>
						<%-- </c:if> --%>
					</div>
				</div>
				<!-- button : E -->

			
			
		</div>
		
		<!-- section : E -->
		
	</div>
	</div>
	
</body>
</html>