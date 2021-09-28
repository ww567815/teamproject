<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<meta charset="UTF-8">
<meta name="description" content="Cake Template">
<meta name="keywords" content="Cake, unica, creative, html">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>단호박마켓(SGP)</title>

<%-- include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
  <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
 <script>
google.charts.load('current', {packages: ['corechart', 'line']});
google.charts.setOnLoadCallback(drawLogScales);

function drawLogScales() {
      var data = new google.visualization.DataTable();
      data.addColumn('number', 'X');
      data.addColumn('number', '확진자수');
      data.addColumn('number', 'Cats');

      data.addRows([
        [0, 0, 0],    [1, 20, 80],   [2, 30, 45],  [3, 17, 9],   [4, 18, 10],  [5, 9, 5],
      ]);

      var options = {
        hAxis: {
          title: 'Time',
          logScale: true
        },
        vAxis: {
          title: 'Popularity',
          logScale: false
        },
        colors: ['#a52714', '#097138']
      };

      var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
      chart.draw(data, options);
    }


 </script>

</head>

<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
<%-- 
	<!-- Offcanvas Menu Begin -->
	<div class="offcanvas-menu-overlay"></div>
	<div class="offcanvas-menu-wrapper">
		<div class="offcanvas__cart">
			<div class="offcanvas__cart__links">
				<a href="#" class="search-switch"><img
					src="/resources/img/icon/search.png" alt=""></a> <a href="#"><img
					src="/resources/img/icon/heart.png" alt=""></a>
			</div>
			<div class="offcanvas__cart__item">
				<a href="#"><img src="/resources/img/icon/cart.png" alt="">
					<span>0</span></a>
				<div class="cart__price">
					Cart: <span>₩0.00</span>
				</div>
			</div>
		</div>
		<div class="offcanvas__logo">
			<a href="/index"><img src="/resources/img/logo.png" alt=""></a>
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
--%>
	<!-- Header Section Begin -->
	<div class="header__banner-left">
		<h3>배너자리배너자리배너자리배너자리배너자리배너자리배너자리배너자리배너자리</h3>
	</div>
	<jsp:include page="/WEB-INF/views/include/Nav.jsp" />
	<!-- Header Section End -->

	<!-- Hero Section Begin -->
	<section class="hero">
		<div class="hero__slider owl-carousel">
			<div class="hero__item set-bg teamlogo" data-setbg="/resources/img/4team_logomain_.jpg" >
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="col-lg-8">
							<div class="hero__text">
								<h2>단호박마켓(SGP)</h2><br>
								<h2>오픈기념 최대 50%</h2>
								<a href="#" class="primary-btn">WELCOME</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="hero__item set-bg" data-setbg="/resources/img/4team_logo.png" style=" object-fit: contain;">
				<div class="container">
					<div class="row d-flex justify-content-center">
						<div class="col-lg-8">
							<div class="hero__text">
								<h2>단호박마켓(SGP)</h2><br>
								<h2>오픈기념 최대 50%</h2>
								<a href="#" class="primary-btn">Our cakes</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Hero Section End -->




	<!-- Product Section Begin -->
	<section class="product spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-1.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Dozen Cupcakes</a>
							</h6>
							<div class="product__item__price">₩32.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-2.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Cookies and Cream</a>
							</h6>
							<div class="product__item__price">₩30.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-3.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Gluten Free Mini Dozen</a>
							</h6>
							<div class="product__item__price">₩31.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-4.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Cookie Dough</a>
							</h6>
							<div class="product__item__price">₩25.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-5.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Vanilla Salted Caramel</a>
							</h6>
							<div class="product__item__price">₩05.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-6.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">German Chocolate</a>
							</h6>
							<div class="product__item__price">₩14.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-7.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Dulce De Leche</a>
							</h6>
							<div class="product__item__price">$32.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/resources/img/shop/product-8.jpg">
							<div class="product__label">
								<span>Cupcake</span>
							</div>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">Mississippi Mud</a>
							</h6>
							<div class="product__item__price">₩08.00</div>
							<div class="cart_add">
								<a href="#">Add to cart</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Product Section End -->


	<!-- Testimonial Section Begin -->
	<section class="testimonial spad">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-center">
					<div class="section-title">
						<span>코로나 방역물품 50% 할인</span>
						<h2>손소독제, 마스크</h2>
					</div>
				</div>
			</div>
			<div class="col">
				<div id="chart_div"></div>
			</div>
		</div>
	</section>
	<!-- Testimonial Section End -->

	<!-- Footer Section Begin -->
	<footer class="footer set-bg" data-setbg="/resources/img/footer-bg.jpg">
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
							<a href="#"><img src="/resources/img/footer-logo.png" alt=""></a>
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