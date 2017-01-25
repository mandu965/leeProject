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
	
	// 취소
	$("#prdtmngCancel")
		.button({icons:{primary:'ui-icon-closethick'}})
		.click(function(){
			$("#boardSearchForm").attr('action','<c:url value="/sm/prdtmng/prdtmngList.do"/>').submit();
			return false;
	});
	
	// 저장
	$("#prdtmngMod")
		.button({icons:{primary:'ui-icon-disk'}})
		.click(function(){
			$("#prdtSearchForm").attr('action','<c:url value="/sm/prdtmng/prdtmngMod.do"/>').submit();
			return false;
	});
});


</script>

</head>
<body>
	<form id="prdtSearchForm">
		<input type="hidden" id="prdt_sno" name="prdt_sno" value="${prdt.prdt_sno}"/>
	</form>
	
	<div id="kwork_contents">
		<!-- section : S -->
		<div class="section">
			<div class="detail_table">
				<!-- view : S -->
				<div class="form_table">
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
							   <td><c:out value="${prdtmngVO.lg_cate_nm}"/></td>
							   
							   <th>중분류</th>
							   <td><c:out value="${prdtmngVO.cate_nm}"/></td>
							 </tr>
							 
							 <tr>
							   <th>상품명</th>
							   <td><c:out value="${prdtmngVO.prdt_nm}"/></td>
							   <th>상품가격</th>
							   <td><c:out value="${prdtmngVO.prdt_price}"/></td>
							 </tr>
							 
							  <tr>
							   <th>설명</th>
							   <td colspan="3">${prdtmngVO.prdt_expl}</td>
							 </tr>
							 
							<tr>
							   <th>사용여부</th>
							   <td colspan="3" class="buttonset"><c:out value="${prdtmngVO.use_yn}"/></td>
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
					<div>
						<button id="prdtmngDel">삭제</button>
					    <button id="prdtmngMod">수정</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
		
</body>
</html>