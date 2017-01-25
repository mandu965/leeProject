<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/bb/pctbbs/infocommuni/" scope="request"/>

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			
			/* $('#cmnt_cntn').keyup(function(){
				var val = limitCharacters($(this), 300, '코멘트');
			}); */
		});
	</script>
</head>
<body>
	<div class="detail_table">
		<div class="sTitle">코멘트</div>
		<div class="form_table">
			<form:form commandName="cmntVO" id="cmntForm" name="cmntForm" action="" method="post">
			<form:hidden path="blt_rsrc_sno" id="blt_rsrc_sno"/>
			<form:hidden path="cmnt_sno"/>
			
				<table class="view wfull">
					<colgroup>
							<col class="w100p" />
					</colgroup>
					<thead>
						<tr>
							<td style="padding: 5px">
								<textarea id="cmnt_cntn" name="cmnt_cntn" rows="3" cols="110"></textarea>&nbsp;<button id="cmntBtnAdd">등록</button>
								<form:hidden path="cmnt_contents" id="cmnt_contents"/>
							</td>
						</tr>
					</thead>
					<tbody>
						
						<c:choose> 
							<c:when test="${not empty cmntList}">
								<c:forEach items="${cmntList}" var="vo" varStatus="index">
									<tr>
										<td style="padding: 5px"><pre style="white-space:pre-line;">${vo.cmnt_contents}</pre>
											<p class="writeinfo"><c:out value="${vo.usr_nm}"/> | <c:out value="${vo.reg_date}"/> |
											<c:if test="${loginUsrNo == vo.reg_usr_no}">
												<a href='#${vo.cmnt_sno}' class="cmntMod"><img src='<c:url value='/wcom/images/icon/ico_mod.gif'/>' /></a>
												<a href='#${vo.cmnt_sno}' class="cmntDel"><img src='<c:url value='/wcom/images/icon/ico_del.gif'/>' /></a>	
											</c:if></p>
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>

					</tbody>	
				</table>
			</form:form>
		</div>
	</div>
	
	
</body>

</html>
		