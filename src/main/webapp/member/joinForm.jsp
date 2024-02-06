<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="colorlib-product">
	<script>
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
	</script>
			<div class="container">
				<div class="row row-pb-lg">
					<div class="col-sm-10 offset-md-1">
						<div class="process-wrap">
							<div class="process text-center active">
								<p><span>01</span></p>
								<h3>회원정보 기입</h3>
							</div>
							<div class="process text-center active">
								<p><span>02</span></p>
								<h3>회원정보 확인</h3>
							</div>
							<div class="process text-center">
								<p><span>03</span></p>
								<h3>회원가입 완료</h3>
							</div>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-8">
						<!-- <form action="/shoes/member?cmd=join" method="post" class="colorlib-form"> -->
							<h2>회원정보 기입</h2>
						<form action="/shoes/member?cmd=join" method="post" class="colorlib-form" onsubmit="return valid()">	
		              	<div class="row">
			               <!-- <div class="col-md-12">
			                  <div class="form-group">
			                  	<label for="country">Select Country</label>
			                     <div class="form-field">
			                     	<i class="icon icon-arrow-down3"></i>
			                        <select name="people" id="people" class="form-control">
				                      	<option value="#">Select country</option>
				                        <option value="#">Alaska</option>
				                        <option value="#">China</option>
				                        <option value="#">Japan</option>
				                        <option value="#">Korea</option>
				                        <option value="#">Philippines</option>
			                        </select>
			                     </div>
			                  </div>
			               </div> -->
								<div class="col-md-12">
									<div class="form-group">
										<label>이름</label>
										<input type="text" name="member_name" class="form-control" placeholder="이름" required>
									</div>
								</div>
								<!-- <div class="col-md-6">
									<div class="form-group">
										<label for="lname">이름</label>
										<input type="text" id="lname" class="form-control" placeholder="이름" required>
									</div>
								</div> -->
								
								<div class="col-md-12">
									<div class="form-group">
										<label>아이디</label>
			                    	<input type="text" id="member_id" name="member_id" class="form-control" placeholder="영문/특수문자를 이용한 6~8자리이상" required>
			                    	<button type="button" onclick="idcheck()" >아이디 중복체크</button>
			                    	</div>
			                    	<div class="form-group">
										<label>비밀번호</label>
			                    	<input type="text" name="member_pw" class="form-control" placeholder="비밀번호" required>
			              			</div>
									<div class="form-group">
										<label>주소</label>
					                   	<input type="text" name="member_address" class="form-control" placeholder="주소" required>
					                </div>
						            <!-- <div class="form-group">
						                 <input type="text" id="address2" class="form-control" placeholder="상세주소" required>
						            </div> -->
			               		</div>
			               		
								<div class="col-md-12">
									<div class="form-group">
										<label>이메일</label>
										<input type="text" name="member_email" class="form-control" placeholder="이메일" required>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label>휴대폰 번호</label>
										<input type="text" name="member_phone" class="form-control" placeholder="휴대폰 번호" required>
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
										  <button type="submit" class="btn btn-primary submit-search text-center">회원가입</button>
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
    