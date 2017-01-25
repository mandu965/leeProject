<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/board/freeboard/" scope="request"/>

<html>
<head>


<script type="text/javascript">
$(document).ready(function() {
	$(".buttonLeft").buttonset();
	
	//검색
	$("#boardBtnSearch")
		.button({icons:{primary:'ui-icon-search'}})
		.click(function(){
			$('input[name=pageIndex]').val(1);
			$("#boarderForm").attr('action','<c:url value="${CTX_PATH}boardList.do"/>').submit();
			return false;
	});
	
	//목록갯수선택
	$('input[name=pageSize][type=radio]').click(function(){
		$('input[name=pageIndex]').val(1);
		$("#boarderForm").attr('action', '<c:url value="${CTX_PATH}boardList.do"/>').submit();
		return false;
	});
	
	// 조회
	$(".boardBtnView").click(function(){
		$('input[name=blt_rsrc_sno]').val($().getAnchorValue($(this),0));
		$("#boarderForm").attr('action','<c:url value="${CTX_PATH}boardView.do"/>').submit();
		return false;
	});
	
	//글쓰기
	$("#boarderAddBtn")
	.button({icons:{primary:'ui-icon-disk'}})
	.click(function() {
		$("#boarderForm").attr('method','get');
		$("#boarderForm").attr('action', '<c:url value="${CTX_PATH}boardAdd.do"/>').submit();
		return false;
	});
});
	</script>
	
</head>
<body>
   
    
    <form:form commandName="boardSearch" id="boarderForm" name="boarderForm" action="">
      <form:hidden path="bbs_sno" />
      <form:hidden path="pageIndex" />
    
    
   <div id="kwork_contents">
   		<div class="page_title">
			<%-- <c:out value="${PAGE_TITLE}"/> --%>
			<c:out value="페이지 타이틀"/>
			<%-- <span class="pageLocation"><c:out escapeXml="false" value="${PAGE_NAVI}${PAGE_TITLE}"/></span> --%>
			<span class="pageLocation"><c:out escapeXml="false" value="페이지네비----페이지 타이틀"/></span>
		</div>
		
		<div class="section">
			<!-- search : S -->
				<div class="search_table">
					<table class="searchTable wfull">
						<colgroup>
							<col class="w90p"/> 
							<col class="w10p"/>
						</colgroup>
						<tbody>
							<tr>
								<td>
									<span class="title">제목</span>
									<form:input path="sh_title" cssClass="w150"/>
									
									<span class="title">작성자</span>
									<form:input path="sh_reg_usr" cssClass="w150"/>
									
									<span class="title">id</span>
									<form:input path="sh_id" cssClass="w150"/>
									
								</td>
								<td class="searchBtn">
								    <button id="boardBtnSearch">검색</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- search : E -->
			
          
			<!-- button : S -->
			  <div class="buttons">
				<div class="buttonLeft">

					목록수 <input type="radio" id="pageSize10" name="pageSize" value="10" <c:if test="${pageSize=='10'}"> checked="checked"</c:if> />
						<label for="pageSize10">10개</label> 
						
						<input type="radio" id="pageSize30" name="pageSize" value="30" <c:if test="${pageSize=='30'}"> checked="checked"</c:if> />
						<label for="pageSize30">30개</label> 
						
						<input type="radio" id="pageSize50" name="pageSize" value="50" <c:if test="${pageSize=='50'}"> checked="checked"</c:if> />
						<label for="pageSize50">50개</label> 총
					${count}건 
				</div>
				<div class="buttonRight">
					<c:if test="${not empty loginSession_no }">  <!-- 로그인 사용자만 가능 -->
				 		<div class="buttonRight">
							<button id="boarderAddBtn">글쓰기</button>
				 		</div> 
		 			</c:if>
				</div>
				
			</div>
			<!-- button : E -->
            
				<!-- list : S -->
				<div class="tableContents">
					<table class="list wfull"> <!-- list wfull -->
						<!-- #kwork_contents table.wfull { width:100%; } -->
						<colgroup>
							<col class="w5p" />
							<col class="w15p" />
							 <col class="w10p" />
							 <col class="w10p" />
							 <col class="w10p" /> 
							<col class="w10p" />
							<col class="w5p" />
						</colgroup>
	
						<tbody>
							<tr>
								<td>번호</td>
								<td>제목</td>
								<td>작성자</td>
								<td>ID</td>
								<td>등록일시</td>
								<td>조회수</td>
	
							</tr>
							
							<c:choose>
								<c:when test="${not empty articleList}">
									<c:forEach items="${articleList}" var="vo" varStatus="idx">
	
										<tr class="${idx.count % 2 == 1 ? 'trOdd' : 'trEven'}">
											<td><c:choose>
													<c:when test="${count > pageSize}"> <!-- ex) count= 11, pageSize=10 -->
														<c:out
															value="${count - pageSize*(pageIndex-1) - idx.count +1}" /> <!-- 11,10,9,8.......... -->
													</c:when>
													<c:otherwise>
														<c:out value="${count  - idx.count +1}" />
													</c:otherwise>
	
												</c:choose></td>
	
											<%-- <td><a href="lee/board/boardView.do?blt_rsrc_sno=${vo.blt_rsrc_sno}" class="boardBtnView">${vo.blt_rsrc_tlt}</a></td> --%>
											<td><a href="<c:url value="${CTX_PATH}boardview.do?blt_rsrc_sno=${vo.blt_rsrc_sno} "/>"><c:out value="${vo.bbs_title}"/></a></td>
											<td><c:out value="${vo.usr_nm}"/></td>
											<td><c:out value="${vo.usr_id}"/></td>
											<td><c:out value="${vo.reg_date}"/></td>
											<td><c:out value="${vo.bbs_cnt}"/></td>
											
										</tr>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td colspan="7">조회된 자료가 없습니다.</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</tbody>
					</table>
	
				</div>
				<!-- list : E -->
			</div>
			


			<!-- Paging : S -->
			<c:if test="${count > 0}">
				<c:set var="pageCount" value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
				<c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}" />
				<c:set var="endPage" value="${startPage + pageGroupSize-1}" />
				
				<c:if test="${endPage > pageCount}">
					<c:set var="endPage" value="${pageCount}" />
				</c:if>

				<c:if test="${numPageGroup > 1}">
					<a href="<c:url value = "${CTX_PATH}boardList.do?pageIndex=${(numPageGroup-2)*pageGroupSize+1 }&pageSize=${pageSize}&bbs_sno=${bbs_sno}"/>">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="<c:url value="${CTX_PATH}boardList.do?pageIndex=${i}&pageSize=${pageSize}&bbs_sno=${bbs_sno}"/>">
					  [<font color="#000000" /> 
					  <c:if test="${pageIndex == i}"><font color="#bbbbbb" /></c:if> ${i} </font>]
					</a>
				</c:forEach>

				<c:if test="${numPageGroup < pageGroupCount}">
					<a href="<c:url value = "${CTX_PATH}boardList.do?pageIndex=${numPageGroup*pageGroupSize+1}&pageSize=${pageSize}&bbs_sno=${bbs_sno}"/>">[다음]</a>
				</c:if>
			</c:if>
			<!-- Pageing : E -->
			
			
		</div>
   </form:form>
</body>
</html>
