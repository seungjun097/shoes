
<%@page import="domain.board.BoardDTO"%>
<%@page import="domain.board.BoardDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/include/header.jsp" %>
	<%
		//DAO를 생성해 DB에 연결
		BoardDAO dao = new BoardDAO();
		//사용자가 입력한 검색조건을 Map에 저장
		//Map<String, Object> param = new HashMap<String, Object>();
		
		List<BoardDTO> boardLists = dao.boardlist();
	%>
	<h2>목록보기</h2>
	<!-- 검색폼 -->
	<form method="get">
		<table border="1" width="90%">
			<tr>
				<td align="center">
					<select name="serchField">
						<option value="title">제목</option>
						<option value="content">내용</option>
					</select>
					<input type="text" name="serchWord" />
					<input type="submit" value="검색하기" />
				</td>
			</tr>
		</table>
	</form>
	<!-- 게시물 목록 테이블(표) -->
	<table border="1" width="90%">
		<!-- 각 컬럼의 이름 -->
		<tr>
			<th width="10%">번호</th>
			<th width="50%">제목</th>
			<th width="15%">작성자</th>
			<th width="10%">작성일</th>
			<th width="15%">조회수</th>
		</tr>
		<!-- 목록의 내용 -->
<%
	if(boardLists.isEmpty()){
		//게시물이 하나도 없을 때 
	
%>
	<tr>
		<td colspan="5" align="center">
			등록된 게시물이 없습니다.
		</td>	
	</tr>
<%
}else{
	//게시물이 있을때
	//int virtualNum = 0; //화면상에서의 게시물 번호
	for(BoardDTO dto : boardLists){
	//	virtualNum = totalCount--;  //전체 게시물 수에서 시작해 1씩 감소
%>
 <tr>
 	<td align="center"><%=dto.getMember_key() %></td>
 	<td align="center"><a href="View.jsp?num=<%= dto.getBoard_key()%>"><%=dto.getBoard_title()%></td>
 	<td align="center"><%=dto.getMember_name() %></td>
 	<td align="center"><%=dto.getBoard_date() %></td>
 	<td align="center"><%=dto.getBoard_visitcount() %></td>
 </tr>

<%	
	}
}
%>
	</table>
	<!-- 목록 하단의 글쓰기 버튼 -->
	<table border="1" width="90%">
		<tr align="center">
			<td><button type="button" onclick="location.href='Write.jsp';">글쓰기
			</button></td>
		</tr>
	</table>
	
<%@ include file="/include/footer.jsp" %>