<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>

<c:set var="CTX_PATH" value="/prdt/" scope="request"/>

<c:set var="imgUrl" value="<%=LeeConstants.getAttachSaveDir()%>" scope="request" />
<c:set var="imgfolder" value="/product/thumb/"  scope="request" />
<!DOCTYPE>
<html>
<head>

<script type="text/javascript">

$(document).ready(function(){
	

	 var prdt_count = $( "#prdt_count" ).spinner();
	 prdt_count.spinner("value", 0);
	  
	//장바구니 등록
	$("#cartAdd")
	.button({icons:{primary:'ui-icon-cart'}})
	.click(function(){
		var loginyn= '${lee_loginSession}';
		
		if(loginyn == null || loginyn.length <=0){
			alert("로그인을 해주세요.");
			return false;
		}
		if(prdt_count.spinner("value") <= 0){
			alert("상품의 수량을 선택하세요");
			return false;
		}
		
		if($('#cartSelectDialog')) $('#cartSelectDialog').remove();
		
		$(cartSelecHtml()).appendTo('body');
		
		//$( "#dialog:ui-dialog" ).dialog( "destroy" );
		
  		 $("#cartSelectDialog").dialog({
 			modal: true,
			resizable: false,
			// height:190,
			//width:350, 
			buttons:{
				"예" : function(){
					$(this).dialog("close");
			 		 $.ajax({
			 			cache: false,
						url: '/lee/ajax/prdt/cartAdd.do',
						data: $("#prdtForm").serialize(),
						type:'post',
						dataType: 'json',
						error: function(){
							alert('실패');
						},
						success: function(json){
							if(json.result){
								if($('#cartMoveDialog')) $('#cartMoveDialog').remove();
								$(cartMoveHtml()).appendTo('body');
								 $("#cartMoveDialog").dialog({
									modal: true,
									resizable: false,
									buttons:{
										"예" : function(){
											$("#prdtForm").attr('method','get');
											$("#prdtForm").attr('action','<c:url value="/mp/orderList.do"/>').submit();
										},
										"아니오" : function(){
											$(this).dialog("close");
											alert("장바구니에 보관하였습니다.");
										}
									}
								 });
							}else{
								alert("장바구니 실패");
							}

						}
					});  
				},
				"아니오" : function(){
					$(this).dialog("close");
				}
			}
		});  
	});
	
	//주문 페이지 이동
	$("#orderAdd")
	.button({icons:{primary:'ui-icon-cart'}})
	.click(function(){
		
		var loginyn= '${lee_loginSession}';
		
		if(loginyn == null || loginyn.length <=0){
			alert("로그인을 해주세요.");
			return false;
		}
		
		if(prdt_count.spinner("value") <= 0){
			alert("상품의 수량을 선택하세요");
			return false;
		}
		
		if($('#orderDialog')) $('#orderDialog').remove();
		$(orderHtml()).appendTo('body');
		
  		 $("#orderDialog").dialog({
 			modal: true,
			resizable: false,
			buttons:{
				"예" : function(){
					$("#prdtForm").attr('action','<c:url value="${CTX_PATH}prdtOrderView.do"/>').submit();
					
				},
				"아니오" : function(){
					$(this).dialog("close");
				}
			}
		});  
	});
	
	 function cartSelecHtml(){
		var str = '<div id="cartSelectDialog" title="장바구니선택">';
	    str += '<p style="float:left; margin:15px">';
	    str += '<span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 0 0;"></span>';
	    str += '장바구니에 보관 하시겠습니까?';
	    str += '</p>';
	    str += '</div>';
	    return str;
	} 
	
	 function cartMoveHtml(){
		var str = '<div id="cartMoveDialog" title="장바구니이동">';
	    str += '<p style="float:left; margin:15px">';
	    str += '<span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 0 0;"></span>';
	    str += '장바구니로 이동 하시겠습니까?';
	    str += '</p>';
	    str += '</div>';
	    return str;
	} 
	 
	 function orderHtml(){
		var str = '<div id="orderDialog" title="구매페이지이동">';
		str += '<p style="float:left; margin:15px">';
		str += '<span class="ui-icon ui-icon-info" style="float:left; margin:0 7px 0 0;"></span>';
		str += '구매하시겠습니까?';
		str += '</p>';
		str += '</div>';
		return str;
	} 
	

});

</script>
</head>
<body>
	<div id="kwork_contents">
		<!-- section : S -->
		<div class="section">
			<div class="detail_table">

				<!-- view : S -->
				<div class="form_table">
					<form:form commandName="prdtVO" id="prdtForm" name="prdtForm" action="" method="post">
						<form:hidden path="prdt_sno" />
						<form:hidden path="prdt_nm" />
						<form:hidden path="prdt_price" />

						<table class="view wfull">
							<colgroup>
								<col class="" />
								<col class="w20p" />
								<col class="w20p" />
							</colgroup>
							<tbody>
								<tr>
									<td rowspan="3"><img src="<c:url value='${imgUrl}${imgfolder}${prdtVO.thumb}'/>"width="400px" height="400px" /></td>
									<td>카테고리</td>
									<td><c:out value="${prdtVO.lg_cate_nm}" /></td>
								</tr>

								<tr>

									<td>상품명</td>
									<td><c:out value="${prdtVO.prdt_nm}" /></td>
								</tr>

								<tr>

									<td>가격(원)</td>
									<td><c:out value="${prdtVO.prdt_price}" /></td>
								</tr>
								<tr>
									<td colspan="3">${prdtVO.prdt_expl}</td>
								</tr>

								<tr>
									<td colspan="3">개수 : <input type="number" id="prdt_count"
										name="prdt_count" /></td>
								</tr>

							</tbody>
						</table>
					</form:form>
				</div>
				<!-- view : E -->
			</div>
			<%-- <c:if test="${not empty loginSession_no }"> --%>
			<button id="cartAdd">장바구니</button>
			<button id="orderAdd">즉시구매</button>
			<%-- </c:if> --%>
		</div>
		<!-- section : E -->
	</div>
</body>
</html>