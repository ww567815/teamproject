<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="/resources/css/style.css" type="text/css">
<%-- include head.jsp --%>
<jsp:include page="/WEB-INF/views/include/head.jsp" />
</head>

<body>
   <!-- Page Preloder -->
   <div id="preloder">
      <div class="loader"></div>
   </div>

 	<!-- Header Section Begin -->
	<jsp:include page="/WEB-INF/views/include/Nav.jsp" />
	<!-- Header Section End -->

   <!-- send message -->
   <div class="breadcrumb-option">
      <div class="container">
         <div class="row">
            <div class="col-lg-6 col-md-6 col-sm-6">
               <!-- <div class="breadcrumb__text"> -->
               <div>
               	아이디:<input type="text" value="${ sessionScope.id }" class="form-control" id="id" aria-describedby="idHelp" name="id" required autofocus readonly="readonly">
                  <button class="btn btn-lg bg-warning" onclick="openSocket();">대화방
                     참여</button>
                  <button class="btn btn-lg bg-warning" onclick="closeSocket();">대화방
                     나가기</button>
                  <br /> <br /> <br />

               </div>
            </div>
            <div class="col-lg-6 col-md-6 col-sm-6"></div>
         </div>
         <!-- send message End -->

         <!-- Server responses get written here -->
         <div class="card border-success mb-3" style="max-width: 50rem;">
            <div class="card-header">
            	<div class="card__header-chat" style="width: 100px;">
              	 <img class="chat__logo" src=/resources/img/logo_widthVer.png >
            	</div>
               단호박 채팅
            </div>
            <div class="card-body text-success">
               <p class="card-text">
               </p>
                  <div id="messages"></div>
               <form>
               <div class="checkout__input">
                  메세지 입력 : <input type="text" id="sender" value="${sessionScope.id}"
                     style="display: none;"> 
                     <input type="text" id="messageinput" >
                   
                  <button class="btn btn-lg bg-warning" id="submit" onclick="send();" type="reset">메세지
                     전송</button>
   
                  <button class="btn btn-lg bg-warning"
                     onclick="javascript:clearText();">대화내용 지우기</button>
                  <script type="text/javascript">
                     var ws;
                     var messages = document.getElementById("messages");

                     function openSocket() {
                        if (ws !== undefined
                              && ws.readyState !== WebSocket.CLOSED) {
                           writeResponse("WebSocket is already opened.");
                           return;
                        }
                        //웹소켓 객체 만드는 코드
                        ws = new WebSocket(
                              "ws://localhost:8090/echo.do");

                        ws.onopen = function(event) {
                           if (event.data === undefined) {
                              return;
                           }
                           writeResponse(event.data);
                        };

                        ws.onmessage = function(event) {
                           console.log('writeResponse');
                           console.log(event.data)
                           writeResponse(event.data);
                        };

                        ws.onclose = function(event) {
                           writeResponse("대화 종료");
                        }

                     }

                     function send() {
                        // var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
                        var text = document
                              .getElementById("messageinput").value
                              + ","
                              + document.getElementById("sender").value;
                        ws.send(text);
                        text = "";
                     }

                     function closeSocket() {
                        ws.close();
                     }

                     function writeResponse(text) {
                        messages.innerHTML += "<br/>" + text;
                     }

                     function clearText() {
                        console.log(messages.parentNode);
                        messages.parentNode.removeChild(messages)
                     }

                  </script>
               </div>
               </form>
            </div>
         </div>
      </div>
   </div>

   <!-- websocket javascript -->

   <!-- chatting Section -->
   <!-- chatting Section End -->

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