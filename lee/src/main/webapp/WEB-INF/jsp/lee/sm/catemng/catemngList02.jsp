<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="<c:url value="/wcom/js/click.comm.dialog.js" />"></script>
<script type="text/javascript" src="<c:url value="/wcom/jquery/js/jquery.validate.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
			// 중분류 주제어 등록
			$("#kwdAddBtn02Ajax")
				.button({icons:{primary:'ui-icon-tag'}})
				.click(function(){
					$.ajax({
						url: '/nams/ajax/sm/kwdmng/kwdmngAdd02.do',
						data: 'cmn_cd='+$("#cmn_cd").val(),
						type: 'post',
						dataType: 'html',
						error: function(){
							$().ck_alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_kwdInsert').html(html);
							initDialog('div_kwdInsert', 'dialogBox01', 400, 200, '주제어등록', '');
							dialogButtons('div_kwdInsert', {
								'등록':function() {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 등록");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										$().ck_alert("주제어명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngAdd02Pro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_kwdInsert');
								}
							});
							openDialog('div_kwdInsert', [500, 300]);
							$("#kwd_job_cls_cd").combobox();
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 등록");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										$().ck_alert("주제어명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngAdd02Pro.do"/>').submit();
									return false;
								} 
							 });
						}
					}); 
				return false;
			});
			
			// 중분류 주제어 수정
			$(".kwdmngMod02")
				.click(function(){
					var job_cls_kwd_sno = $().getAnchorValue($(this),0);
					 $.ajax({
						url: '/nams/ajax/sm/kwdmng/kwdmngMod02.do',
						data: 'job_cls_kwd_sno='+job_cls_kwd_sno,
						type: 'post',
						dataType: 'html',
						error: function(){
							$().ck_alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_kwdInsert').html(html);
							initDialog('div_kwdInsert', 'dialogBox01', 400, 200, '주제어수정', '');
							dialogButtons('div_kwdInsert', {
								'수정':function() {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 수정");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										$().ck_alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngMod02Pro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_kwdInsert');
								}
							});
							openDialog('div_kwdInsert', [500, 300]);
							$("#kwd_job_cls_cd").combobox();
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 수정");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										$().ck_alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngMod02Pro.do"/>').submit();
									return false;
								} 
							 });
							}
						});
					return false;
			});
			
			// 중분류 주제어 삭제
			$(".kwdmngDel02")
				.click(function(){
				var job_cls_kwd_sno = $().getAnchorValue($(this),0);
				$("#job_cls_kwd_sno").val(job_cls_kwd_sno);
				$().ck_confirm('중분류 주제어를 삭제 하시겠습니까?','중분류 주제어 삭제', function(){
					$("#delForm").attr('action','<c:url value="/sm/kwdmng/kwdmngDel02.do"/>').submit();
				});
				
				return false;
				 });
			
		});
	</script>
</head>
<body>
	<div class="tableContents">
		<!-- list : S -->
		<div class="sTitle">상품카테고리</div>
		<table class="list wfull">
			<colgroup>
				<col class="" />
				<col width="80px" />
				
			</colgroup>
			<thead>
				<tr>
					<th>분류명</th>
					<th>수정/삭제</th>
				</tr>
			</thead>
		</table>
		<div class="h400 scroll" style="border-left:1px solid #eaeaea; border-right:1px solid #eaeaea;border-bottom:1px solid #eaeaea;"">
			<table class="list wfull">
				<colgroup>
					<col width="259px" />
					<col class="" />
				</colgroup>
				<tbody id="div02Table">
					<c:choose>
						<c:when test="${not empty cateList}">
							<c:forEach items="${cateList}" var="cateList" varStatus="idx">
								<tr class="${idx.count%2==1 ? 'trOdd' : 'trEven'}">
									<td class="tleft">&nbsp;<c:out value="${cateList.text}"/></td>
									<td>
										<a href='#${cateList.value}' class="kwdmngMod02"><img src='<c:url value='/wcom/images/icon/ico_mod.gif'/>' /></a>
										<a href='#${cateList.value}' class="kwdmngDel02"><img src='<c:url value='/wcom/images/icon/ico_del.gif'/>' /></a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="6"><%-- <spring:message code="click.comm.search.empty"/> --%></td></tr>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<!-- list : E -->
		<div class="right"><button id="kwdAddBtn02Ajax">추가</button></div>
	</div>

</body>
</html>