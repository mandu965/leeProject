<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>


<div class="form_table">
	<form:form commandName="lgcatemngVO" id="lgcateForm" name="lgcateForm" action="" method="post">
		<form:hidden path="lg_cate_sno" id="lg_cate_sno" />

		
        <table class="view wfull">
			<colgroup>
				<col class="w25p" />
				<col class="" />
			</colgroup>

			<tbody>
				<tr>
					<th class="required">분류명</th>
					<td class="w50p"><form:input path="lg_cate_nm" /></td>
				</tr>

				<tr>
					<th class="required">사용 여부</th>
					<td class="buttonSet">
						<!-- <div id="use_yn"> -->
						<c:choose>
							<c:when test="${empty lgcatemngVO.use_yn}">
								<form:radiobutton path="use_yn" id="Y" value="Y" label="사용" checked="on"/>
							</c:when>
							<c:otherwise>
								<form:radiobutton path="use_yn" id="Y" value="Y" label="사용"/>
							</c:otherwise>
						</c:choose>
						<form:radiobutton path="use_yn" id="N" value="N" label="미사용" />

						<!-- </div> -->
					</td>
				</tr>
			</tbody>
		</table>
    </form:form>
</div>
