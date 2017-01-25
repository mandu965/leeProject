<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/bb/pctbbs/infocommuni/" scope="request"/>

<c:set var="MOBILE_SOECK" value="<%= LeeCodeConstants.MOBILE_SOECK %>" scope="request" />
<c:set var="MOBILE_YELOPAY" value="<%= LeeCodeConstants.MOBILE_YELOPAY %>" scope="request" />
<c:set var="MOBILE_TMONEY" value="<%= LeeCodeConstants.MOBILE_TMONEY %>" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#div_soeck").css('display', 'none');
			$("#div_yelopay").css('display', 'none');
			$("#div_tmoney").css('display', 'none');
			
			$("#soeck").click(function(){
				if($(this).is(':checked')){
					$("#div_soeck").css('display', '');
					$("#div_yelopay").css('display', 'none');
					$("#div_tmoney").css('display', 'none');
					$("#pay_gubun2").val($(this).val());
				}
			});
			

			$("#yelopay").click(function(){
				if($(this).is(':checked')){
					$("#div_soeck").css('display', 'none');
					$("#div_yelopay").css('display', '');
					$("#div_tmoney").css('display', 'none');
					$("#pay_gubun2").val($(this).val());
				}
			});
			
			$("#tmoney").click(function(){
				if($(this).is(':checked')){
					$("#div_soeck").css('display', 'none');
					$("#div_yelopay").css('display', 'none');
					$("#div_tmoney").css('display', '');
					$("#pay_gubun2").val($(this).val());
				}
			});
			
			
		});
	</script>
</head>
<body>
	<div class="detail_table">
		<div class="form_table">
			<form  id="cashForm" name="cashForm" action="" method="post">
			
			<fieldset>
					<legend>필드 힌트 보여주기</legend>
					<label><input type="radio" id="soeck" name="mobile_gubun" value="${MOBILE_SOECK}">휴대폰 소액결제</label>
					<label><input type="radio" id="yelopay" name="mobile_gubun" value="${MOBILE_YELOPAY}">옐로페이</label>
					<label><input type="radio" id="tmoney" name="mobile_gubun" value="${MOBILE_TMONEY}">모바일티머니</label>
				</fieldset>
				
				
				
				<div id="div_soeck">
					<h2>휴대폰 소액결제</h2>
				</div>
				
				<div id="div_yelopay">
					<h2>옐로페이</h2>
				</div>
				
				<div id="div_tmoney">
					<h2>모바일티머니</h2>
				</div>
				
			
				<!-- <table class="view wfull">
					<colgroup>
							<col class="w100p" />
					</colgroup>
					<thead>
						
					</thead>
					<tbody>
						
						

					</tbody>	
				</table> -->
			</form>
		</div>
	</div>
	
	
</body>

</html>
		