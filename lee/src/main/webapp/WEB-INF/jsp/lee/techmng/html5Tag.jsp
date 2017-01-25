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
<li><a href="#">ù��°</a></li>
<li><a href="#">�ι�°</a></li>
<li><a href="#">����°</a></li>
</ul>
</nav> --%>

	<header> [header]�Ӹ���(����) ���� </header>

	<nav>
		<h2>[nav]������ �����ϴ� ������̼� ��ũ(h2�ױ�)</h2>
		<ul>
			<li><a href="/lee/main/main.do">Ȩ</a></li>
			<li><a href="#">�Ϲ��Ƿ�</a></li>
			<li><a href="#">������</a></li>
			<li><a href="#">�Ѻ�</a></li>
		</ul>
	</nav>
	
	<article>
	
		<header>
			<h2>article���� header</h2>
		</header>
		
		<p>
		23�� ��ǥ�� �������� ��ü '�������'�� 11�� 3�� �� �������翡 ������, �� ������� �������� ���� ��� 0.6%p ����� 46.2%�� ����ߴ�. 
		'���������� �߸��ϰ� �ִ�'�� �����򰡴� 1.4%p �϶��� 48.3%���� '��/������' �亯�� 5.5%����. ���������� ������ ��� �̿� ����ߴ�. 
		�̹� ���翡�� ���������� �������� ���� ��� 1.5%p ���� 42.5%�� ����ߴ�. �ݸ� ����ġ���ֿ����� �������� ���� ��� 0.2%p �϶��� 26.8%����. 
		</p>
		
		<section>
			<h3>article ���� section</h3>
			<h3>���</h3>
			
			<article>
				<p>article ��ҷ� ��� ǥ��</p>
				<footer><p><time>2015-12-01</time>���</p></footer>
			</article>
			
			<article>
				<p>article ��ҷ� ��� ǥ��</p>
				<footer><p><time>2015-12-01</time>���</p></footer>
			</article>
			
			<article>
				<p>article ��ҷ� ��� ǥ��</p>
				<footer><p><time>2015-12-01</time>���</p></footer>
			</article>
			
		</section>

		<section>
			[section]����������
			<article>[article]���� ������ ����, section �±׾ȿ� ��ġ</article>
			
			<article>
				<p><mark>&lt;mark&gt;</mark> ����Դϴ�.</p>
				<time datetime="2015-11-15T14:00:00">2015�� 11�� 15�� ����2�� </time>
				
				<p>�ٿ�ε� ���� : <progress max="100"></progress></p>
				<p>�����Ȳ : <meter min="0" max="100" value="20" low="30" high="70">20%</meter></p>
				
				<details>
					<summary>sw ���ᱳ��</summary>
					<ul>
						<li>�������� : �繰���ͳ�</li>
						<li>���� : �̸���</li>
						<li>������ : ����</li>
						<li>�������� : <time datetime="2015-11-25">2015�� 11�� 25��</time></li>
					</ul>
				</details>
			</article>
			
			<article>
				<figure>
				 <img src="../wcom/images/mime/alz.gif"  alt="����"/>
				 <img src="../wcom/images/mime/docx.gif" alt="���幮��"/>
				 <img src="../wcom/images/mime/pdf.gif" alt="pdf"/>
				 <figcaption>[figcaption]Ȯ���� �̹���</figcaption>
				</figure>
			</article>
			
			<article>
				<form>
				<fieldset>
					<legend>�ʵ� ��Ʈ �����ֱ�</legend>
					<ul>
						<li><label for="req">required</label><input type="text" id="req" required="required" /></li>
						<li><label for="phone">�ڵ��� ��ȣ</label><input type="tel" id="phone" placeholder="01011112222"/></li>
						<li><label for="email">�̸���</label><input type="email" id="email" placeholder="abc.@naver.com"/></li>
						<li><label for="no">�й�</label><input type="email" id="no" placeholder="20091885" autocomplete="on"/></li>
					</ul>
					<input type="text" id="id" autofocus="autofocus" value="autofocus"/>
	
				</fieldset>
				
				<fieldset>
					<legend>List</legend>
					<p>
						<label for="fr">�������� : </label><input type="text" id="fr" list="frList"/>
						<datalist id="frList">
							<option value="���">
							<option value="����">
							<option value="����">
							<option value="����">
						</datalist>
					</p>
				</fieldset>
				
				<fieldset>
					<legend>���ϼӼ�</legend>
					<p>
						<label for="tel">�ֹε�Ϲ�ȣ : </label><input type="text" id="jumin" pattern="\d{6} - \d{7}" title="�ֹ� ��ȣ�� ���ڸ� 6�ڸ� - ���ڸ� 7�ڸ��� �Է��ϼž� �մϴ�."/>
					</p>
				</fieldset>
				<input type="number" id="count" name="count"/>
				<p><input type="submit" value="����"/></p>
			</form>
				
			</article>
			
			
		</section>
	
	</article>


	<aside>[aside]���� �̿��� ���� ǥ��</aside>

	<footer> [footer]���� ������ ���۱� ���� ǥ��
	
		<address>7019080@naver.com</address>
        <p>Copyright &copy Moon Jung Min. All Reserved.</p> 
	
	</footer>






</body>
</html>