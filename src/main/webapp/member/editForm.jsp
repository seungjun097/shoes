<%@page import="domain.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%
	Member dto = (Member) request.getAttribute("dto");
%>

<div class="colorlib-product">

			<div class="container">
				
				<div class="row">
					<div class="col-lg-8">
						
							<h2>회원정보 수정</h2>
						<form action="/shoes/member?cmd=edit" method="post" class="colorlib-form"> <!-- onsubmit="return valid()" -->
		              	<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>이름</label>
										<input type="text" id="member_name" name="member_name" class="form-control" placeholder="이름" value="<%=dto.getMember_name()%>" required />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>아이디</label>
			                    	<input type="text" id="member_id" name="member_id" class="form-control" placeholder="영문/특수문자를 이용한 6~8자리이상" value="<%=dto.getMember_id()%>" required />
			                    	</div>
			                    	<div class="form-group">
										<label>비밀번호</label>
			                    	<input type="text" id="member_pw" name="member_pw" class="form-control" placeholder="비밀번호" value="<%=dto.getMember_pw()%>" required />
			              			</div>
									<div class="form-group">
										<label>주소</label>
					                   	<input type="text" id="member_address" name="member_address" class="form-control" placeholder="주소" value="<%=dto.getMember_address()%>" required />
					                </div>
						            
			               		</div>
			               		
								<div class="col-md-12">
									<div class="form-group">
										<label>이메일</label>
										<input type="text" id="member_email" name="member_email" class="form-control" placeholder="이메일" value="<%=dto.getMember_email()%>" required />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>휴대폰 번호</label>
										<input type="text" id="member_phone" name="member_phone" class="form-control" placeholder="휴대폰 번호" value="<%=dto.getMember_phone()%>" required />
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="hidden" id="member_cancel" name="member_cancel" class="form-control" placeholder="" value = "no" />
										<input type="hidden" id="member_key" name="member_key" class="form-control" placeholder="" value = "<%=dto.getMember_key()%>" />
									</div>
								</div>

								<div class="col-md-12">
									<div >
										<div class="text-center ">
										  <button type="submit" class="btn btn-primary submit-search text-center">회원정보 수정</button>
										  
										</div>
									</div>
								</div>
		               </div>
		            </form>
		            <form action="/shoes/member?cmd=delete" method="post" class="colorlib-form">
		            	<input type="hidden" id="member_key" name="member_key" class="form-control" placeholder="" value = "<%=dto.getMember_key()%>" />
		            	<button type="submit" class="btn btn-primary submit-search text-center">회원정보 삭제</button>
		            </form>
					</div>

					
				</div>
			</div>
		</div>
<%@ include file="../include/footer.jsp"%>
    