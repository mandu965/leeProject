<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<html>
<head>
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no,target-densitydpi=medium-dpi,minimal-ui">
<script type="text/javascript" src="<c:url value="/wcom/FusionCharts/FusionCharts.js" />"></script>

	
	<script type="text/javascript">
	alert(1);
	function RollImage(json){
	//이미지 롤링 설정값
	var config = {
		currentImg : 0,
		nextImg : 1,
		listArea : e(json.list_area),
		imageList : e(json.list_area).getElementsByTagName("DIV"),
		imgCnt : e(json.list_area).getElementsByTagName("DIV").length-1, //0부터 시작
		rollTime : json.roll_time,
		moveTime : json.move_time,
		coordX1 : 0,
		coordX2 : e(json.list_area).offsetWidth,
		coordY : e(json.list_area).offsetHeight,
		moveAt : json.moveAt,
		direction : json.direction,
		label : e(json.label),
		labelType : json.labelType
	};
	
	labelBind(config); //라벨(버튼) 바인드
	setRoll(config); //롤링 시작
	rollPause(config); //마우스 오버시 롤링 멈춤
	
	//이미지 롤링 기본 세팅하기
	function setRoll(c){
		c.coordX1 = 0;
		c.coordX2 = c.listArea.offsetWidth;
		c.coordY = c.listArea.offsetHeight;

		if(c.direction=="right" || c.direction=="down"){
			c.coordX2 = c.coordX2 * -1;
			c.coordY = c.coordY * -1;
		}
		
		c.imageList[c.nextImg].style.display = "block";
		setPosition(c);
		rollOver(c)
		//c.imageList[c.nextImg].style.left = c.coordX2+"px";
		
		c.rollTimer = setTimeout(function(){imgMove(c)},c.rollTime);
	};

	//이미지를 움직이게 한다.
	function imgMove(c){
		if(c.direction == "left" || c.direction == "right"){
			c.imageList[c.currentImg].style.left = c.coordX1 + "px";
			c.imageList[c.nextImg].style.left = c.coordX2 + "px";
		}else if(c.direction == "up" || c.direction == "down"){
			c.imageList[c.currentImg].style.top = c.coordX1 + "px";
			c.imageList[c.nextImg].style.top = c.coordY + "px";
		}
		//alert(c.imageList[c.nextImg].style.left);
		var moveAt = parseInt(c.moveAt);
		if (c.direction == "left"){
			c.coordX1 -= moveAt;
			c.coordX2 -= moveAt;
		}else if(c.direction == "right"){
			c.coordX1 += moveAt;
			c.coordX2 += moveAt;
		}else if(c.direction=="up"){
			c.coordX1 -= moveAt;
			c.coordY -= moveAt;
		}else if(c.direction=="down"){
			c.coordX1 += moveAt;
			c.coordY += moveAt;
		}
		
		//if(c.coordX1 < (-1*c.listArea.offsetWidth) ) {
		if( isNextImgRoll(c) ) {
			c.currentImg = c.nextImg;
			c.nextImg += 1;
			if(c.currentImg == c.imgCnt) c.nextImg = 0;
			clearTimeout(c.moveTimer);
			clearTimeout(c.rollTimer);
			setRoll(c);
			return;
		}
		c.moveTimer = setTimeout(function(){imgMove(c)},c.moveTime);
	};
	
	//다음 이미지 롤링 해야하는지 확인
	function isNextImgRoll(c){
		var d = c.direction;
		if(d=="left" && c.coordX2 < 0 ) return true;
		else if(d=="right" && c.coordX2 > 0) return true;
		else if(d=="up" && c.coordY < 0 ) return true;
		else if(d=="down" && c.coordY > 0) return true;

		return false
	};

	//롤링 방향에 따른 두번째 이미지 위치 좌표 설정
	function setPosition(c){
		var d = c.direction;
		if(d=="left") c.imageList[c.nextImg].style.left = c.listArea.offsetWidth+"px";
		else if(d=="right") c.imageList[c.nextImg].style.left = (-1 * c.listArea.offsetWidth) + "px";
		else if(d=="up") c.imageList[c.nextImg].style.top = c.listArea.offsetHeight + "px";
		else if(d=="down") c.imageList[c.nextImg].style.top = (-1 * c.listArea.offsetHeight) + "px";

		//alert(c.imageList[c.nextImg].style.left);
	};
	
	//onmouseover 시 움직임 멈춤
	function rollPause(c){
	//alert(c.listArea.onmouseover);
		c.listArea.onmouseover = function(){
			clearTimeout(c.rollTimer);
		}

		c.listArea.onmouseout = function(){
			//alert("c.listArea.onmouseout");
			setRoll(c);
		}
	};

	//라벨과 바인드
	function labelBind(c){
		if(c.label == null) return; //라벨을 사용하지 않으면 아래는 실행되지 않는다.
		var labels = c.label.getElementsByTagName(c.labelType);

		c.label.onmouseover = function(event){ //라벨영역에 마우스가 오면
			var evt = event || window.event;
			var t = evt.target || evt.srcElement;
			for(n in labels){ 
				if(labels[n] == t){
					//c.imageList[c.currentImg].style.display = "none";
					c.currentImg = parseInt(n);
					c.nextImg = parseInt(n)+1;
					if(c.currentImg == c.imgCnt) c.nextImg = 0;
					clearTimeout(c.rollTimer);
					viewImg(c);
					rollOver(c);
					break;
				}
			}
			//alert(event.srcElement)
		}
		
		c.label.onmouseout = function(event){
			var evt = event || window.event;
			var t = evt.target || evt.srcElement;
			for(n in labels){
				if(labels[n]==t){
					setRoll(c);
					break;
				}
			}
		}
	};

	//라벨 onmouseover 시 클래스 적용
	function rollOver(c){
		if(c.label == null) return;
		var els = c.label.getElementsByTagName(c.labelType);
		
		if(c.labelType == "img"){
			
			for(n in els){
				if(typeof els[n] == "object"){
					if(n == c.currentImg){
						els[n].src = els[n].getAttribute("oversrc");
					}else{
						els[n].src = els[n].getAttribute("outsrc");
					}
				}
			}
		}else{
			for(n in els){
				if(typeof els[n] == "object"){
					if(n == c.currentImg){
						var ocss = els[n].className;
						els[n].className = ocss+" "+els[n].getAttribute("overcss");
					}else{
						els[n].className = els[n].getAttribute("outcss");
					}
				}
			}
		}
	}

	//라벨에서 선택된 이미지 보이기
	function viewImg(c){
		//alert(c.currentImg);
		for(n=0; n<c.imgCnt+1; n++)	{
			c.imageList[n].style.display = "none";
		}
		
		c.imageList[c.currentImg].style.left = "0px";
		c.imageList[c.currentImg].style.top = "0px";
		c.imageList[c.currentImg].style.display = "block";
	};
}

function debug(t){
e("dis").innerHTML = t + "<br>";
}

//id값으로 객체 반환
function e(id){
	var o = document.getElementById(id);
	if(typeof o == undefined || o == null) { return null;}

	return o;
}
//-->
</script>
	
	

	
	
</head>
<body>

<ul class="no_label" id="label_3">
	<li><img src="http://cfs.tistory.com/custom/blog/68/684698/skin/images/1_on.jpg" oversrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/1_on.jpg" outsrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/1.jpg" /></li>
	<li><img src="http://cfs.tistory.com/custom/blog/68/684698/skin/images/2.jpg" oversrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/2_on.jpg" outsrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/2.jpg" /></li>
	<li><img src="http://cfs.tistory.com/custom/blog/68/684698/skin/images/3.jpg" oversrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/3_on.jpg" outsrc="http://cfs.tistory.com/custom/blog/68/684698/skin/images/3.jpg" /></li>
</ul>
  <div class="LEE_MAIN">
  <div class="image_list" id="image_list_3">
	<div class="images" style="display:block"><img src="/lee/wcom/images/index_img/index01.jpg" /></div>
	<div class="images"><img src="/lee/wcom/images/index_img/index02.jpg" /></div>
	<div class="images"><img src="/lee/wcom/images/index_img/index03.jpg" /></div>
</div>


		
<h1>
	Hello world!
	
	session loginSession = ${loginSession_id} 
	
</h1>
<P>  The time on the server is ${serverTime}. </P>
<div id="colum-left">
<br><br><br><br><br><br><br><br><br><br>
</div>
<div id="colum-center">
<br><br><br><br><br><br><br><br><br><br>
</div>
<div id="colum-right">
<br><br><br><br><br><br><br><br><br><br>
</div>
</div>

<script type="text/javascript">
<!--

var j3 = {
	"list_area":"image_list_3",
	"moveAt":"100",
	"roll_time":"3000",
	"move_time":"100",
	"direction":"right",
	"label":"label_3",
	labelType : "img"
};
new RollImage(j3);
//oj1.setRoll();
//-->
</script>
</body>
</html>
