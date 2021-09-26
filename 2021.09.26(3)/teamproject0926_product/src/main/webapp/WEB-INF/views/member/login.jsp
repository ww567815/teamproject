<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Cake Template">
<meta name="keywords" content="Cake, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Cake | Template</title>

<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@400;500;600;700;800;900&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400;500;600;700;800;900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<%-- include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

	<!-- Offcanvas Menu Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="offcanvas-menu-wrapper">
		<div class="offcanvas__cart">
			<div class="offcanvas__cart__links">
				<a href="#" class="search-switch"><img src="/resources/img/icon/search.png"
					alt=""></a> <a href="#"><img src="/resources/img/icon/heart.png" alt=""></a>
			</div>
			<div class="offcanvas__cart__item">
				<a href="#"><img src="img/icon/cart.png" alt=""> <span></span></a>
				<div class="cart__price">
					Cart: <span>₩0.00</span>
				</div>
			</div>
		</div>
		<div class="offcanvas__logo">
			<a href="/index"><img src="img/logo_widthVer.png" alt=""></a>
		</div>
		<div id="mobile-menu-wrap"></div>
		<div class="offcanvas__option">
			<ul>
				<li>USD <span class="arrow_carrot-down"></span>
					<ul>
						<li>EUR</li>
						<li>USD</li>
					</ul>
				</li>
				<li>ENG <span class="arrow_carrot-down"></span>
					<ul>
						<li>Spanish</li>
						<li>ENG</li>
					</ul>
				</li>
				<li><a href="#">Sign in</a> <span class="arrow_carrot-down"></span></li>
			</ul>
		</div>
	</div>
	<!-- Offcanvas Menu End -->

   <!-- Header Section Begin -->
   <header class="header">
      <div class="header__top">
         <div class="container">
            <div class="row">
               <div class="col-lg-12">
                  <div class="header__top__inner">
                     <div class="header__top__left">
                        <ul>
                           <li><a href="/member/join">회원가입</a></li>
                           <li><a href="/member/login">로그인</a></li>
                        </ul>
                     </div>
                     <div class="header__logo">
                        <a href="/index"><img src="/resources/img/logo_widthVer.png"
                           alt=""></a>
                     </div>
                     <div class="header__top__right">
                        <div class="header__top__right__links">
                           <a href="#" class="search-switch"><img
                              src="/resources/img/icon/search.png" alt=""></a> <a href="#"><img
                              src="/resources/img/icon/heart.png" alt=""></a>
                        </div>
                        <div class="header__top__right__cart">
                           <a href="#"><img src="img/icon/cart.png" alt=""> <span></span></a>
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
                     <li><a href="#">물품</a></li>
                     <li><a href="#">마이페이지</a>
                        <ul class="dropdown">
                           <li><a href="#">단호박 온도</a></li>
                           <li><a href="#">장바구니</a></li>
                           <li><a href="#">회원정보수정</a></li>
                           <li><a href="#">비밀번호변경</a></li>
                        </ul></li>
                  </ul>
               </nav>
            </div>
         </div>
      </div>
   </header>
   <!-- Header Section End -->

	<!-- Breadcrumb Begin -->
	<div class="breadcrumb-option">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6">
					<!-- <div class="breadcrumb__text"> -->
					<div>
						<h2>로그인</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">

				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<form action="/member/login" method="POST">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											아이디<span>*</span>
										</p>
										<input type="text" name="id">
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											비밀번호<span>*</span>
										</p>
										<input type="password" name="passwd">
									</div>
								</div>
							</div>

							<button class="btn btn-lg bg-warning">로그인</button>
							<label for="acc"> 로그인 유지 <input type="checkbox" id="acc" name="rememberMe">
								<span class="checkmark"></span>
							</label>
						</div>

					</div>
				</form>
			</div>
		</div>
	</section>
	<!-- Checkout Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer set-bg" data-setbg="img/footer-bg.jpg">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="footer__widget">
						<h6>WORKING HOURS</h6>
						<ul>
							<li>Monday - Friday: 08:00 am – 08:30 pm</li>
							<li>Saturday: 10:00 am – 16:30 pm</li>
							<li>Sunday: 10:00 am – 16:30 pm</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="footer__about">
						<div class="footer__logo">
							<a href="#"><img src="img/footer-logo.png" alt=""></a>
						</div>
						<p>Lorem ipsum dolor amet, consectetur adipiscing elit, sed do
							eiusmod tempor incididunt ut labore dolore magna aliqua.</p>
						<div class="footer__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-instagram"></i></a> <a href="#"><i
								class="fa fa-youtube-play"></i></a>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-md-6 col-sm-6">
					<div class="footer__newslatter">
						<h6>Subscribe</h6>
						<p>Get latest updates and offers.</p>
						<form action="#">
							<input type="text" placeholder="Email">
							<button type="submit">
								<i class="fa fa-send-o"></i>
							</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="copyright">
			<div class="container">
				<div class="row">
					<div class="col-lg-7">
						<p class="copyright__text text-white">
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
							Copyright &copy;
							<script>
								document.write(new Date().getFullYear());
							</script>
							All rights reserved | This template is made with <i
								class="fa fa-heart" aria-hidden="true"></i> by <a
								href="https://colorlib.com" target="_blank">Colorlib</a>
							<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
						</p>
					</div>
					<div class="col-lg-5">
						<div class="copyright__widget">
							<ul>
								<li><a href="#">Privacy Policy</a></li>
								<li><a href="#">Terms & Conditions</a></li>
								<li><a href="#">Site Map</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- Footer Section End -->

	<!-- Search Begin -->
	<div class="search-model">
		<div class="h-100 d-flex align-items-center justify-content-center">
			<div class="search-close-switch">+</div>
			<form class="search-model-form">
				<input type="text" id="search-input" placeholder="Search here.....">
			</form>
		</div>
	</div>
	<!-- Search End -->

	<!-- Js Plugins -->
	<jsp:include page="/WEB-INF/views/include/javascripts.jsp" />
</body>

</html>