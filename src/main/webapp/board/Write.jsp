<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/include/header.jsp" %>

<title>게시판</title>

<script type="text/javascript">
	function validateForm(from) {		//폼내용검증
		//제목 내용 입력 안하면 경고창 출력
		if (form.title.value== "") {
			alert("제목을 입력하세요.")
			form.title.focus();
			return false;
		}
		if (form content.value=="") {
			alert("내용을 입력하세요.")
			form.content.focus();
			return false;
		}
	}
</script>

	<h2>게시판 - 글쓰기</h2>
	<!-- 폼의 이름 전송방식 전송경로 지정 -->
	<form name="writeFrm" method="post" action="WriteProcess.jsp"
		onsubmit="return validateFrom(this);">
		<table border="1" width="90%">
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="board_title" style="width: 90%" />
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="board_content" style="width: 90%; height: 100px;"  >
					</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">작성 완료</button>
					<button type="reset">다시 입력</button>
					<button type="button" onclick="location.href='List.jsp';">
					목록보기</button>
				</td>
			</tr>
		</table>
		
	</form>
	
<%@ include file="/include/footer.jsp" %>