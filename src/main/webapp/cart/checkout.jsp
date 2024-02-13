<%@page import="domain.member.Member"%>
<%@page import="domain.cart.dto.listReqCartDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	List<listReqCartDto> cartlist = (List<listReqCartDto>) request.getAttribute("cartlist");
	int Subtotal = cartlist.stream().mapToInt(i -> i.getItem_amount()*i.getItem_price()).sum();
	String Delivery = request.getParameter("Delivery");
	String Discount = request.getParameter("Discount");
	int orderTotal = Subtotal+Integer.parseInt(Delivery)-Integer.parseInt(Discount);
	Member member = (Member) session.getAttribute("principal");
	String postAddress = "";
	String mainAddress = "";
	String detailAddress = "";
	if(member.getMember_address().contains("@")) {
		String[] adress = member.getMember_address().split("@");
		postAddress = adress[0];
		mainAddress = adress[1];
		detailAddress = adress[2];
	}
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                
                } else {
                    document.getElementById("sample6_extraAddress").value = '';
                }
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr;
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
</script>

		<div class="breadcrumbs">
			<div class="container">
				<div class="row">
					<div class="col">
						<p class="bread"><span><a href="index.html">Home</a></span> / <span>Checkout</span></p>
					</div>
				</div>
			</div>
		</div>
		
		<div class="colorlib-product">
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-sm-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3>Shopping Cart</h3>
							</div>
							<div class="process text-center active">
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
				<div class="row">
					<div class="col-lg-8">
						<form method="post" class="colorlib-form">
							<h2>배송지 입력</h2>
		              	<div class="row">
		              			<div class="col-md-12">
									<div class="form-group">
										<label for="email">이름</label>
										<input type="text" id="email" class="form-control" placeholder="이름" value = "${sessionScope.principal.member_name}">
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="fname">우편번호</label>
										<input type="text" id="sample6_postcode" placeholder="우편번호" class="form-control" value = "<%=postAddress%>">
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn btn-primary">
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="companyname">주소</label>
			                    		<input type="text" id="sample6_address" placeholder="주소" class="form-control" value = "<%=mainAddress%>">
										<input type="text" id="sample6_detailAddress" placeholder="상세주소" class="form-control" value = "<%=detailAddress%>">
			                  		</div>
			               		</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="email">E-mail Address</label>
										<input type="text" id="email" class="form-control" placeholder="이메일 주소" value = "${sessionScope.principal.member_email}">
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="Phone">Phone Number</label>
										<input type="text" id="zippostalcode" class="form-control" placeholder="핸드폰 번호" value = "${sessionScope.principal.member_phone}">
									</div>
								</div>
		               		</div>
		            	</form>
					</div>

					<div class="col-lg-4">
						<div class="row">
							<div class="col-md-12">
								<div class="cart-detail">
									<h2>Cart Total</h2>
									<ul>
										<li>
											<span>Subtotal</span> <span><%=Subtotal%> 원</span>
											<ul>
												<c:forEach var = "cart" items="${cartlist}">
												<li><span>${cart.item_amount} x ${cart.item_name}	</span> <span>${cart.item_amount*cart.item_price} 원</span></li>
												</c:forEach>
											</ul>
											
										</li>
										<li><span>Shipping</span> <span><%=Delivery%> 원</span></li>
										<li><span>Discount</span> <span><%=Discount%> 원</span></li>
										<li><span>Order Total</span> <span><%=orderTotal%> 원</span></li>
									</ul>
								</div>
						   </div>

						   <div class="w-100"></div>

						   <div class="col-md-12">
								<div class="cart-detail">
									<h2>Payment Method</h2>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio"> Direct Bank Tranfer</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio"> Check Payment</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="radio">
											   <label><input type="radio" name="optradio"> Paypal</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="col-md-12">
											<div class="checkbox">
											   <label><input type="checkbox" value=""> I have read and accept the terms and conditions</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 text-center">
								<p><a href="#" class="btn btn-primary">Place an order</a></p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

<%@ include file="../include/footer.jsp" %>