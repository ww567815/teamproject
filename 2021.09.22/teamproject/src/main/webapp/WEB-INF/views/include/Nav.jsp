<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Header Section Start -->
<header class="header">
	<div class="header__banner-left">
		<h3>배너자리배너자리배너자리배너자리배너자리배너자리배너자리</h3>
	</div>
	
	<%-- include quickMenu.jsp --%>
	<jsp:include page="/WEB-INF/views/include/quickMenu.jsp" />
	
	
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="header__top__inner">
						<div>
							<div class="header__top__left">
								<c:choose>
									<c:when test="${ not empty sessionScope.id }">
										<span>${ sessionScope.id } 님</span>
										<a href="/member/logout">로그아웃</a>
									</c:when>
									<c:otherwise>
										<ul>
											<li><a href="/member/join">회원가입</a></li>
											<li><a href="/member/login">로그인</a></li>
										</ul>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
						<div class="header__logo">
							<a href="/index"><img src="/resources/img/logo_widthVer.png"
								alt=""></a>
						</div>
						<div class="header__top__right">
							<div class="header__top__right__links">
								<a href="#" class="search-switch"><img
									src="img/icon/search.png" alt=""></a> <a href="#"><img
									src="/resources/img/icon/heart.png" alt=""></a>
							</div>
							<div class="header__top__right__cart">
								<a href="#"><img src="/resources/img/icon/cart.png" alt="">
									<span>0</span></a>
								<div class="cart__price">
									Cart: <span>₩0.00</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="canvas__open">
				<i class="fa fa-bars"></i>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<nav class="header__menu mobile-menu">
					<ul>
						<li><a href="/index">홈</a></li>
						<li><a href="/member/chat">채팅</a></li>
						<li><a href="/products/productsList">물품</a></li>
						<li><a href="#">마이페이지</a>
							<ul class="dropdown">
								<li><a href="#">단호박 온도</a></li>
								<li><a href="#">장바구니</a></li>
								<li><a href="/member/modify">회원정보수정</a></li>
								<li><a href="/member/passwd">비밀번호변경</a></li>
							</ul></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
</header>
<!-- Header Section End -->
