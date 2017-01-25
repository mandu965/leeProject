<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<%-- 사용자권한  --%>
<c:set var="YES" value="<%= LeeCodeConstants.YES %>" scope="request" />
<c:set var="NO" value="<%= LeeCodeConstants.NO %>" scope="request" />

	<!-- section : S -->
	<div class="section">
		
		<!-- view : S -->
		<div class="form_table">
			<form:form commandName="usrmngVO" id="usrmngForm" name="usrmngForm" action="" method="post">
				<form:hidden path="usr_no"/>

				<table class="view wfull">
					<colgroup>
						<col class="w15p" />
						<col class="w35p" />
						<col class="w15p" />
						<col class="w35p" />
					</colgroup>
					
					<tbody>
						<tr>
							<th>성명</th>
							<td><form:input path="usr_nm"/></td>
							<th>ID</th>
							<td><form:input path="usr_id"/></td>
						</tr>
						<tr>
							<th>성별</th>
							<td class="buttonset">
								<form:radiobutton path="usr_sex" label="남자" value="M"/>
								<form:radiobutton path="usr_sex" label="여자" value="W"/>
							</td>
							<th>가입일자</th>
							<td><form:input path="reg_date"/></td>
						</tr>
						<tr>
							<th>연락처</th>
							<td><form:input path="usr_hp"/></td>
							<th>생일</th>
							<td><form:input path="usr_birth"/></td>
						</tr>
						<tr>
							<th>마일리지</th>
							<td colspan="3"><form:input path="usr_point"/></td>
						</tr>
						<tr>
							<th>관리자여부</th>
							<td class="buttonset">
								<form:radiobutton path="usr_auth_cd" label="Y" value="${YES}"/>
								<form:radiobutton path="usr_auth_cd" label="N" value="${NO}"/>
							</td>
							<%-- <th>매니저여부</th>
							<td class="buttonset">
								<form:radiobutton path="manager_yn" label="Y" value="${YES}"/>
								<form:radiobutton path="manager_yn" label="N" value="${NO}"/>
							</td> --%>
						</tr>
						<tr>
							<th>주소</th>
							<td colspan="3"><form:input path="usr_addr"/></td>
						</tr>
						<%-- <tr>
							<th class="required">부서국회담당자여부</th>
							<td class="buttonset02">
								<form:radiobutton path="post_na_pschg_yn" label="Y" value="${YES}"/>
								<form:radiobutton path="post_na_pschg_yn" label="N" value="${NO}"/>
							</td>
						</tr> --%>
					</tbody>
				</table>
			</form:form>
		</div>
		<!-- view : E -->
			
	</div>
	<!-- section : E -->