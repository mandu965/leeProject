<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value="/wcom/jquery/css/progressbar.css" />" />

<script type="text/javascript">
$(document).ready(function() {
	
	$("input:checkbox[name='allBtn']").click(function(){
        $(this).parent().parent().find("#rdname").attr('disabled',true);
        $(this).parent().parent().find("#chname").attr('disabled',true);
    });


	
	/* $("input:checkbox[name='allBtn']").click(function(){
		var checkbox = $(this).parent().parent().find("#chname").val();
		//var checkbox = $(this).parent().find("#chname").val();
		//if($(this).parent().parent().find('#ans_rfr_usr_no').val()==null){
		alert(checkbox);
		var parent01 = "<input type='text' id='ans_rfr_usr_no' name='ans_rfr_usr_no' value='parent()'/>";
		var parent02 = "<input type='text' id='ans_rfr_usr_no' name='ans_rfr_usr_no' value='parent().parent()'/>";
	
		$(this).parent().parent().append(parent02);
		$(this).parent().append(parent01);
	}); */
	
	$("input:checkbox[name='chname']").click(function(){
		$(this).parent().parent().find("#rdname").attr('disabled',true);
		
	});

	$("#checkForeach").click(function(){
		//요소 찾아서 for문돌리기 
		$("input:checkbox[id='chname']").each(function(index,element){
			alert($(this).val());
		});
		
	});
	
	$("#appendBtn").click(function(){
		
		$("#appendTest").append("<div>append</div>");
		$("<div>appendTo</div>").appendTo("#appendTest");
		$("#appendTest").prepend("<div>prepend</div>");
	});
	
	//pdf파일 생성
	$("#pdfCreateBtn").click(function(){
		$("#pdfForm").attr('action','<c:url value="/techmng/pdfCreate.do"/>').submit();
		return false;
	});
	
	
	
	//json Tset
	var student = {
	name:"홍길동",
	age:"22",
	like:["수학","과학"],
	isBool:true,
	father:{
		name:"홍길동 아빠",
		age:"50"
	},
	friend:[
	    {
			name:"홍길동친구1",
			age:"22"
		},
		{
			name:"홍길동친구2",
			age:"22"
		}
	]
}
	
	console.log(student.name);
	console.log(student.age);
	console.log(student.isBool);
	console.log(student.like[0]);
	console.log(student.like[1]);
	console.log(student.father.name);
	console.log(student.father.age);
	console.log(student.friend[0].name);
	console.log(student.friend[1].name);
	
	
	//student.partner = '홍길순(파트너)';
	//console.log(student.partner);
	
	//delete item (key)
	delete student.name;//삭제된다.
	
	//push는 배열일 경우에 활용한다.
	student.friend.push({name:'홍길동친구3', age:'22'});
	console.log(student.friend[2].name);
	
	var keyname="kkk";
	student[keyname + 'postfix'] = 'value';
	console.log(student.kkkpostfix);
	console.log(student[keyname+'postfix']);
	console.log(student['kkkpostfix']);
	
	});
	
	
</script>
</head>
<body>
<table border="1">
		<colgroup>
			<col class="70px" />
			<col class="70px" />
			<col class="70px" />
		</colgroup>
		<thead>
			<tr>
				<th>구분</th>
				<th>체크박스</th>
				<th>라디오박스</th>
			</tr>
		</thead>

		<tbody>
			<tr>
			<!-- <td><input type="checkbox" id="allBtn" name="allBtn" value=""/><input type="checkbox" id="chname" name="chname" value="check0122"/></td> -->
				<td><input type="checkbox" id="allBtn" name="allBtn" value=""/></td>
				<td><input type="checkbox" id="chname" name="chname" value="check01"/></td>
				<td><input type="radio" id="rdname" name="rdname" value="radio01"/></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="allBtn" name="allBtn" value=""/></td>
				<td><input type="checkbox" id="chname" name="chname" value="check02"/></td>
				<td><input type="radio" id="rdname" name="rdname" value="radio02"/></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="allBtn" name="allBtn" value=""/></td>
				<td><input type="checkbox" id="chname" name="chname" value="check03"/></td>
				<td><input type="radio" id="rdname" name="rdname" value="radio03"/></td>
			</tr>
			<tr>
				<td><input type="checkbox" id="allBtn" name="allBtn" value=""/></td>
				<td><input type="checkbox" id="chname" name="chname" value="check04"/></td>
				<td><input type="radio" id="rdname" name="rdname" value="radio04"/></td>
			</tr>
		</tbody>
	</table>
	
	<button id="checkForeach">checkBox foreach</button>
	
	<div id="appendTest">
	<button id="appendBtn">기준Clieck</button>
	</div>
	<div id="appendTo"></div>
	
	<form id="pdfForm" name="pdfForm" action="" method="post"></form>
	<button id="pdfCreateBtn">PDF파일생성</button>

</body>
</html>