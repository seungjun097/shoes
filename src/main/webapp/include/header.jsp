<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
	<head>
	<title>Footwear - Free Bootstrap 4 Template by Colorlib</title>
   <meta charset="utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Rokkitt:100,300,400,700" rel="stylesheet">

	<!-- Animate.css -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/animate.css">
	<!-- Icomoon Icon Fonts-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/icomoon.css">
	<!-- Ion Icon Fonts-->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/ionicons.min.css">
	<!-- Bootstrap  -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/bootstrap.min.css">

	<!-- Magnific Popup -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/magnific-popup.css">

	<!-- Flexslider  -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/flexslider.css">

	<!-- Owl Carousel -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/owl.carousel.min.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/owl.theme.default.min.css">
	
	<!-- Date Picker -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/bootstrap-datepicker.css">
	<!-- Flaticons  -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/fonts/flaticon/font/flaticon.css">
	
	<!-- Theme style  -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/file/css/style.css">
	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-latest.min.js"></script>
	
	<script>
	$(document).ready(function() {
	    $.ajax({
    		type: "post",
    		url: "/shoes/cart?cmd=cartlistnum",
    		dataType: "text",
	        success: function(response) {
	        	let str = "cart["+response+"]";
	        	console.log(str);
	            $('#cartCount').text(str);
	        },
	        error: function() {
	        	console.log("카트 num호출 실패");
	        }
	    });
	});
	</script>
	
	</head>
	<body>
		
	<div class="colorlib-loader"></div>

	<div id="page">
		<nav class="colorlib-nav" role="navigation">
			<div class="top-menu">
				<div class="container">
					<div class="row">
						<div class="col-sm-7 col-md-9">
							<div id="colorlib-logo"><a href="/shoes">Footwear</a></div>
						</div>
						<div class="col-sm-5 col-md-3">
			            <form action="#" class="search-wrap">
			               <div class="form-group">
			                  <input type="search" class="form-control search" placeholder="Search">
			                  <button class="btn btn-primary submit-search text-center" type="submit"><i class="icon-search"></i></button>
			               </div>
			            </form>
			         </div>
		         </div>
		         	
					<div class="row">
						<div class="col-sm-12 text-left menu-1">
							<ul>
								<li class="active"><a href="/shoes/">Home</a></li>
								<li><a href="/shoes/item?cmd=manlist">man</a></li>
								<li><a href="/shoes/item?cmd=womanlist&page=0">Woman</a></li>
								<li class="cart"><a id="cartCount"></a></li>
								
								<%	
									if(session.getAttribute("principal")==null) { //로그아웃 일때는 '로그인과 회원가입' 메뉴가 보이고
				 				%>
								<li class="cart"><a href="/shoes/member?cmd=joinForm">join</a></li>
								<li class="cart"><a href="/shoes/member?cmd=loginForm">login</a></li>
								<%
									}
									else {	//로그인 일때는 '로그아웃과 회원정보수정' 메뉴가 보이게 해라
								%>
								
								<li class="cart"><a href="/shoes/cart?cmd=list"><i class="icon-shopping-cart"></i> cart [0]</a></li>
								<li class="cart"><a href="/shoes/member?cmd=editForm">회원정보 수정</a></li>
								<li class="cart"><a href="/shoes/member?cmd=logout">로그아웃</a></li>
								<%
									}
								%>
							</ul>
						</div>
					</div>
				</div>
			</div>
			<div class="sale">
				<div class="container">
					<div class="row">
						<div class="col-sm-8 offset-sm-2 text-center">
							<div class="row">
								<div class="owl-carousel2">
									<div class="item">
										<div class="col">
											<h3><a href="#">25% off (Almost) Everything! Use Code: Summer Sale</a></h3>
										</div>
									</div>
									<div class="item">
										<div class="col">
											<h3><a href="#">Our biggest sale yet 50% off all summer shoes</a></h3>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>