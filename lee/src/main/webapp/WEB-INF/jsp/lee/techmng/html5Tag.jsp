<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>

<!DOCTYPE html>
<html lang="ko">
<head>


<meta charset="utf-8">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/wcom/css/leeWork.css" />" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/wcom/jquery/css/jquery-ui-1.9.2.custom.css"/>" />

<script type="text/javascript"
	src="<c:url value="/wcom/jquery/js/jquery-2.1.4.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/wcom/jquery/js/jquery-ui-1.9.2.custom.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/wcom/js/lee.util.js" />"></script>

<title>Html5 Tag</title>

<style>
.top ul {
	list-style: none;
}

.top li {
	float: left;
	padding-right: 15px;
}

a {
	text-decoration: none;
}
</style>

</head>
<body>

	<%-- <nav class="top">
<ul>
<li><a href="<c:url value="/main/main.do"/>">Home</a></li>
<li><a href="#">첫번째</a></li>
<li><a href="#">두번째</a></li>
<li><a href="#">세번째</a></li>
</ul>
</nav> --%>

	<header> [header]머리말(제목) 지정 </header>

	<nav>
		<h2>[nav]문서를 연결하는 에비게이션 링크(h2테그)</h2>
		<ul>
			<li><a href="/lee/main/main.do">홈</a></li>
			<li><a href="#">일반의류</a></li>
			<li><a href="#">스포츠</a></li>
			<li><a href="#">한복</a></li>
		</ul>
	</nav>
	
	<article>
	
		<header>
			<h2>article안의 header</h2>
		</header>
		
		<p>
		23일 발표된 여론조사 업체 '리얼미터'의 11월 3주 차 정례조사에 따르면, 박 대통령의 지지율은 전주 대비 0.6%p 상승한 46.2%를 기록했다. 
		'국정수행을 잘못하고 있다'는 부정평가는 1.4%p 하락한 48.3%였고 '모름/무응답' 답변은 5.5%였다. 새누리당의 지지율 곡선도 이와 비슷했다. 
		이번 조사에서 새누리당의 지지율은 전주 대비 1.5%p 오른 42.5%를 기록했다. 반면 새정치민주연합의 지지율은 전주 대비 0.2%p 하락한 26.8%였다. 
		</p>
		
		<section>
			<h3>article 안의 section</h3>
			<h3>댓글</h3>
			
			<article>
				<p>article 요소로 댓글 표현</p>
				<footer><p><time>2015-12-01</time>댓댓</p></footer>
			</article>
			
			<article>
				<p>article 요소로 댓글 표현</p>
				<footer><p><time>2015-12-01</time>댓댓</p></footer>
			</article>
			
			<article>
				<p>article 요소로 댓글 표현</p>
				<footer><p><time>2015-12-01</time>댓댓</p></footer>
			</article>
			
		</section>

		<section>
			[section]콘텐츠영역
			<article>[article]실제 콘텐츠 내용, section 태그안에 위치</article>
			
			<article>
				<p><mark>&lt;mark&gt;</mark> 요소입니다.</p>
				<time datetime="2015-11-15T14:00:00">2015년 11월 15일 오후2시 </time>
				
				<p>다운로드 진행 : <progress max="100"></progress></p>
				<p>진행상황 : <meter min="0" max="100" value="20" low="30" high="70">20%</meter></p>
				
				<details>
					<summary>sw 무료교육</summary>
					<ul>
						<li>교육과목 : 사물인터넷</li>
						<li>강사 : 이만복</li>
						<li>수강료 : 무료</li>
						<li>교육일자 : <time datetime="2015-11-25">2015년 11월 25일</time></li>
					</ul>
				</details>
			</article>
			
			<article>
				<figure>
				 <img src="../wcom/images/mime/alz.gif"  alt="알집"/>
				 <img src="../wcom/images/mime/docx.gif" alt="워드문서"/>
				 <img src="../wcom/images/mime/pdf.gif" alt="pdf"/>
				 <figcaption>[figcaption]확장자 이미지</figcaption>
				</figure>
			</article>
			
			<article>
				<form>
				<fieldset>
					<legend>필드 힌트 보여주기</legend>
					<ul>
						<li><label for="req">required</label><input type="text" id="req" required="required" /></li>
						<li><label for="phone">핸드폰 번호</label><input type="tel" id="phone" placeholder="01011112222"/></li>
						<li><label for="email">이메일</label><input type="email" id="email" placeholder="abc.@naver.com"/></li>
						<li><label for="no">학번</label><input type="email" id="no" placeholder="20091885" autocomplete="on"/></li>
					</ul>
					<input type="text" id="id" autofocus="autofocus" value="autofocus"/>
	
				</fieldset>
				
				<fieldset>
					<legend>List</legend>
					<p>
						<label for="fr">과일종류 : </label><input type="text" id="fr" list="frList"/>
						<datalist id="frList">
							<option value="사과">
							<option value="포도">
							<option value="딸기">
							<option value="참외">
						</datalist>
					</p>
				</fieldset>
				
				<fieldset>
					<legend>패턴속성</legend>
					<p>
						<label for="tel">주민등록번호 : </label><input type="text" id="jumin" pattern="\d{6} - \d{7}" title="주민 번호는 앞자리 6자리 - 뒷자리 7자리를 입력하셔야 합니다."/>
					</p>
				</fieldset>
				<input type="number" id="count" name="count"/>
				<p><input type="submit" value="전송"/></p>
			</form>
				
			</article>
			
			
		</section>
	
	</article>


	<aside>[aside]본문 이외의 내용 표시</aside>

	<footer> [footer]제작 정보와 저작권 정보 표시
	
		<address>7019080@naver.com</address>
        <p>Copyright &copy Moon Jung Min. All Reserved.</p> 
	
	</footer>






</body>
</html>