<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
	Kakao.init('bac6e97cacea3e5ae4a416a83b6e45e8'); // 사용하려는 앱의 JavaScript 키 입력
	//카카오 로그인
	function kakaoLogin(){
	 Kakao.Auth.login({
	        success: function (response) {
	            Kakao.API.request({
	            url: '/v2/user/me',
	            success: function (response) {
	            	 console.log(response)
	            	 //카카오로그인시 email, nickname 을 data 객체에 값 할당
	            	 let data = {
	            		 member_email: response.kakao_account.email,
	            		 member_id: response.kakao_account.profile.nickname
	            	 }
	            	 kakaoSiteLogin(data);
	  
	            },
	            fail: function (error) {
	                console.log(error)
	            },
	            })
	        },
	        fail: function (error) {
	            console.log(error)
	        },
	        })
	}
	//카카오이메일주소, 닉네임으로 회원정보 확인
	function kakaoSiteLogin(result){
	 console.log(result);
	 //result --> 객체
	 $.ajax({
	  type:"POST", //전송메소드
	  url: "/shoes/member?cmd=kakaologin", //전송경로
	  data: JSON.stringify(result), //body에 담을 데이터
	  contentType: "application/json; charset=utf-8", //요청 데이터 타입
	  dataType: "text"	//응답 데이터 타입
	 }).done(function(logindata){
	  if(logindata=="fail"){
		  alert("회원가입해주세요");
		  location.href="/shoes/member?cmd=joinForm";
	  }else if(logindata=="ok"){
		  alert("로그인 되었습니다.");
		 location.href="index.jsp";
	  }
	 })
	}
</script>
<div class="colorlib-product">

			<div class="container">
				
				
					<div class="container">
						
						<h2>ID 로그인</h2>
						<form action="/shoes/member?cmd=login" method="post" class="colorlib-form">	
		              	<div class="row">
							<div class="col-md-12">
								<!-- <div class="form-group">
									<label>아이디</label>
		                    	<input type="text" name="member_id" class="form-control" placeholder="아이디" required>
		                    	</div> -->
		                    	<div class="form-group">
									<label>이메일</label>
		                    	<input type="text" name="member_email" class="form-control" placeholder="이메일" required>
		                    	</div>
		                    	<div class="form-group">
									<label>비밀번호</label>
		                    	<input type="password" name="member_pw" class="form-control" placeholder="비밀번호" required>
		              			</div>
		               		</div>
							<div class="col-md-12">
								<div >
									<div class="text-center ">
									  <button type="submit" class="btn btn-primary submit-search text-center">로그인</button>
									</div>
								</div>
							</div>
		               </div>
		            </form>
		            <br/>
					<div>
						<p onclick="kakaoLogin()">
						<a href="javascript:void(0)">
						<img src="../shoes/images/kakao_login_btn.png" width="230">
						</a></p>
					</div>
					</div>
				
			</div>
		</div>
<%@ include file="../include/footer.jsp"%>
    