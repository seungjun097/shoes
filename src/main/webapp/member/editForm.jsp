<%@page import="domain.member.dto.JoinReqDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%
	JoinReqDto dto = (JoinReqDto) request.getAttribute("dto");
%>
<div class="colorlib-product">
<!-- 	<script>
		let isChecking = false;
		function valid(){
			if(!isChecking){
				alert("아이디 중복체크를 해주세요");
			}
			return isChecking;
		}
		function idcheck(){
			//input의 값을 member_id변수에 할당
			let member_id = $("#member_id").val();
			console.log(member_id);
			//네트워크 요청
			$.ajax({
				type: "POST",
				url: "/shoes/member?cmd=member_idCheck", //요청경로
				data: member_id, //body에 보낼데이터(get은 못씀)
				contentType: "text/plain; charset=utf-8", //보낼데이터 mime타입
				dataType: "text" //응답받을 데이터 타입
			}).done(function(data){
				//멤버아이디가 중복되지 않으면 ok를 전송
				if(data=="ok"){
					isChecking = true;
					alert("사용가능한 아이디 입니다.");
				}else {
					alert("이미 사용하고 있는 아이디 입니다.");
					isChecking = false;
				}
			})
		}
	</script> -->
			<div class="container">
				
				<div class="row">
					<div class="col-lg-8">
						
							<h2>회원정보 수정</h2>
						<form action="/shoes/member?cmd=edit" method="post" class="colorlib-form"> <!-- onsubmit="return valid()" -->
		              	<div class="row">
			               
								<div class="col-md-12">
									<div class="form-group">
										<label>이름</label>
										<input type="text" name="member_name" class="form-control" placeholder="이름" value="<%=dto.getMember_name() %>" required>
									</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<label>아이디</label>
			                    	<input type="text" id="member_id" name="member_id" class="form-control" placeholder="영문/특수문자를 이용한 6~8자리이상" value="<%=dto.getMember_id() %>" required>
			                    	<button type="button" >아이디 중복체크</button> <!-- onclick="idcheck()" -->
			                    	</div>
			                    	<div class="form-group">
										<label>비밀번호</label>
			                    	<input type="text" name="member_pw" class="form-control" placeholder="비밀번호" value="<%=dto.getMember_pw() %>" required>
			              			</div>
									<div class="form-group">
										<label>주소</label>
					                   	<input type="text" name="member_address" class="form-control" placeholder="주소" value="<%=dto.getMember_address() %>" required>
					                </div>
						            
			               		</div>
			               		
								<div class="col-md-12">
									<div class="form-group">
										<label>이메일</label>
										<input type="text" name="member_email" class="form-control" placeholder="이메일" value="<%=dto.getMember_email() %>" required>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>휴대폰 번호</label>
										<input type="text" name="member_phone" class="form-control" placeholder="휴대폰 번호" value="<%=dto.getMember_phone() %>" required>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<input type="hidden" name="member_cancel" class="form-control" placeholder="" value = "no">
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
					</div>

					
				</div>
			</div>
		</div>
<%@ include file="../include/footer.jsp"%>
    