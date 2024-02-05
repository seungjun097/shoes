<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<body>
	<h2>상품 업로드</h2>
	<form action="/shoes/item?cmd=saveitem" method="post" enctype="multipart/form-data">
	
		<p>상품명 <input type ="text" name = "item_name"/></p>
		<div class="form-group">
			<label for="country">사용자 성별</label>
			   <div class="form-field">
			       <select name="item_gender" id="gender" class="form-control" style="width:300px">
				       <option value="남">남</option>
				       <option value="여">여</option>
				       <option value="공용">공용</option>
			      </select>
			 </div>
		</div>
		<div class="form-group">
			<label for="country">카테고리</label>
			   <div class="form-field">
			       <select name="item_cate" id="category" class="form-control" style="width:300px">
				       <option value="스니커즈">스니커즈</option>
				       <option value="구두">구두</option>
				       <option value="농구화">농구화</option>
				       <option value="등산화">등산화</option>
				       <option value="런닝화">런닝화</option>
			      </select>
			 </div>
		</div>
		<p>신발 색상 <input type ="text" name = "item_color"/></p>
		<p>신발 가격 <input type ="text" name = "item_price"/></p>
		<p>신발 설명 <input type ="text" name = "item_content"/></p>
		<p>신발 제고 <input type ="text" name = "item_stock"/></p>
		<p>신발 사이즈 <input type ="text" name = "item_size"/></p>
		<p>신발 상세페이지 사진 <input type ="file" name = "item_detail_img"/></p>
		<p>신발 카트페이지 사진 <input type ="file" name = "item_cart_img"/></p>
		<p>신발 리스트페이지 사진 <input type ="file" name = "item_list_img"/></p>
		
		<p><input type ="submit" value = "작성"/></p>
		
	</form>
</body>

<%@ include file="../include/footer.jsp" %>