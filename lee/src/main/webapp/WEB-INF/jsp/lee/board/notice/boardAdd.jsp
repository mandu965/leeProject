<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<c:set var="CTX_PATH" value="/board/notice/" scope="request"/>

<c:set var="YES" value="<%= LeeCodeConstants.YES %>" scope="request" />
<c:set var="NO" value="<%= LeeCodeConstants.NO %>" scope="request" />
	
<!DOCTYPE html>
<html>
<head>

	<script type="text/javascript" src="<c:url value="/wcom/se2/js/HuskyEZCreator.js" />" charset="utf-8"></script>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.validate.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/js/lee.calendar.js" />" ></script>

	<!-- 첨부파일 모듈 : S -->
	<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/uploadify.css" />" />
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.include-min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.uploadify.v2.1.4.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/jquery/js/swfobject.js" />"></script>
	<script type="text/javascript" src="<c:url value="/wcom/js/lee.attach.js" />"></script>
	<!-- 첨부파일 모듈 : E -->


<script type="text/javascript">
	$(document).ready(function() {
		$('.buttonSet').buttonset();
		$(".popupSet").css("display", "none");
		
		// 공지시작일자, 공지마감일자 달력플러그인
		$("#popup_stt_dt").datepicker({defaultDate: new Date()});
		$("#popup_stt_dt").dateFormatText();
		$("#popup_end_dt").datepicker({defaultDate: new Date()});
		$("#popup_end_dt").dateFormatText();
		
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
		
		
		// 유효성
		$("#boardForm").validate({
			rules: {
				"bbs_title" : "required",
			},
			messages: {
				"bbs_title" : {required: "제목을 입력해주세요."},
			},
			submitHandler: function (frm) {
                   frm.submit();
               }
		});
		
		//저장
		$("#boardBtnAdd")
		.button({icons:{primary:'ui-icon-disk'}})
		.click(function(){
			// Smart Editor V2.0 의 버그중 하나로(IE만 해당)
			// 사진 업로드후 보이지않는 특수문자(물음표)가 태그에 추가되서, 이걸 삭제해줌.
			var sHTML = oEditors.getById["contents"].getIR();
			var questMask = unescape("%uFEFF");
			sHTML = sHTML.split(questMask).join("");
								
			$("#bbs_contents").val(sHTML);

			
			$("#boardForm").attr('action','<c:url value="${CTX_PATH}boardAdd.do"/>').submit();
			return false;
		});
		
		// 팝업공지 선택
		$("#main_ntc_yn").click(function(){
			if($(this).is(':checked')){
				$(".popupSet").css("display", "");
				$("#popup_stt_dt").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
				$("#popup_end_dt").val($.datepicker.formatDate($.datepicker.ATOM, new Date()));
			}else{
				$(".popupSet").css("display", "none");
				$("#popup_stt_dt").val("");
				$("#popup_end_dt").val("");
			}
		});
		
		
		
		$("#boardBtnCancel")
		.button({icons:{primary:'ui-icon-cancel'}})
		.click(function(){
			$("#boardSearchForm").attr('action','<c:url value="${CTX_PATH}boardList.do"/>').submit();
			return false;
		});

		$().lee_defaultAttachInit('attach', 'attach-queue', 'leeFile', 100*1024*1024);
	});
	
	
</script>



</head>
<body>
<form id="boardSearchForm">
<input type="hidden" id="bbs_sno" name="bbs_sno" value="${boardVO.bbs_sno}"/>
</form>
	<div id="kwork_contents">
	
		
		
		<!-- section : S -->
		<div class="section">
			<div class="detail_table">
			
				<!-- view : S -->
				<div class="form_table">
					<form:form commandName="boardVO" id="boardForm" name="boardForm" action="" method="post">

						<form:hidden path="bbs_sno" value="${bbs_sno}" />      <!-- 구분값 좀더 유기적으로 처리 요망 -->
						<%-- <input type="hidden" name="reg_usr_no" value="<c:out value ='${loginSession_no}' />"/> <!-- 로그인 사용자 번호 --> --%>
						
						<table class="view wfull">
							<colgroup>
								<col class="w15p" />
								<col class="w85p" />
							</colgroup>
							<tbody>
								<tr>
									<th class="required">*제목</th>
									<td class="buttonSet">
										<form:input path="bbs_title" id="bbs_title" cssClass="w400"/>
										<%-- <c:if test="${boardInfo.ntc_yn == YES}"> --%>
											<form:checkbox path="ntc_yn" id="ntc_yn" value="${YES}"/><label for="ntc_yn" style="font-size:0.9em;">공지</label>
										<%-- </c:if> --%>
										<%-- <c:if test="${ckf:isAdmin()}"> --%>
										   <form:checkbox path="main_ntc_yn" id="main_ntc_yn" value="${YES}" /><label for="main_ntc_yn" style="font-size:0.9em;">팝업공지</label>
										<%-- </c:if> --%>
									</td>
								</tr>
								<tr class="popupSet">
								  <th>크기</th>
								   <td><form:input path="popup_width" id="popup_width" cssClass="w50"/> x
								       <form:input path="popup_height" id="popup_height" cssClass="w50"/>(너비 x 높이)</td>
								</tr>
								
								<tr class="popupSet">
								  <th>기간</th>
								   <td><form:input path="popup_stt_dt" cssClass="w70"/>부터 
								       <form:input path="popup_end_dt" cssClass="w70"/>까지</td> 
								  </tr>
							
								
								<tr class="popupSet">
								  <th>위치</th>
								  <td><form:input path="popup_x_pos" id="popup_x_pos" cssClass="w50"/>,
								      <form:input path="popup_y_pos" id="popup_y_pos" cssClass="w50"/>(x축, y축)</td>
								</tr>
								<tr>
									<th>내용</th>
									<td>
										<textarea name="contents" id="contents" rows="10" cols="50" style="width:98%; height:412px;"></textarea>
										<form:hidden path="bbs_contents" id="bbs_contents"/>
									</td>
								</tr>
								
								<tr>
									<th>첨부파일</th>
									<td>
										<div id="attach-queue"></div>
										<input id="attach" type="file"/>
									</td>
								</tr> 
						 	</tbody>
						</table>
					</form:form>
				</div>
				<!-- view : E -->
				
				<!-- button : S -->
				<div class="buttons">
					<div class="buttonLeft">
						<button id="boardBtnCancel">취소</button>
					</div>
					<div class="buttonRight">
						<button id="boardBtnAdd">저장</button>
					</div>
				</div>
				<!-- button : E -->
			
			</div>
		</div>
		<!-- section : E -->
		
	</div>
	
</body>

</html>