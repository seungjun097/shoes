<%@page import="domain.member.Member"%>
<%@page import="util.Script"%>
<%@page import="domain.board.BoardDAO"%>
<%@page import="domain.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//폼값 받기
	String title = request.getParameter("board_title");
	String content = request.getParameter("board_content");
	Member member = (Member) session.getAttribute("principal");

	//폼값을 DTO 객체에 저장
	BoardDTO dto = new BoardDTO(); 
	dto.setBoard_title(title);
	dto.setBoard_content(content);
	//session영역에 저장되있는 DTO를 담은 이유는 board와 member의 id가 외래키로 연결되있어
	//id가 빈값이면 insert할때 제약조건위반으로 오류 발생하기때문
	dto.setMember_key(String.valueOf(member.getMember_key()));
	dto.setMember_name(member.getMember_name());
	//DAO객체를 통해 DB에 DTO 저장
	BoardDAO dao = new BoardDAO();
	int iResult = dao.insertWrite(dto);
	dao.close();
	
	//성공 or 실패
	if (iResult == 1){
		response.sendRedirect("List.jsp");
	}else{
		Script.back("글쓰기에 실패했습니다.", response);
	}
	
%>