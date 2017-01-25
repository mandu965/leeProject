<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/bb/pctbbs/infocommuni/" scope="request"/>

<c:set var="CARD_NHN" value="<%= LeeCodeConstants.CARD_NHN %>" scope="request" />
<c:set var="CARD_KB" value="<%= LeeCodeConstants.CARD_KB %>" scope="request" />
<c:set var="CARD_WOORI" value="<%= LeeCodeConstants.CARD_WOORI %>" scope="request" />
<c:set var="CARD_HANA" value="<%= LeeCodeConstants.CARD_HANA %>" scope="request" />
	
<!DOCTYPE html>
<html>
<head>
	<script type="text/javascript">
		$(document).ready(function() {
			
			$("#bankSel").change(function(){
				$("#pay_gubun2").val($(this).val());
			});

			
			
		});
	</script>
</head>
<body>
	<div class="detail_table">
		<div class="form_table">
			<form  id="cashForm" name="cashForm" action="" method="post">
			
			<fieldset>
				<legend>카드결제방법</legend>
				<label><input type="radio" id="Non_account" name="cash_gubun" value="${NON_ACCOUNT}"  checked>신용/체크카드</label>
			</fieldset>
				
				
				
				<div id="">
					<select id="bankSel">
					<option value="0">선택</option>
					 <option value="${CARD_NHN}">농협</option>
					 <option value="${CARD_KB}">국민은행</option>
					 <option value="${CARD_WOORI}">우리은행</option>
					<option value="${CARD_HANA}">하나은행</option>
					</select>
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
		