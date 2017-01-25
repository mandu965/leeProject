<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function() {
	});
</script>


<div class="form_table">
	<form:form commandName="cmntVO" id="cmntModForm" name="cmntModForm" action="" method="post">
		<form:hidden path="cmnt_sno" id="cmnt_sno" />
		<form:hidden path="blt_rsrc_sno" id="blt_rsrc_sno" />
		
		<table class="view wfull">
			<colgroup>
				<col class="w25p" />
				<col class="" />
			</colgroup>

			<tbody>
				<tr>
					<td class="w100p">
					<textarea id="cmnt_cntn" name="cmnt_cntn" rows="3" cols="87%">${cmntVO.cmnt_contents}</textarea>
					<form:hidden path="cmnt_contents" cssClass="w200" /> 
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>