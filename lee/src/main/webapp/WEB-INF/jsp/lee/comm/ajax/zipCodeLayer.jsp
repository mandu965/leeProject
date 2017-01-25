<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/include/header.jsp" %>	

<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){
	
	$("#zipSearchBtn").css('font-size','0.9em')
	.button({icons:{primary:'ui-icon-search'}})
	.click(function(){
		$.ajax({
			url: '/lee/ajax/comm/zipCodeList.do',
			type:'post',
			data:'dong='+$("#dong").val(),
			dataType:'html',
			error:function(){
				
			},
			success: function(html){
				$('#div_zipCodeList').html(html);
				
				$(".selZipCode").click(function(){
					var addr = $().getAnchorValue($(this),1);
					//alert(seq);
					$("#new_usr_addr01").val(addr);
					closeDialog('div_zipCodeLyery');
					
				});
				
				
			}
			
		});
		
		return false;
	});
	

	
	
	
});

</script>
</head>
<body>
<div id="kwork_contents">
	<!-- section : S -->
	<div class="section">
		<!-- view : S -->
		찾고자 하는 주소의 동(읍/면)을 입력하신 후 검색을 누르세요. <br>
		예)서울시 강남구 역삼1동이라면, '역삼1' 혹은 '역삼1동'으로 입력해주세요.
		<br><br>
		검색어 <input type="text" id="dong" name="dong">
		<button id="zipSearchBtn" >검색</button>
		<!-- view : E -->
		
		<!-- 주소검색 : S -->
		<div id="div_zipCodeList" style="height:250px;overflow-y:auto;"></div>
		<!-- 주소검색 : E -->
	</div>
	<!-- section : E -->
</div>
</body>
</html>