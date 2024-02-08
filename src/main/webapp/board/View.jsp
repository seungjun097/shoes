
<%@page import="domain.member.Member"%>
<%@page import="domain.board.BoardDTO"%>
<%@page import="domain.board.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/header.jsp" %>

<%
	String board_key = request.getParameter("num"); //번호 받아오기
	
	BoardDAO dao = new BoardDAO();	//DAO 생성
	dao.updateVisitCount(board_key);	//조회수 증가 //DAO에서 조회수 증가를 가져와 View를 거치면 1증가
	BoardDTO dto = dao.selectView(board_key);	//게시물 가져오기
	Member member = (Member) session.getAttribute("principal");
	String m_key = "";
	if(member != null) {
		m_key = String.valueOf(member.getMember_key());
		}
	request.setAttribute("num", dto.getBoard_key());
%>

<title>게시판</title>
<script>
	function deletePost() {
		var confiremed = confirm("정말로 삭제하시겠습니까?");
		if(confiremed){
			var from = document.writeFrm;		//이름이 "WriteFrm" 인 폼 선택
			from.method = "post";		//전송방식
			from.action = "DeleteProcess.jsp";	//전송경로
			from.submit();		//폼값 전송	
		}
	}
</script>
	<h2>게시판 - 상세보기</h2>
	<form name="writeFrm">
		<input type="hidden" name="board_key" value="<%= board_key%>" />
		<table border="1" width="90%">
			<tr>
				<td>번호</td>
				<td id = "board_key"><%=dto.getBoard_key() %></td>
				<td>작성자</td>
				<td><%=dto.getMember_name() %></td>
			</tr>
			<tr>
				<td>작성일</td>
				<td><%=dto.getBoard_date() %></td>
				<td>조회수</td>
				<td><%=dto.getBoard_visitcount() %></td>
			</tr>
			<tr>
				<td>제목</td>
				<td colspan="3"><%=dto.getBoard_title() %></td>
			</tr>
			<tr>
				<td>내용</td>
				<td colspan="3" height="100">
				<%=dto.getBoard_content().replace("\r\n", "<br/>") %></td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<%
					if(member != null && m_key.equals(dto.getMember_key())){
					%>	
					<button type="button" onclick=
						"location.href='Edit.jsp?num=<%=dto.getBoard_key()%>'">
						수정하기</button>
					<button type="button" onclick="deletePost();">삭제하기</button>
					<%
					}
					%>
					<button type="button" onclick="location.href='List.jsp';">
					목록보기
					</button>
				</td>
			</tr>		
		</table>
	</form>
<%@ include file="/include/footer.jsp" %>