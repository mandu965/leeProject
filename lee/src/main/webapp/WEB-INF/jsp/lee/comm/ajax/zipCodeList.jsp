<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<script type="text/javascript">

	$(document).ready(function() {	
		/* $(".selZipCode").click(function(){
			alert(1);
		}); */
		
	});
	
</script>

<!-- <div class="sTitle">검색주소</div> -->
<!-- list : S -->

<table id="zipCodeTable" class="list2 wfull" >
	<colgroup>	
		<col class="" />
	</colgroup>

	<thead>
		<tr>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${not empty zipCodelist}">
			<div >
				<c:forEach items="${zipCodelist}" var="zipCodelist" varStatus="idx">
					<tr >
						<td>
						<a href="#${zipCodelist.seq};${zipCodelist.addr}" class="selZipCode" >
						<c:out value="${zipCodelist.addr}"/></a>
						</td>
					</tr>
				</c:forEach>
				</div>
			</c:when>
		</c:choose>
	</tbody>
</table>

<!-- list : E -->