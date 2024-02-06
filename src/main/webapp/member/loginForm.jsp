<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="colorlib-product">

			<div class="container">
				
				<div class="row">
					<div class="col-lg-8">
						
						<h2>ID 로그인</h2>
						<form action="/shoes/member?cmd=login" method="post" class="colorlib-form">	
		              	<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<label>아이디</label>
		                    	<input type="text" name="member_id" class="form-control" placeholder="아이디" required>
		                    	</div>
		                    	<div class="form-group">
									<label>비밀번호</label>
		                    	<input type="text" name="member_pw" class="form-control" placeholder="비밀번호" required>
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
					</div>
				</div>
			</div>
		</div>
<%@ include file="../include/footer.jsp"%>
    