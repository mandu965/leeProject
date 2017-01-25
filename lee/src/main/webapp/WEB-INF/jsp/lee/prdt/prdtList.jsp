<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<c:set var="CTX_PATH" value="/prdt/" scope="request" />
<c:set var="imgUrl" value="<%=LeeConstants.getAttachSaveDir()%>"
	scope="request" />
<c:set var="imgfolder" value="/product/thumb/" scope="request" />

<c:set var="i" value="0" />
<c:set var="j" value="4" />

<html>
<head>

<script type="text/javascript">
	$(document).ready(
			function() {
				$(".buttonLeft").buttonset();

				//검색
				$("#prdtBtnSearch").button({
					icons : {
						primary : 'ui-icon-search'
					}
				}).click(
						function() {
							$('input[name=pageIndex]').val(1);
							$("#prdtForm").attr('action',
									'<c:url value="${CTX_PATH}prdtList.do"/>')
									.submit();
							return false;
						});

				//목록갯수선택
				$('input[name=pageSize][type=radio]').click(
						function() {
							$('input[name=pageIndex]').val(1);
							$("#prdtForm").attr('action',
									'<c:url value="${CTX_PATH}prdtList.do"/>')
									.submit();
							return false;
						});

				// 조회
				$(".prdtView").click(
						function() {
							var prdt_no = $().getAnchorValue($(this), 0);
							$("#prdt_sno").val(prdt_no);
							$("#prdtForm").attr('method', 'get');
							$("#prdtForm").attr('action',
									'<c:url value="${CTX_PATH}prdtView.do"/>')
									.submit();
							return false;
						});

				//
				$("#prdterAddBtn").button({
					icons : {
						primary : 'ui-icon-disk'
					}
				}).click(
						function() {
							$("#prdtForm").attr('method', 'get');
							$("#prdtForm").attr('action',
									'<c:url value="${CTX_PATH}prdtAdd.do"/>')
									.submit();
							return false;
						});
			});
</script>

</head>
<body>





	<div id="kwork_contents">
		<div class="page_title">
			<%-- <c:out value="${PAGE_TITLE}"/> --%>
			<c:out value="페이지 타이틀" />
			<%-- <span class="pageLocation"><c:out escapeXml="false" value="${PAGE_NAVI}${PAGE_TITLE}"/></span> --%>
			<span class="pageLocation"><c:out escapeXml="false"
					value="페이지네비----페이지 타이틀" /></span>
		</div>

		<div class="section">
			<form:form commandName="prdtSearchVO" id="prdtForm" name="prdtForm" action="">
				<form:hidden path="pageIndex" />
				<form:hidden path="prdt_sno" />
				<!-- search : S -->
				<div class="search_table">
					<table class="searchTable wfull">
						<colgroup>
							<col class="w90p" />
							<col class="w10p" />
						</colgroup>
						<tbody>
							<tr>
								<td><span class="title">상품명</span> <span class="title">가격</span>

								</td>
								<td class="searchBtn">
									<button id="prdtBtnSearch">검색</button>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- search : E -->


				<!-- button : S -->
				<div class="buttons">
					<div class="buttonLeft">
						목록수 <input type="radio" id="pageSize10" name="pageSize" value="10" <c:if test="${pageSize=='10'}"> checked="checked"</c:if> /> <label for="pageSize10">10개</label> 
							<input type="radio" id="pageSize30" name="pageSize" value="30" <c:if test="${pageSize=='30'}"> checked="checked"</c:if> /> <label for="pageSize30">30개</label>
						 	<input type="radio" id="pageSize50" name="pageSize" value="50" <c:if test="${pageSize=='50'}"> checked="checked"</c:if> /> 
							<label for="pageSize50">50개</label> 총${count}건
					</div>


				</div>
				<!-- button : E -->

				<!-- list : S -->
				<div class="tableContents">
					<table class="list wfull">
						<colgroup>
							<col class="w25p" />
							<col class="w25p" />
							<col class="w25p" />
							<col class="w25p" />

						</colgroup>

						<tbody>
							<c:choose>
								<c:when test="${not empty articleList}">
									<c:forEach items="${articleList}" var="vo" varStatus="idx">
										<c:if test="${i==0 }">
											<tr>
										</c:if>
										<!-- 0 i=0일때, tr열고 데이터 뿌린다 -->

										<td>
										<a href='#${vo.prdt_sno}' class="prdtView">
										<img src="<c:url value='${imgUrl}${imgfolder}${vo.thumb}'/>" /></a><br>
										<a href="#${vo.prdt_sno}" class="prdtView"><c:out value="${vo.prdt_nm}" /></a><br> 
										<c:out value="${vo.prdt_price}" /></td>
										<c:choose>
											<c:when test="${i!=j-1 }">
												<c:set var="i" value="${i+1}" />
											</c:when>
											<c:otherwise>
												<c:set var="i" value="0" />
												</tr>
												<!-- j-1 즉 3일때 닫는다 0,1,2,3 4개뿌리고 닫는 것이다. -->
											</c:otherwise>
										</c:choose>
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
			<c:set var="pageCount"
				value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
			<c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}" />
			<c:set var="endPage" value="${startPage + pageGroupSize-1}" />

			<c:if test="${endPage > pageCount}">
				<c:set var="endPage" value="${pageCount}" />
			</c:if>

			<c:if test="${numPageGroup > 1}">
				<a
					href="<c:url value = "${CTX_PATH}prdtList.do?pageIndex=${(numPageGroup-2)*pageGroupSize+1 }&pageSize=${pageSize}"/>">[이전]</a>
			</c:if>

			<c:forEach var="i" begin="${startPage}" end="${endPage}">
				<a
					href="<c:url value="${CTX_PATH}prdtList.do?pageIndex=${i}&pageSize=${pageSize}"/>">
					[<font color="#000000" /> <c:if test="${pageIndex == i}">
						<font color="#bbbbbb" />
					</c:if> ${i} </font>]
				</a>
			</c:forEach>

			<c:if test="${numPageGroup < pageGroupCount}">
				<a
					href="<c:url value = "${CTX_PATH}prdtList.do?pageIndex=${numPageGroup*pageGroupSize+1}&pageSize=${pageSize}"/>">[다음]</a>
			</c:if>
		</c:if>
		<!-- Pageing : E -->
	</form:form>
	</div>
	
</body>
</html>
