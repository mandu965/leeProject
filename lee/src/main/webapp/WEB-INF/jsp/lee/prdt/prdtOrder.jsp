<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>
<c:set var="CTX_PATH" value="/prdt/" scope="request"/>

<c:set var="CASH" value="<%= LeeCodeConstants.PAY_GB_CASH %>" scope="request" />
<c:set var="CARD" value="<%= LeeCodeConstants.PAY_GB_CARD %>" scope="request" />
<c:set var="MOBILE" value="<%= LeeCodeConstants.PAY_GB_MOBILE %>" scope="request" />

<!DOCTYPE>
<html>
<head>

<script type="text/javascript">

$(document).ready(function(){
	//$("#quickBtnAdd").css('font-size','0.9em')
	$( ".buttons").buttonset();
	
	var initEnter = 0;
	
	//초기 화면 설정
	if(initEnter == 0){
		$("#addr_gubun01").click();
		$("#div_addr_gubun01").css('display', '');
		$("#div_addr_gubun02").css('display', 'none');
		initEnter=1;
	}
	
	$("#zipSearchOpenBtn")
	.button({icons:{primary:'ui-icon-cart'}})
	.click(function(){
		initDialog('div_zipCodeLyery', 'dialogBox01', 500, 400, '주소검색', '');
		$.ajax({
			cache: false,
			url: '/lee/ajax/comm/zipCodeLayer.do',
			data:'',
			type:'POST',
			dataType: 'html',
			error: function(){
				alert('실패');
			},
			success: function(html){
				$('#div_zipCodeLyery').html(html);
				
				openDialog('div_zipCodeLyery', '');
			}
		});
		 return false;
	});
	
	$("#addr_gubun01").click(function(){
		if($(this).is(':checked')){
			$("#div_addr_gubun01").css('display', '');
			$("#div_addr_gubun02").css('display', 'none');
		}
	});
	

	$("#addr_gubun02").click(function(){
		if($(this).is(':checked')){
			$("#div_addr_gubun01").css('display', 'none');
			$("#div_addr_gubun02").css('display', '');
		}
	});
	
	$("#pay_cash").click(function(){
		$.ajax({
			cache: false,
			url: '/lee/ajax/comm/cashLayer.do',
			data: '',
			type: 'POST',
			dataType: 'html',
			error: function(){
				//$().ck_alert("질의등록 화면을 불러오는데 실패했습니다.");
			},
			success: function(html){
				$('#div_payLayer').html(html);
				$("#pay_gubun2").val(0);
				$("#pay_gubun").val($("#pay_cash").val());
			}
		});
	});
	
	$("#pay_card").click(function(){
		$.ajax({
			cache: false,
			url: '/lee/ajax/comm/cardLayer.do',
			data: '',
			type: 'POST',
			dataType: 'html',
			error: function(){
				//$().ck_alert("질의등록 화면을 불러오는데 실패했습니다.");
			},
			success: function(html){
				$('#div_payLayer').html(html);
				$("#pay_gubun2").val(0);
				$("#pay_gubun").val($("#pay_card").val());
			}
		});
	});
	
	$("#pay_mobile").click(function(){
		$.ajax({
			cache: false,
			url: '/lee/ajax/comm/mobileLayer.do',
			data: '',
			type: 'POST',
			dataType: 'html',
			error: function(){
				//$().ck_alert("질의등록 화면을 불러오는데 실패했습니다.");
			},
			success: function(html){
				$('#div_payLayer').html(html);
				$("#pay_gubun2").val(0);
				$("#pay_gubun").val($("#pay_mobile").val());
			}
		});
	});
	
	
	//결제
	$("#orderAddBtn")
	.button({icons:{primary:'ui-icon-check'}})
	.click(function(){
		//상품정보
		
		//새로운 주소
		if($("#addr_gubun02").is(" :checked")){
			var usr_hp = $("#new_usr_hp01").val() + '-' + $("#new_usr_hp02").val() + '-' + $("#new_usr_hp03").val();
			var addr = $("#new_usr_addr01").val() + ' ' + $("#new_usr_addr02").val();

			$("#usr_nm").val($("#new_usr_nm").val());
			$("#addr").val(addr);
			$("#usr_hp").val(usr_hp);
		}
		
		//결제방법
		if($("#pay_gubun").val()==null || $("#pay_gubun").val().length<=0){
			alert("결제방법을 선택해 주세요.");
			return false;
		}
		
		if($("#pay_gubun2").val()==null || $("#pay_gubun2").val().length<=0 || $("#pay_gubun2").val()==0){
			alert("세부 결제방법을 선택해 주세요.");
			return false;
		}
		
		var order_end_ck = confirm('주문완료 하시겠습니까?');
		
		if(order_end_ck){
			$("#orderForm").attr('action','<c:url value="${CTX_PATH}orderAdd.do"/>').submit();	
			return false;
		}
		
		
		return false;

	});

});

</script>
</head>
<body>

	<div id="kwork_contents">
		<div class="page_title">
			<c:out value="주문결제"/>
		</div>
		
		<!-- section : S -->
		<div class="section">
			<form:form commandName="orderVO" id="orderForm" name="orderForm" action="" method="post">
				<form:hidden path="usr_nm" id="usr_nm" name="usr_nm" value="${usr_nm}"/>
				<form:hidden path="usr_no" id="usr_no" name="usr_no" value="${usr_no}"/>
				<form:hidden path="addr" id="addr" name="addr" value="${usr_addr}"/>
				<form:hidden path="usr_hp" id="usr_hp" name="usr_hp" value="${usr_hp}"/>
				<form:hidden path="prdt_sno" id="prdt_sno" name="prdt_sno" value="${prdt_sno}"/>
				<form:hidden path="prdt_count" id="prdt_count" name="prdt_count" value="${prdt_count}"/>
				<form:hidden path="prdt_nm" id="prdt_nm" name="prdt_nm" value="${prdt_nm}"/>
				<form:hidden path="total_price" id="total_price" name="total_price" value="${total_price}"/>
				<form:hidden path="pay_gubun" id="pay_gubun" name="pay_gubun"/>
				<form:hidden path="pay_gubun2" id="pay_gubun2" name="pay_gubun2"/>

				<div class="sub_title">상품정보</div>
				<div class="form_table">
					<!-- 상품정보 : S -->
					<div id="div_prdt_info">
						<table class="view wfull">
							<colgroup>
								<col class="w20p" />
								<col class="" />
							</colgroup>
							<tbody>
								<tr>
									<th>상품명</th>
									<td><c:out value="${prdt_nm}"/></td>
								</tr>
								<tr>
									<th>수량</th>
									<td><c:out value="${prdt_count}"/></td>
								</tr>
								<tr>
									<th>가격</th>
									<td><c:out value="${total_price}"/></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 상품정보 : E -->
				</div>
				
				<div class="space20"></div>
				
				<div class="buttons">
					<input type="radio" id="addr_gubun01" name="addrGubun" ><label for="addr_gubun01">기존 배송지</label>
					<input type="radio" id="addr_gubun02" name="addrGubun" ><label for="addr_gubun02">새로운 배송지</label>
				</div>
		
				<!-- 배송주소 : S -->
				<div class="form_table">
					<!-- 기본배송주소 : S -->
					<div id="div_addr_gubun01">
						<table class="view wfull">
							<colgroup>
								<col class="w20p" />
								<col class="" />
							</colgroup>
							<tbody>
								<tr>
									<th>받는분</th>
									<td><c:out value="${usr_nm}"/></td>
								</tr>
								<tr>
									<th>연락처</th>
									<td><c:out value="${usr_hp}"/></td>
								</tr>
								<tr>
									<th>주소</th>
									<td><c:out value="${usr_addr}"/></td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- 기본배송주소 : E -->
	
					<!-- 새로운 배송주소 : S -->
					<div id="div_addr_gubun02">
						<table class="view wfull">
							<colgroup>
								<col class="w20p" />
								<col class="" />
							</colgroup>
							<tbody>
									<tr>
									<th>받는분</th>
									<td><input type="text" id="new_usr_nm"></td>
								</tr>
									<tr>
									<th>연락처</th>
									<td><input type="text" id="new_usr_hp01">-<input type="text" id="new_usr_hp02">-<input type="text"id="new_usr_hp03"></td>
								</tr>
									<tr>
									<th>주소</th>
									<td>
										<button id="zipSearchOpenBtn">주소검색</button>
										<input type="text" id="new_usr_addr01" readonly style="width: 350px"><br>
										상세주소<input type="text" id="new_usr_addr02">
									</td>
								</tr>
									<tr>
							</tbody>
						</table>
					</div>
					<!-- 새로운 배송주소 : E -->
				</div>
				<!-- 배송주소 : E -->
	
				<div class="space20"></div>
	
				<!-- 결재방법 : S -->
				<div class="">
					<div class="sub_title">결제방법</div>
						<div class="buttons">
						<input type="radio" id="pay_cash" name="pay_gubun_r" value="${CASH}"/><label for="pay_cash">현금</label>
						<input type="radio" id="pay_card" name="pay_gubun_r" value="${CARD}"/><label for="pay_card">신용카드</label>
						<input type="radio" id="pay_mobile" name="pay_gubun_r" value="${MOBILE}"/><label for="pay_mobile">모바일</label>
					</div>
					<div id="div_payLayer"></div>
				</div>
				<!-- 결재방법 : E -->
				
			</form:form>
			
			<div class="space20"></div>
			
			<button id="orderAddBtn">결제하기</button>
		</div>
		<!-- section : E -->
		
		<!-- 주소창레이어 : S -->
		<div id="div_zipCodeLyery" class="dialogBox01"></div>
		<!-- 주소창레이어 : E -->
		
	</div>
	
</body>
</html>