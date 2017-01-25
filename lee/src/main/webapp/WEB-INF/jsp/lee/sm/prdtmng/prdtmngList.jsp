<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/sm/prdtmng/" scope="request"/>

<html>
<head>

<script type="text/javascript">
$(document).ready(function() {
	$(".buttonLeft").buttonset();

	//검색
	$("#prdtmngBtnSearch")
		.button({icons:{primary:'ui-icon-search'}})
		.click(function(){
			$('input[name=pageIndex]').val(1);
			$("#prdtmngForm").attr('action','<c:url value="${CTX_PATH}prdtmngList.do"/>').submit();
			return false;
	});
	
	//목록갯수선택
	$('input[name=pageSize][type=radio]').click(function(){
		$('input[name=pageIndex]').val(1);
		$("#prdtmngForm").attr('action', '<c:url value="${CTX_PATH}prdtmngList.do"/>').submit();
		return false;
	});
	
	// 조회
	$(".prdtmngView").click(function(){
		var prdt_no = $().getAnchorValue($(this),0);
		$("#prdt_sno").val(prdt_no);
		$("#prdtmngForm").attr('method','get');
		$("#prdtmngForm").attr('action', '<c:url value="${CTX_PATH}prdtmngView.do"/>').submit();
		return false;
	});
	
	//
	 $("#prdtmngerAddBtn")
	.button({icons:{primary:'ui-icon-disk'}})
	.click(function() {
		$("#prdtmngForm").attr('method','get');
		$("#prdtmngForm").attr('action', '<c:url value="${CTX_PATH}prdtmngAdd.do"/>').submit();
		return false;
	});
});
	</script>
	
</head>
<body>
   
    
    <form:form commandName="prdtmngSearchVO" id="prdtmngForm" name="prdtmngForm" action="">
      <form:hidden path="pageIndex" />
      <form:hidden path="prdt_sno" />
    
    
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
								<span class="title">상품명</span>
								<span class="title">가격</span>
														
							</td>
							<td class="searchBtn">
							    <button id="prdtmngBtnSearch">검색</button>
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
						<label for="pageSize50">50개</label> 총${count}건 
				</div>
				<div class="buttonRight">
					<c:if test="${not empty lee_loginSession}">
				 		<div class="buttonRight">
							<button id="prdtmngerAddBtn">상품등록</button>
				 		</div> 
		 			</c:if>
				</div>
				
			</div>
			<!-- button : E -->
            
			<!-- list : S -->
			<div class="tableContents">
				<table class="list wfull">
					<colgroup>
						<col class="w5p" />
						<col class="w10p" />
						<col class="w10p" />
						<col class="" />
						<col class="w10p" />
						<col class="w5p" />
						<col class="w10p" />
					</colgroup>
	
					<tbody>
						<tr>
							<td>번호</td>
							<td>대분류</td>
							<td>중분류</td>
							<td>상품명</td>
							<td>가격</td>
							<td>사용여부</td>
							<td>등록일시</td>
						</tr>
							
						<c:choose>
							<c:when test="${not empty articleList}">
								<c:forEach items="${articleList}" var="vo" varStatus="idx">		
									<tr class="${idx.count % 2 == 1 ? 'trOdd' : 'trEven'}">
										<td>
											<c:choose>
												<c:when test="${count > pageSize}">
													<c:out value="${count - pageSize*(pageIndex-1) - idx.count +1}" />
												</c:when>
												<c:otherwise>
													<c:out value="${count  - idx.count +1}" />
												</c:otherwise>
											</c:choose>
										</td>
										<td><c:out value="${vo.lg_cate_nm}"/></td>
										<td><c:out value="${vo.cate_nm}"/></td>			
										<td><a href="#${vo.prdt_sno}" class="prdtmngView"><c:out value="${vo.prdt_nm}"/></a></td>
										<td><c:out value="${vo.prdt_price}"/></td>
										<td><c:out value="${vo.use_yn}"/></td>
										<td><c:out value="${vo.reg_date}"/></td>
													
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="8">조회된 자료가 없습니다.</td>
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
					<a href="<c:url value = "${CTX_PATH}prdtmngList.do?pageIndex=${(numPageGroup-2)*pageGroupSize+1 }&pageSize=${pageSize}"/>">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="<c:url value="${CTX_PATH}prdtmngList.do?pageIndex=${i}&pageSize=${pageSize}"/>">
					  [<font color="#000000" /> 
					  <c:if test="${pageIndex == i}"><font color="#bbbbbb" /></c:if> ${i} </font>]
					</a>
				</c:forEach>

				<c:if test="${numPageGroup < pageGroupCount}">
					<a href="<c:url value = "${CTX_PATH}prdtmngList.do?pageIndex=${numPageGroup*pageGroupSize+1}&pageSize=${pageSize}"/>">[다음]</a>
				</c:if>
			</c:if>
			<!-- Pageing : E -->

		</div>
   </form:form>
</body>
</html>
