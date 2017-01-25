<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<c:set var="CTX_PATH" value="/board/notice/" scope="request"/>
<!DOCTYPE>
<html>
<head>

<script type="text/javascript" src="<c:url value="/wcom/js/lee.dialog.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/js/lee.calendar.js" />" ></script>
<script type="text/javascript">

$(document).ready(function(){
	
	boardCmntList('List');
	
	// 목록
	$("#boardBtnList")
		.button({icons:{primary:'ui-icon-document'}})
		.click(function(){
			$("#boardSearchForm").attr('action','<c:url value="${CTX_PATH}boardList.do"/>').submit();
			return false;
	});
	
	// 수정
	$("#boardBtnMod")
		.button({icons:{primary:'ui-icon-wrench'}})
		.click(function(){
			$("#boardForm").attr('action','<c:url value="${CTX_PATH}boardMod.do"/>').attr('method', 'get').submit();
			return false;
	});
	
	// 삭제
	$("#boardBtnDel")
		.button({icons:{primary:'ui-icon-trash'}})
		.click(function(){
			$("#boardForm").attr('action','<c:url value="${CTX_PATH}boardDel.do"/>').attr('method', 'post').submit();
			/* confirm('게시글 을(를) 삭제하시겠습니까?','삭제', function(){
				$("#boardForm").attr('action','<c:url value="${CTX_PATH}boardDel.do"/>').attr('method', 'post').submit();
			}); */
			return false;
	});


	function boardCmntList(gubun){
		var blt_rsrc_sno = $("#boardForm #blt_rsrc_sno").val();
		var cmnt_cntn = $("#cmnt_cntn").val();
		
		$.ajax({
			url: '/lee/ajax/comm/cmntList.do',
			data: "blt_rsrc_sno="+blt_rsrc_sno+'&cmnt_cntn='+cmnt_cntn+'&gubun='+gubun,
			type: 'POST',
			dataType: 'html',
			error: function(){
				alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
			},
			success: function(html){
				$('#boarCmntList').html(html);
				
				//등록
				$("#cmntBtnAdd")
				.button({icons:{primary:'ui-icon-document'}})
				.click(function(){
					boardCmntList('Add');
					return false;
				});
				
				//수정
				$(".cmntMod")
				.click(function(){
					
					var cmnt_sno = $().getAnchorValue($(this),0);
					
				 	$.ajax({
						url: '/lee/ajax/comm/cmntMod.do',
						data: 'cmnt_sno='+cmnt_sno,
						type: 'post',
						dataType: 'html',
						error: function(){
							alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_cmntModLayer').html(html);
							initDialog('div_cmntModLayer', 'dialogBox01', 600, 200, '코멘트 수정', '');
							$('#cmntModForm #cmnt_contents').keyup(function(){
								var val = limitCharacters($(this), 300, '코멘트');
							});
							dialogButtons('div_cmntModLayer', {
								'수정':function() {
									var contens = $('#cmntModForm #cmnt_cntn').val();
									if(contens==''){
										alert("내용을 입력 하세요.");
										return false;
									}
									$("#cmntModForm #cmnt_contents").val(contens);
									$("#cmntModForm").attr('action','<c:url value="/comm/${CTX_PATH}cmntModPro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_cmntModLayer');
								}
							});
							openDialog('div_cmntModLayer', [500, 300]);
							
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									$("#cmntModForm").attr('action','<c:url value="/comm/${CTX_PATH}cmntModPro.do"/>').submit();
									return false;
								} 
							 });
							}
						}); 
					
					return false;
					
				});
				
				//삭제
				$(".cmntDel")
				.click(function(){
					var cmnt_sno = $().getAnchorValue($(this),0);
					
					$("#cmnt_sno").val(cmnt_sno);
					
					if($('#cmntDelDialog')) $('#cmntDelDialog').remove();
					$(cmntDelHtml()).appendTo('body');
					
			  		  $("#cmntDelDialog").dialog({
			 			modal: true,
						resizable: false,
						buttons:{
							"예" : function(){
								$("#cmntForm").attr('action','<c:url value="/comm/${CTX_PATH}cmntDel.do"/>').submit();
								$("#cmnt_sno").val(0);
							},
							"아니오" : function(){
								$(this).dialog("close");
							}
						}
					});
					
					return false;
				});	
			}
			
		});
	}

	 function cmntDelHtml(){
			var str = '<div id="cmntDelDialog" title="코멘트삭제">';
		    str += '<p style="float:left; margin:15px">';
		    str += '<span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 0 0;"></span>';
		    str += '코멘트를 삭제하시겠습니까?';
		    str += '</p>';
		    str += '</div>';
		    return str;
		} 
	
	
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
					<form:form commandName="boardVO" id="boardForm" name="boardForm" >
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
									<td>
										<c:out value="${boardVO.bbs_title}"/>
									</td>
									<th>등록일</th>
									<td>
										<c:out value="${boardVO.reg_date}"/>
									</td>
									<%-- <form:input path="bbs_title" id="bbs_title" value="${board.bbs_title}" readonly="true" cssClass="w400"/> --%>
								</tr>
								<tr>
									<th>내용</th>  
									<td id="contents" colspan="3">${boardVO.bbs_contents}</td>
								</tr>
							
								
								<c:if test="${not empty atchFileList}">
								<tr>
									<th>첨부파일</th>
									<td colspan="3">
										<div class="attachView">
											<c:forEach items="${atchFileList}" var="atchFileList">
												<div class="attachViewItem">
													<img class="attachMime" src="<c:url value="/wcom/images/mime" />/${lu:extensionIcon(pageContext, '/wcom/images/mime', atchFileList.file_dsp_nm)}"/>
													<a href="<c:url value="/comm/attach/attachBbsDown.do?atch_file_sno=${atchFileList.atch_file_sno}&atch_file_no=${atchFileList.atch_file_no}"/>"><c:out value="${atchFileList.file_dsp_nm}"/></a>
													<span class="attachSize">(<c:out value="${lu:attachSizeStr(atchFileList.file_sz_byte)}"/>)</span>
												</div>
											</c:forEach>
										</div>
									</td>
								</tr>
							</c:if>
						 	</tbody>
						</table>
					</form:form>
				</div>
				<!-- view : E -->
				
				<!-- button : S -->
				<div class="buttons">
					<div class="buttonLeft">
						<button id="boardBtnList">목록</button>
					</div>
					<div class="buttonRight">
						<button id="boardBtnMod">수정</button>
						<%-- <c:if test="${ckf:isAdmin()}"> --%>
							<button id="boardBtnDel">삭제</button>
						<%-- </c:if> --%>
					</div>
				</div>
				<!-- button : E -->
				
				
				
			<!-- reply : S -->	
		 	<div class="space10"></div>
			
			<div id="boarCmntList"></div>
			<div id="div_cmntModLayer" class="dialogBox01"></div>
			
			
		</div>
		
		<!-- section : E -->
		
	</div>
	</div>
	
</body>
</html>