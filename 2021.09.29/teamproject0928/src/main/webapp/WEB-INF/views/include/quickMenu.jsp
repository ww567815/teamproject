<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<head>
<link rel="stylesheet" href="/resources/css/style.css" />
</head>
<script>
	function goTop() {
		$('html').scrollTop(0);
	}
	function goBottom() {
		$("html").scrollTop($(document).height());
	}
</script>

<body>
	<div class="header__banner-right">
		<div class="floar_sidebar">
			<h4>단호한 리모컨</h4>
			<ul>
				<li><a href="http://www.facebook.com"> <img width="24"
						height="24" src="${contextPath}/resources/img/facebook_icon.png">
						페이스북
				</a></li>
				<li><a href="http://twitter.com"> <img width="24"
						height="24" src="${contextPath}/resources/img/twitter_icon.png">
						트위터
				</a></li>
				<li><a href="http://www.instagram.com"> <img width="24"
						height="24" src="${contextPath}/resources/img/instagram_icon.png">
						인스타그램
				</a></li>
			</ul>
			<div class="d-inline-flex row">
			<a href="javascript:history.back();">
				<button class="btn btn-lg bg-light d-flex flex-row justify-content-center">◀</button>
			</a> <a href="javascript:history.forward();">
				<button class="btn btn-lg bg-light d-flex flex-row justify-content-center">▶</button>
			</a>
			<button class="btn btn-lg bg-light d-flex flex-row justify-content-center" onclick="goTop()">▲</button>
			<button class="btn btn-lg bg-light d-flex flex-row justify-content-center" onclick="goBottom()">▼</button>
			</div>
		</div>
	</div>
</body>