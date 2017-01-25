<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/include/header.jsp" %>

<c:set var="YES" value="<%= LeeCodeConstants.YES %>" scope="request" />
<c:set var="NO" value="<%= LeeCodeConstants.NO %>" scope="request" />

<!DOCTYPE html>
<html>
<head>

	<script type="text/javascript">
		$(document).ready(function() {
			
			//분류 카테고리 등록
			$("#lgcateAddBtn")
				.button({icons:{primary:'ui-icon-tag'}})
				.click(function(){
					$.ajax({
						url: '/lee/ajax/sm/lgcatemng/lgcatemngAdd.do',
						data: '',
						type: 'post',
						dataType: 'html',
						error: function(){
							alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_cateInsert').html(html);
							initDialog('div_cateInsert', 'dialogBox01', 400, 200, '분류카테고리등록', '');
							dialogButtons('div_cateInsert', {
								'등록':function() {
									if($("#kwdmngForm #kwdmng01_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngAdd01Pro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_cateInsert');
								}
							});
							openDialog('div_cateInsert', [500, 300]);
							$("#use_yn").buttonset();
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwdmng01_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngAdd01Pro.do"/>').submit();
									return false;
								} 
							 });
						}
					});
				return false;
			});
			
			// 중분류 주제어 등록
			$("#cateAddBtn")
				.button({icons:{primary:'ui-icon-tag'}})
				.click(function(){
					$.ajax({
						url: '/lee/ajax/sm/catemng/catemngAdd.do',
						data: 'cmn_cd='+$("#cmn_cd").val(),
						type: 'post',
						dataType: 'html',
						error: function(){
							alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_cateInsert').html(html);
							initDialog('div_cateInsert', 'dialogBox01', 400, 200, '주제어등록', '');
							dialogButtons('div_cateInsert', {
								'등록':function() {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										//$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 등록");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngAdd02Pro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_cateInsert');
								}
							});
							openDialog('div_cateInsert', [500, 300]);
							$("#lg_cate_sno").combobox();
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										//$().ck_confirm("대분류 주제어를 선택 하세요.","주제어 등록");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										alert("분류명을 입력하세요.");
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
			
			
			// 분류카테고리수정
			$(".lgcateMod")
				.click(function(){
					var lg_cate_sno = $().getAnchorValue($(this),0);
					$.ajax({
						url: '/lee/ajax/sm/lgcatemng/lgcatemngMod.do',
						data: 'lg_cate_sno='+lg_cate_sno,
						type: 'post',
						dataType: 'html',
						error: function(){
							alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_cateInsert').html(html);
							initDialog('div_cateInsert', 'dialogBox01', 400, 200, '분류카테고리수정', '');
							dialogButtons('div_cateInsert', {
								'수정':function() {
									if($("#kwdmngForm #kwdmng01_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngMod01Pro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_cateInsert');
								}
							});
							openDialog('div_cateInsert', [500, 300]);
							
							$(".buttonSet").buttonset();
							
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwdmng01_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/kwdmngMod01Pro.do"/>').submit();
									return false;
								} 
							 });
							}
						});
					return false;
			});
			
			// 상품카테고리수정
			$(".catemngMod")
				.click(function(){
					var cate_sno = $().getAnchorValue($(this),0);
					$.ajax({
						url: '/lee/ajax/sm/catemng/catemngMod.do',
						data: 'cate_sno='+cate_sno,
						type: 'post',
						dataType: 'html',
						error: function(){
							alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
						},
						success: function(html){
							$('#div_cateInsert').html(html);
							initDialog('div_cateInsert', 'dialogBox01', 500, 270, '상품카테고리수정', '');
							dialogButtons('div_cateInsert', {
								'수정':function() {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										//$().ck_confirm("대분류 주제어를 선택 하세요.", "주제어 수정");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/catemngModPro.do"/>').submit();
								},'취소': function(){
									closeDialog('div_cateInsert');
								}
							});
							openDialog('div_cateInsert', [500, 300]);
							//$("#lg_cate_sno").combobox();
							//$("#lg_cate_sno").combobox();
							$(".buttonSet").buttonset();
							$("input[type=text]").keydown(function(e) {
								if(event.keyCode == 13) {
									if($("#kwdmngForm #kwd_job_cls_cd").val()=="0"){
										//$().ck_confirm("대분류 주제어를 선택 하세요.", "주제어 수정");
										return false;
									}
									if($("#kwdmngForm #kwdmng02_nm").val() == ""){
										alert("분류명을 입력하세요.");
										return false;
									}
									$("#kwdmngForm").attr('action','<c:url value="/sm/kwdmng/catemngModPro.do"/>').submit();
									return false;
								} 
							 });
							}
						});
					return false;
			});
			
			// 대분류 주제어 삭제
			$(".lgcatemngDel")
				.click(function(){
					var cmn_cd = $().getAnchorValue($(this),0);
					$("#cmn_cd").val(cmn_cd);
					/* $().ck_confirm('대분류 주제어를 삭제 하시겠습니까?','대분류 주제어 삭제', function(){
						$("#delForm").attr('action','<c:url value="/sm/lgcatemng/lgcatemngDel.do"/>').submit();
					});	 */
					return false;
				 });
				
			// 중분류 주제어 삭제
			$(".catemngDel")
				.click(function(){
					var job_cls_kwd_sno = $().getAnchorValue($(this),0);
					$("#job_cls_kwd_sno").val(job_cls_kwd_sno);
					/* $().ck_confirm('중분류 주제어를 삭제 하시겠습니까?','중분류 주제어 삭제', function(){
						$("#delForm").attr('action','<c:url value="/sm/catemng/catemngDel.do"/>').submit();
					});	 */
				return false;
				 });
		
 			$(".lacatemngView").click(function(){
				$(".lacatemngView").css({'font-weight':''});
				$(".lacatemngView").css({'color':'#2D75C4'});
				
				$(this).css({'font-weight':'bold'});
				$(this).css({'color':'red'});
			
				var lg_cate_sno = $().getAnchorValue($(this),0);
				//$("#cmn_cd").val(cmn_cd);
				
				$.ajax({
					url: '/lee/ajax/sm/catemng/catemngList.do',
					type: 'post',
					data: 'lg_cate_sno='+lg_cate_sno,
					dataType: 'html',
					error: function(){
						alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
					},
					success: function(html){
						$('#catemngList').html(html);
					}
				});
				
				return false;
			});
			
 			
		});
	</script>
</head>
<body>
<%-- <form:form commandName="kwdmngVO" id="delForm" name="delForm" action="" method="post">
<form:hidden path="cmn_cd" id="cmn_cd"/>
<form:hidden path="job_cls_kwd_sno" id="job_cls_kwd_sno"/>
</form:form> --%>

	<div id="kwork_contents">
		<div class="page_title">
			<c:out value="${PAGE_TITLE}"/>
			<span class="pageLocation"><c:out escapeXml="false" value="${PAGE_NAVI}${PAGE_TITLE}"/></span>
		</div>
	
		<!-- section : S -->
		<div class="section">
	

			<div class="space10"></div>

			<div id="catemngAllList" class="tabs">
			
				<!-- lgcateDiv : S -->
				<div id="kwdDiv01" class="left w50p">
					<div class="tableContents">
					<div class="sTitle">분류카테고리</div>
						<!-- list : S -->
						<table class="list wfull">
							<colgroup>
								<col width="343px" />
								<col class="" />
							</colgroup>
							<thead>
								<tr>
									<th>분류명</th>
									<th>수정/삭제</th>
								</tr>
							</thead>
						</table>
						
						<div class="h400 scroll" style="border-left:1px solid #eaeaea; border-right:1px solid #eaeaea;border-bottom:1px solid #eaeaea;">
							<table class="list wfull">
								<colgroup>
								<col width="343px" />
								<col class="" />
									
								</colgroup>
								<tbody>
									<c:forEach items="${lgcateList}" var="lgcateList" varStatus="idx">
										<tr class="${idx.count % 2 == 1 ? 'trOdd' : 'trEven'}">
										<td><a href="#${lgcateList.value}" class="lacatemngView"><c:out value="${lgcateList.text}"/></a></td>
										<td>
											<a href='#${lgcateList.value}' class="lgcateMod"><img src='<c:url value='/wcom/images/icon/ico_mod.gif'/>' /></a>
											<a href='#${lgcateList.value}' class="lgcateDel"><img src='<c:url value='/wcom/images/icon/ico_del.gif'/>' /></a>
										</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<!-- list : E -->
					</div>
					<div class="right"><button id="lgcateAddBtn">추가</button></div>
				</div>
				<!-- lgcateDiv : E -->
				
				<!-- catemngList : S -->
				<div id="catemngList" class="right w40p">
					<div class="tableContents">
					<div class="sTitle">상품카테고리</div>
						<!-- list : S -->
						<table class="list wfull">
							<colgroup>
								<col width="250px" />
								<col class="" />
								
							</colgroup>
							<thead>
								<tr>
									<th>분류명</th>
									<th>수정/삭제</th>
								</tr>
							</thead>
						</table>
						<div class="h400 scroll" style="border-left:1px solid #eaeaea; border-right:1px solid #eaeaea;border-bottom:1px solid #eaeaea;">
							<table class="list wfull">
								<colgroup>
									<col width="250px" />
									<col class="" />
									
								</colgroup>
								<tbody id="div02Table">
									<c:choose>
										<c:when test="${not empty cateList}">
											<c:forEach items="${cateList}" var="vo" varStatus="idx">
												<tr class="${idx.count % 2 == 1 ? 'trOdd' : 'trEven'}">
													<td><c:out value="${vo.text}"/></td>
													<td>
														<a href='#${vo.value}' class="catemngMod"><img src='<c:url value='/wcom/images/icon/ico_mod.gif'/>' /></a>
														<a href='#${vo.value}' class="catemngDel"><img src='<c:url value='/wcom/images/icon/ico_del.gif'/>' /></a>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr><td colspan="3"><%-- <spring:message code="click.comm.search.empty"/> --%></td></tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<!-- list : E -->
					</div>
					<div class="right"><button id="cateAddBtn">추가</button></div>
				</div>
				<!-- catemngList : E -->
			</div>
			<!-- tab_div01 : E-->
			
			<!-- 주제어 추가삭제 : S -->
			<div id="div_cateInsert" class="dialogBox01"></div>
			<!-- 주제어 추가삭제 : E -->
	
		</div>
		<!-- section : E -->
		
	</div>
</body>
</html>