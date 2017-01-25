<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/bb/pctbbs/infocommuni/" scope="request"/>

<c:set var="NON_ACCOUNT" value="<%= LeeCodeConstants.CASH_NON_ACCOUNT %>" scope="request" />
<c:set var="ACCOUNT" value="<%= LeeCodeConstants.CASH_ACCOUNT %>" scope="request" />

<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#div_nonaccount").css('display', 'none');
			$("#div_account").css('display', 'none');
			
			$("#Non_account").click(function(){
				if($(this).is(':checked')){
					$("#div_nonaccount").css('display', '');
					$("#div_account").css('display', 'none');
					$("#pay_gubun2").val($(this).val());
					
				}
			});
			

			$("#account").click(function(){
				if($(this).is(':checked')){
					$("#div_nonaccount").css('display', 'none');
					$("#div_account").css('display', '');
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
				<legend>현금결제방법</legend>
				<label><input type="radio" id="Non_account" name="cash_gubun" value="${NON_ACCOUNT}">무통장 입금</label>
				<label><input type="radio" id="account" name="cash_gubun" value="${ACCOUNT}">실시간계좌이체</label>
	
			</fieldset>
				

				<div id="div_nonaccount">
					<h2>무통장 입금 선택</h2>
				</div>
				
				<div id="div_account">
					<h2>실시간 계좌체 선택</h2>
				</div>
			</form>
		</div>
	</div>
	
	
</body>

</html>
		