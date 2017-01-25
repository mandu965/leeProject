<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="CTX_PATH" value="/sm/catemng/" scope="request"/>


<html>
<head>
<script type="text/javascript" src="<c:url value="/wcom/FusionCharts/FusionCharts.js" />"></script>

 <script type="text/javascript">
$(document).ready(function() {
	
	//목록갯수선택
	$('input[name=listCount][type=radio]').click(function(){
		$("#cateForm").attr('action', '<c:url value="${CTX_PATH}catemngList.do"/>').submit();
		return false;
	});
	
	// 조회
	$(".boardBtnView").click(function(){
		alert(3);
		$('input[name=blt_rsrc_sno]').val($().getAnchorValue($(this),0));
		$("#cateForm").attr('action','<c:url value="${CTX_PATH}view.do"/>').submit();
		return false;
	});
	
	
	//등록
	$("#cateAddBtn")
	.button({icons:{primary:'ui-icon-disk'}})
	.click(function() {
		$("#cateForm").attr('action', '<c:url value="${CTX_PATH}catemngAdd.do"/>').submit();
		return false;
	});
});
	</script> 
	
</head>
<body>
   
    <form name="cateForm" id="cateForm">
    <!-- <input type="hidden" id="cate_sno" name="cate_sno" value=""/> VO에 cate_sno가 long으로 지정되어있어서 여기서 String형으로 쓰면 안된다 에러 뜸 -->
    
    
   <div id="kwork_contents">
		<div class="section">
			<!-- button : S -->
			<div class="serch_table"></div>
			<!-- button : E -->
			
          
			<!-- button : S -->
			  <div class="buttons">
				<div class="buttonLeft">

					목록수 <input type="radio" id="listCount10" name="listCount" value="10" <c:if test="${pageSize=='10'}"> checked="checked"</c:if> />
						<label for="listCount10">10개</label> 
						
						<input type="radio" id="listCount30" name="listCount" value="30" <c:if test="${pageSize=='30'}"> checked="checked"</c:if> />
						<label for="listCount30">30개</label> 
						
						<input type="radio" id="listCount50" name="listCount" value="50" <c:if test="${pageSize=='50'}"> checked="checked"</c:if> />
						<label for="listCount50">50개</label> 총
					${count}건 
				</div>
			</div>
			
			<!-- button : E -->
            
			<!-- list : S -->
			<div class="tableContents">
				<table class="list wfull"> <!-- list wfull -->
					<!-- #kwork_contents table.wfull { width:100%; } -->
					<colgroup>
						<col class="w5p" />
						<col class="w10p" />
						 <col class="w10p" />
						 <col class="w15p" /> 
						<col class="w10p" />
						<col class="w10p" />
						<col class="w15p" />
					</colgroup>

					<tbody>
						<tr>
							<td>번호</td>
							<td>성별</td>
							<td>종류</td>
							<td>카테고리명</td>
							<td>사용여부</td>
							<td>등록일시</td>

						</tr>
						
						<c:choose>
							<c:when test="${not empty articleList}">
								<c:forEach items="${articleList}" var="vo" varStatus="idx">

									<tr class="${idx.count % 2 == 1 ? 'trOdd' : 'trEven'}">
										<td><c:choose>
												<c:when test="${count > pageSize}">
													<c:out
														value="${count - pageSize*(currentPage-1) - idx.count +1}" />
												</c:when>
												<c:otherwise>
													<c:out value="${count  - idx.count +1}" />
												</c:otherwise>

											</c:choose></td>
											<td><c:out value="${vo.cate_sex}"/></td>
											<td><c:out value="${vo.cate_kind}"/></td>
											<td><c:out value="${vo.cate_nm}"/></td>
											<td><c:out value="${vo.del_yn}"/></td>
											<td><c:out value="${vo.reg_date}"/></td>

										<%-- <td><a href="lee/board/boardView.do?blt_rsrc_sno=${vo.blt_rsrc_sno}" class="boardBtnView">${vo.blt_rsrc_tlt}</a></td> --%>
										<%-- <td style="width:10px;overflow:hidden;text-overflow;ellipsis;" ><c:out value="${vo.bbs_contents}"/></td> --%>
										
									</tr>

								</c:forEach>

							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="6"><spring:message code="click.comm.search.empty" /></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>

</div>

			</div>
			<!-- list : E -->


			<!-- Paging : S -->

			<c:if test="${count > 0}">
							<c:set var="pageCount"
								value="${count / pageSize + ( count % pageSize == 0 ? 0 : 1)}" />
								
							<c:set var="startPage"
								value="${pageGroupSize*(numPageGroup-1)+1}" />
								
							<c:set var="endPage" value="${startPage + pageGroupSize-1}" />

							<c:if test="${endPage > pageCount}">
								<c:set var="endPage" value="${pageCount}" />
							</c:if>

							<c:if test="${numPageGroup > 1}">
								<a
									href="<c:url value = "/sm/catemng/catemngList.do?pageNum=${(numPageGroup-2)*pageGroupSize+1 }&listCount=${pageSize}"/>">[이전]</a>
							</c:if>

							<c:forEach var="i" begin="${startPage}" end="${endPage}">
								<a href="<c:url value="/sm/catemng/catemngList.do?pageNum=${i}&listCount=${pageSize}"/>">[ <font
									color="#000000" /> <c:if test="${currentPage == i}">
										<font color="#bbbbbb" />
									</c:if> ${i} </font>]
								</a>
							</c:forEach>


							<c:if test="${numPageGroup < pageGroupCount}">
								<a
									href="<c:url value = "/sm/catemng/catemngList.do?pageNum=${numPageGroup*pageGroupSize+1}&listCount=${pageSize}"/>">[다음]</a>
							</c:if>
						</c:if>

			<!-- Pageing : E -->
		</div>

  </form>


	 <div class="buttonRight">
		<button id="cateAddBtn">등록</button>
	 </div> 
	




</body>
</html>
