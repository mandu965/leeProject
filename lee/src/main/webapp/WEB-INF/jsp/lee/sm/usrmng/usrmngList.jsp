<%@page import="lee.LeeTitleConstants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/sm/usrmng/" scope="request"/>
<c:set var="PAGE_TITLE" value="<%= LeeTitleConstants.SM_USRMNG_TITLE %>" scope="request"/>
<c:set var="PAGE_NAVI" value="<%= LeeTitleConstants.SM_NAVI %>" scope="request"/>

<c:set var="USR_NORMAL" value="<%= LeeCodeConstants.USR_NORMAL %>" scope="request" />
<c:set var="USR_MANAGER" value="<%= LeeCodeConstants.USR_MANAGER %>" scope="request" />
<c:set var="USR_ADMIN" value="<%= LeeCodeConstants.USR_ADMIN %>" scope="request" />

<c:set var="MAN" value="<%= LeeCodeConstants.USER_SEX_MAN %>" scope="request" />
<c:set var="WOMAN" value="<%= LeeCodeConstants.USER_SEX_WOMAN %>" scope="request" />

<html>
<head>
<script type="text/javascript" src="<c:url value="/wcom/js/lee.dialog.js" />"></script>
<script type="text/javascript">
$(document).ready(function() {
	$(".buttonLeft").buttonset();
	$(".searchTable").buttonset();

	//검색
	$("#usrmngBtnSearch")
		.button({icons:{primary:'ui-icon-search'}})
		.click(function(){
			$('input[name=pageIndex]').val(1);
			$("#usrmngerForm").attr('action', '<c:url value="${CTX_PATH}usrmngList.do"/>').submit();
			return false;
	});
	
	//목록갯수선택
	$('input[name=pageSize][type=radio]').click(function(){
		$('input[name=pageIndex]').val(1);
		$("#usrmngerForm").attr('action', '<c:url value="${CTX_PATH}usrmngList.do"/>').submit();
		return false;
	});
	
	// 조회
	$(".usrmngMod").click(function(){
		var usr_no = $().getAnchorValue($(this),0);
		usrmngModDialog(usr_no);
		return false;
	});
	
	// 사용자 엑셀 다운로드
	$("#userXlsDownBtn")
		.button({icons:{primary:'ui-icon-tag'}})
		.click(function(){
			$("#usrmngerForm").attr('action','<c:url value="${CTX_PATH}userXlsDwon.do"/>').submit();
		return false;
	});
	
	function usrmngModDialog(usr_no){
		$.ajax({
			url: '/lee/ajax/sm/usrmng/usrmngMod.do',
			data: "usr_no=" + usr_no,
			type: 'get',
			dataType: 'html',
			error: function(){
				alert('요청하신 페이지에 문제가 있어 표시할 수 없습니다.');
			},
			success: function(html){
				$('#usrmngModLayer').html(html);
				initDialog('usrmngModLayer', 'dialogBox01', 600, 400, '사용자 수정', '');
			 	dialogButtons('usrmngModLayer', {
					'저장':function() {
						usrmngMod();
						//$("#usrmngBtnSearch").click();
					},'취소': function(){
						closeDialog('usrmngModLayer');
					}
				});
				openDialog('usrmngModLayer');
				// 이벤트 및 css 초기화
				usrmngModDialogInit();
			}
		});
	}
	
	function usrmngMod(){
		$("#usrmngForm").attr('action','<c:url value="/ajax/sm/usrmng/usrmngMod.do"/>').submit();
		return false;
	}
	
	function usrmngModDialogInit(){
		$(".buttonset").buttonset();
		/* $(".buttonset02").buttonset();
		$(".buttonset03").buttonset();
		$("#post_cd").combobox();
		
		// Add나 Mod 화면인 경우 selectbox의 넓이를 키운다.
		$(".view .ui-autocomplete-input").css("width","120px");
		
		// 유효성
		$("#usrmngForm").validate({
			rules: {
				"usr_ath_cd" : {required: true},
				"post_na_pschg_yn" : {required: true}
			},
			messages: {
				"usr_ath_cd" : {required: "권한을 선택해주세요."},
				"brd_mmbr_yn" : {required: "부서국회담당자여부를 선택해주세요."}
			},
			submitHandler: function (frm) {
				frm.submit(); 
        	}
		}); */
	}
	
});
	</script>
	
</head>
<body>
   
    
    <form:form commandName="usrmngSearchVO" id="usrmngerForm" name="usrmngerForm" method="post" action="">
      <form:hidden path="pageIndex" />
    
    
   <div id="kwork_contents">
   		<div class="page_title">
			<c:out value="사용자관리"/>
			<span class="pageLocation"><c:out escapeXml="false" value="${PAGE_NAVI}${PAGE_TITLE}"/></span>
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
								<span class="title">이름</span><form:input path="sh_usr_nm"/>
								<span class="title">ID</span><form:input path="sh_usr_id"/>
								<span class="title">성별</span>
									<form:checkbox path="sh_usr_sexList" value="${MAN}" label="남"/>
									<form:checkbox path="sh_usr_sexList" value="${WOMAN}" label="여"/>
								<span class="title">권한</span>
									<form:checkbox path="sh_usr_athList" value="${USR_NORMAL}" label="일반"/>
									<form:checkbox path="sh_usr_athList" value="${USR_MANAGER}" label="메니저"/>
									<form:checkbox path="sh_usr_athList" value="${USR_ADMIN}" label="관리자"/>			
							</td>
							<td class="searchBtn">
							    <button id="usrmngBtnSearch">검색</button>
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
				
			</div>
			<!-- button : E -->
            
            
            <button id="userXlsDownBtn">사용자목록 엑셀다운</button>
			<!-- list : S -->
			<div class="tableContents">
				<table class="list wfull">
					<colgroup>
						<col class="w5p" />
						<col class="w5p" />
						<col class="w10p" />
						<col class="w18p" />
						<col class="w10p" />
						<col class="" />
						
					</colgroup>
	
					<tbody>
						<tr>
							<td>번호</td>
							<td>성별</td>
							<td>이름</td>
							<td>ID</td>
							<td>권한</td>
							<td>주소</td>
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
										<c:choose>
											<c:when test="${vo.usr_sex == 'M' }">
												<td><c:out value="남"/></td>
											</c:when>
											<c:otherwise>
												<td><c:out value="여"/></td>
											</c:otherwise>
										</c:choose>
										<%-- <td><a href="<c:url value="${CTX_PATH}usrmngview.do?blt_rsrc_sno=${vo.usr_no} "/>"><c:out value="${vo.usr_nm}"/></a></td> --%>			
										<td><a href="#${vo.usr_no}" class="usrmngMod"><c:out value="${vo.usr_nm}"/></a></td>
										<td><c:out value="${vo.usr_id}"/></td>
										<td>
										<c:choose>
											<c:when test="${vo.usr_auth_cd == USR_NORMAL }">
												<c:out value="일반"/>
											</c:when>
											<c:when test="${vo.usr_auth_cd == USR_MANAGER }">
												<c:out value="매니저"/>
											</c:when>
											<c:otherwise>
												<c:out value="관리자"/>
											</c:otherwise>
										</c:choose>
										</td>
										<td><c:out value="${vo.usr_addr}"/></td>
										
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
					<a href="<c:url value = "${CTX_PATH}usrmngList.do?pageIndex=${(numPageGroup-2)*pageGroupSize+1 }&pageSize=${pageSize}"/>">[이전]</a>
				</c:if>

				<c:forEach var="i" begin="${startPage}" end="${endPage}">
					<a href="<c:url value="${CTX_PATH}usrmngList.do?pageIndex=${i}&pageSize=${pageSize}"/>">
					  [<font color="#000000" /> 
					  <c:if test="${pageIndex == i}"><font color="#bbbbbb" /></c:if> ${i} </font>]
					</a>
				</c:forEach>

				<c:if test="${numPageGroup < pageGroupCount}">
					<a href="<c:url value = "${CTX_PATH}usrmngList.do?pageIndex=${numPageGroup*pageGroupSize+1}&pageSize=${pageSize}"/>">[다음]</a>
				</c:if>
			</c:if>
			<!-- Pageing : E -->

		</div>
   </form:form>
   <div id="usrmngModLayer" class="dialogBox01"></div>
</body>
</html>
