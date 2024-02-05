<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div class="breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col">
						<p class="bread"><span><a href="index.html">Home</a></span> / <span>Men</span></p>
					</div>
				</div>
			</div>
		</div>

		<div class="colorlib-product">
			<div class="container">
				<div class="row">
					<div class="col-lg-3 col-xl-3">
						<div class="row">
						
							<div class="col-sm-12">
								<div class="side border mb-1">
									<h3>Size/Width</h3>
									<div class="block-26 mb-2">
										<h4>Size</h4>
					               <ul>
					                  <li><a href="#">7</a></li>
					                  <li><a href="#">7.5</a></li>
					                  <li><a href="#">8</a></li>
					                  <li><a href="#">8.5</a></li>
					                  <li><a href="#">9</a></li>
					                  <li><a href="#">9.5</a></li>
					                  <li><a href="#">10</a></li>
					                  <li><a href="#">10.5</a></li>
					                  <li><a href="#">11</a></li>
					                  <li><a href="#">11.5</a></li>
					                  <li><a href="#">12</a></li>
					                  <li><a href="#">12.5</a></li>
					                  <li><a href="#">13</a></li>
					                  <li><a href="#">13.5</a></li>
					                  <li><a href="#">14</a></li>
					               </ul>
					            </div>
					            <div class="block-26">
										<h4>Width</h4>
					               <ul>
					                  <li><a href="#">M</a></li>
					                  <li><a href="#">W</a></li>
					               </ul>
					            </div>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="side border mb-1">
									<h3>Category</h3>
									<ul>
										<li><a href="#">러닝화</a></li>
										<li><a href="#">워커</a></li>
										<li><a href="#">슬리퍼</a></li>
										<li><a href="#">스니커즈</a></li>
										<li><a href="#">구두</a></li>
									</ul>
								</div>
							</div>
							<div class="col-sm-12">
								<div class="side border mb-1">
									<h3>Colors</h3>
									<ul>
										<li><a href="#">Black</a></li>
										<li><a href="#">White</a></li>
										<li><a href="#">Blue</a></li>
										<li><a href="#">Red</a></li>
										<li><a href="#">Green</a></li>
										<li><a href="#">Grey</a></li>
										<li><a href="#">Brown</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
					
				
				
				<div class="col-lg-9 col-xl-9">
					<div class="row row-pb-md">
						<c:forEach var="i" items="${items}">
							<div class="col-lg-4 mb-4 text-center">
								<div class="product-entry border">
									<a href="#" class="prod-img">
										<img src="<%=request.getContextPath()%>/file/images/${i.item_list_img}" class="img-fluid" alt="Free html5 bootstrap 4 template">
									</a>
									<div class="desc">
										<h2><a href="<%=request.getContextPath()%>/item?cmd=itemdetail&id=${i.item_key}">${i.item_name}</a></h2>
										<span class="price">${i.item_price}</span>
									</div>	
								</div>	
							</div>
						</c:forEach>
					</div>
				</div>
				
						
						<div class="row">
							<div class="col-md-12 text-center">
								<div class="block-27">
				               <ul>
						               <c:choose>
							               <c:when test="${page==0}">
								                <li><a href="item?cmd=manlist&page=${page-1}"><i class="ion-ios-arrow-back"></i></a></li>
								           </c:when>
								           <c:otherwise>
								           		<li><a href="item?cmd=manlist&page=${page-1}"><i class="ion-ios-arrow-back" ></i></a></li>
								           </c:otherwise>
						                </c:choose>
						                
						                <c:choose>
							               <c:when test="${page==lastpage}">
								                <li><a href="item?cmd=manlist&page=${page+1}"><i class="ion-ios-arrow-forward"></i></a></li>
								           </c:when>
								           <c:otherwise>
								           		<li><a href="item?cmd=manlist&page=${page+1}"><i class="ion-ios-arrow-forward" ></i></a></li>
								           </c:otherwise>
						                </c:choose>
						              
					               </ul>
				            </div>
							</div>
						</div>
					</div>
				</div>
			</div>
	

		<div class="colorlib-partner">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 offset-sm-2 text-center colorlib-heading colorlib-heading-sm">
						<h2>Trusted Partners</h2>
					</div>
				</div>
				<div class="row">
					<div class="col partner-col text-center">
						<img src="images/brand-1.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
					</div>
					<div class="col partner-col text-center">
						<img src="images/brand-2.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
					</div>
					<div class="col partner-col text-center">
						<img src="images/brand-3.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
					</div>
					<div class="col partner-col text-center">
						<img src="images/brand-4.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
					</div>
					<div class="col partner-col text-center">
						<img src="images/brand-5.jpg" class="img-fluid" alt="Free html4 bootstrap 4 template">
					</div>
				</div>
			</div>
		</div>
		<%@ include file="../include/footer.jsp"%>