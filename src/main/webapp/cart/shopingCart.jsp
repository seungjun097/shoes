<%@page import="domain.cart.dto.listReqCartDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../include/header.jsp" %>

<%
	List<listReqCartDto> cartlist = (List<listReqCartDto>) request.getAttribute("cartlist");
	int Subtotal = cartlist.stream().mapToInt(i -> i.getItem_amount()*i.getItem_price()).sum();
	int Delivery = 3000;
	int Discount = 0;
	
%>

		<div class="breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col">
						<p class="bread"><span><a href="index.html">Home</a></span> / <span>Shopping Cart</span></p>
					</div>
				</div>
			</div>
		</div>
		<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-md-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3>Shopping Cart</h3>
							</div>
							<div class="process text-center">
								<p><span>02</span></p>
								<h3>Checkout</h3>
							</div>
							<div class="process text-center">
								<p><span>03</span></p>
								<h3>Order Complete</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row row-pb-lg">
					<div class="col-md-12">
						<div class="product-name d-flex">
							<div class="one-forth text-left px-4">
								<span>Product Details</span>
							</div>
							<div class="one-eight text-center">
								<span>Price</span>
							</div>
							<div class="one-eight text-center">
								<span>size</span>
							</div>
							<div class="one-eight text-center">
								<span>Quantity</span>
							</div>
							<div class="one-eight text-center">
								<span>Total</span>
							</div>
							<div class="one-eight text-center px-4">
								<span>Remove</span>
							</div>
						</div>
						<!--여기서부터 카트 리스트 반복문. 필요한 정보 사진, 상품이름, 가격, 수량, 가격*상품수량 제거 -->
						<c:forEach var="cart" items="${cartlist}">
						<div class="product-cart d-flex">
							<div class="one-forth">
								<div class="product-img" style="background-image: url(file/images/${cart.item_cart_img});">
								</div>
								<div class="display-tc">
									<h3>${cart.item_name}</h3>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="price">${cart.item_price}</span>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="size">${cart.item_size}</span>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<input type="text" id="quantity" name="quantity" class="form-control input-number text-center" value="${cart.item_amount}" min="1" max="100">
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<span class="price">${cart.item_price * cart.item_amount}</span>
								</div>
							</div>
							<div class="one-eight text-center">
								<div class="display-tc">
									<a href="/shoes/cart?cmd=delete&cartkey=${cart.cart_key}" class="closed"></a>
								</div>
							</div>
						</div>
							</c:forEach>
						</div>
					</div>
					<div class="row center">
					<div class="col-md-12 text-center">
						<div class="total-wrap">
							<div class="row">
								<div class="col-sm-4 text-center">
									<div class="total">
										<div class="sub">
											<p><span>Subtotal:</span> <span><%=Subtotal%></span></p>
											<p><span>Delivery:</span> <span><%=Delivery%></span></p>
											<p><span>Discount:</span> <span><%=Discount%></span></p>
										</div>
										<div class="grand-total">
											<p><span><strong>Total:</strong></span> <span><%=Subtotal+Delivery-Discount%></span></p>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
					<form action="/shoes/cart?cmd=checkout" method="post">
					<input type= "hidden" name= "Delivery" value= <%=Delivery%>>
					<input type= "hidden" name= "Discount" value= <%=Discount%>>
						<div class="col-md-12 text-center">
							<input type="submit" value="주문하기" class="btn btn-primary">
						</div>
					</form>
		</div>
	</div>		
<%@ include file="../include/footer.jsp" %>
