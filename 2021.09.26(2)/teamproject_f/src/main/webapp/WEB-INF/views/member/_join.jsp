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
<!-- 다음 주소API -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>


function execDaumPostcode() {
  new daum.Postcode({
    oncomplete: function(data) {
      // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

      // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
      // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
      var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
      var extraRoadAddr = ''; // 도로명 조합형 주소 변수

      // 법정동명이 있을 경우 추가한다. (법정리는 제외)
      // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
      if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
        extraRoadAddr += data.bname;
      }
      // 건물명이 있고, 공동주택일 경우 추가한다.
      if(data.buildingName !== '' && data.apartment === 'Y'){
        extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
      }
      // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
      if(extraRoadAddr !== ''){
        extraRoadAddr = ' (' + extraRoadAddr + ')';
      }
      // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
      if(fullRoadAddr !== ''){
        fullRoadAddr += extraRoadAddr;
      }

      // 우편번호와 주소 정보를 해당 필드에 넣는다.
      document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
      document.getElementById('roadAddress').value = fullRoadAddr;
      document.getElementById('jibunAddress').value = data.jibunAddress;

      // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
      if(data.autoRoadAddress) {
        //예상되는 도로명 주소에 조합형 주소를 추가한다.
        var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
        document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

      } else if(data.autoJibunAddress) {
          var expJibunAddr = data.autoJibunAddress;
          document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
      } else {
          document.getElementById('guide').innerHTML = '';
      }
      
     
    }
  }).open();
}

function fn_overlapped(){
    var _id=$("#_member_id").val();
    if(_id==''){
   	 alert("ID를 입력하세요");
   	 return;
    }
    $.ajax({
       type:"post",
       async:false,  
       url:"${contextPath}/member/overlapped.do",
       dataType:"text",
       data: {id:_id},
       success:function (data,textStatus){
          if(data=='false'){
       	    alert("사용할 수 있는 ID입니다.");
       	    $('#btnOverlapped').prop("disabled", true);
       	    $('#_member_id').prop("disabled", true);
       	    $('#member_id').val(_id);
          }else{
        	  alert("사용할 수 없는 ID입니다.");
          }
       },
       error:function(data,textStatus){
          alert("에러가 발생했습니다.");ㅣ
       },
       complete:function(data,textStatus){
          //alert("작업을완료 했습니다");
       }
    });  //end ajax	 
 }	
</script>
</head>

<body>

	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>

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
							<!-- <li><a href="./about.html">About</a></li> -->
							<li><a href="./shop.html">물품</a></li>
							<li><a href="#">마이페이지</a>
								<ul class="dropdown">
									<li><a href="./shop-details.html">Shop Details</a></li>
									<li><a href="./shoping-cart.html">Shoping Cart</a></li>
									<li><a href="./checkout.html">Check Out</a></li>
									<li><a href="./wisslist.html">Wisslist</a></li>
									<li><a href="./Class.html">Class</a></li>
									<li><a href="./blog-details.html">Blog Details</a></li>
								</ul></li>
							<li><a href="./blog.html">블로그</a></li>
							<li><a href="./contact.html">오시는길</a></li>
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
						<h2>회원가입</h2>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6">
					<div class="breadcrumb__links">
						<a href="./index.html">홈</a> <span>회원가입</span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb End -->

	<!-- Checkout Section Begin -->
	<section class="checkout spad">
		<div class="container">
			<div class="checkout__form">
				<form action="/member/join" method="POST">
					<div class="row">
						<div class="col-lg-8 col-md-6">
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											이름<span>*</span>
										</p>
										<input type="text" name="name">
									</div>
								</div>
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
										<input type="text" name="passwd">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											비밀번호 재확인<span>*</span>
										</p>
										<input type="text">
									</div>
								</div>
							</div>
							<div class="checkout__input">
								<p>
									주소<span>*</span>
								</p>
<!-- 								<input type="text" placeholder="도로명 주소" -->
<!-- 									class="checkout__input__add" name="address"> <input -->
<!-- 									type="text" placeholder="상세주소"> -->
								<input type="text" id="zipcode" name="zipcode" size="10" > <a href="javascript:execDaumPostcode()">우편번호검색</a>
								<input type="text" id="roadAddress" name="roadAddress" size="50" /><br>
								<input type="text" id="jibunAddress" name="jibunAddress" size="50" /><br>
								<input type="text" name="namujiAddress" size="50"/>
 							</div>
							<div class="row">
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											휴대전화<span>*</span>
										</p>
										<input type="text" name="phonenumber">
									</div>
								</div>
								<div class="col-lg-6">
									<div class="checkout__input">
										<p>
											Email<span>*</span>
										</p>
										<input type="text" name="email">
									</div>
								</div>
								<div class="form-group">
									<label for="gender"> <span class="align-middle">성별
											선택</span>
									</label> <select class="form-control" id="gender" name="gender">
										<option value="" disabled selected>성별을 선택하세요.</option>
										<option value="M">남자</option>
										<option value="F">여자</option>
										<option value="U">선택 안함</option>
									</select>
								</div>
								<div class="form-group">
									<label for="birthday"> <span class="align-middle">생년월일</span>
									</label> <input type="date" class="form-control" id="birthday"
										name="birth">
								</div>
							</div>
							<button class="btn btn-lg bg-warning">회원가입</button>
							<button class="btn btn-lg bg-warning">초기화</button>
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