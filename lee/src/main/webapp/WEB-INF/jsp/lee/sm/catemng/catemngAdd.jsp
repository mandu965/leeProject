<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>


<div class="form_table">
	<form:form commandName="catemngVO" id="cateForm" name="cateForm" action="" method="post">
		<form:hidden path="cate_sno" id="cate_sno" />

		
        <table class="view wfull">
			<colgroup>
				<col class="w25p" />
				<col class="" />
			</colgroup>

			<tbody>
				<tr>
					<th class="required">분류카테고리</th>
					<td>
						<form:select path="lg_cate_sno" id="lg_cate_sno">
							<form:option value="0">-선택-</form:option>
							<c:forEach items="${lgcateList}" var="lgcateList">
								<form:option value="${lgcateList.value}"><c:out value="${lgcateList.text}" /></form:option>
							</c:forEach>
						</form:select>
					</td>
				</tr>
				<tr>
					<th class="required">분류명</th>
					<td class="w50p"><form:input path="cate_nm" /></td>
				</tr>
				<tr>
					<th class="required">성별</th>
					<td class="buttonSet">
						<div id="sex_ck">
							<c:choose>
								<c:when test="${empty catemngVO.cate_sex}">
									<form:radiobutton path="cate_sex" id="M" value="M" label="남자" checked="on"/>
								</c:when>
								<c:otherwise>
									<form:radiobutton path="cate_sex" id="M" value="M" label="남자"/>
								</c:otherwise>
							</c:choose>
							<form:radiobutton path="cate_sex" id="W" value="W" label="여자" />
						</div>
					</td>
				</tr>

				<tr>
					<th class="required">사용 여부</th>
					<td class="buttonSet">
						<div id="use_yn">
						<c:choose>
							<c:when test="${empty catemngVO.use_yn}">
								<form:radiobutton path="use_yn" id="Y" value="Y" label="사용" checked="on"/>
							</c:when>
							<c:otherwise>
								<form:radiobutton path="use_yn" id="Y" value="Y" label="사용"/>
							</c:otherwise>
						</c:choose>
						<form:radiobutton path="use_yn" id="N" value="N" label="미사용" />

						</div>
					</td>
				</tr>
			</tbody>
		</table>
    </form:form>
</div>
