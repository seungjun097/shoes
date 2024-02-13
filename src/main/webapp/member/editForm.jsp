<%@page import="domain.member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<%
	Member dto = (Member) request.getAttribute("dto");
	String post_address = "";
	String main_address = "";
	String detail_address = "";
	if(dto.getMember_address().contains("@")) {
		String[] str = dto.getMember_address().split("@");
		post_address = str[0];
		main_address = str[1];
		detail_address = str[2];
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
	    function valid(){
			var postcode = $('#sample6_postcode').val();
		    var address = $('#sample6_address').val();
		    var detailAddress = $('#sample6_detailAddress').val();
		    var combinedAddress = postcode + "@" + address + "@" + detailAddress;
		    $('#member_address').val(combinedAddress);
		    return true;
		}
</script>

<div class="colorlib-product">

			<div class="container">
				
				<div class="row">
					<div class="col-lg-8">
						
							<h2>회원정보 수정</h2>
						<form action="/shoes/member?cmd=edit" method="post" class="colorlib-form" onsubmit = "return valid()"> <!-- onsubmit="return valid()" -->
		              	<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label>이름</label>
										<input type="text" id="member_name" name="member_name" class="form-control" placeholder="이름" value="<%=dto.getMember_name()%>" required />
									</div>
								</div>
								<div class="col-md-12">
			                    	<input type="hidden" id="member_id" name="member_id" class="form-control" placeholder="영문/특수문자를 이용한 6~8자리이상" value="<%=dto.getMember_id()%>" required />
			                    	<div class="form-group">
										<label>비밀번호</label>
			                    	<input type="password" id="member_pw" name="member_pw" class="form-control" placeholder="비밀번호" value="<%=dto.getMember_pw()%>" required />
			              			</div>
			               		</div>
			               		<div class="col-md-12">
									<div class="form-group">
										<label for="fname">우편번호</label>
										<input type="text" id="sample6_postcode" placeholder="우편번호" class="form-control" value= "<%=post_address%>" required >
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기" class="btn btn-primary"><br>
									</div>
								</div>
								<div class="col-md-12">
									<div class="form-group">
										<label for="companyname">주소</label>
			                    		<input type="text" id="sample6_address" placeholder="주소" class="form-control" value="<%=main_address%>" required><br>
										<input type="text" id="sample6_detailAddress" placeholder="상세주소" class="form-control" value= "<%=detail_address%>" required>
										<input type="hidden" id="member_address" name ="member_address">
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
    