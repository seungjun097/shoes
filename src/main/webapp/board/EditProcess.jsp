<%@page import="util.Script"%>
<%@page import="domain.board.BoardDAO"%>
<%@page import="domain.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//수정내용 얻기 //Edit에서 값을 가져온다
	request.setCharacterEncoding("utf-8");
	String board_key = request.getParameter("num");
	String board_title = request.getParameter("title");
	String board_content = request.getParameter("content");
	
	//DTO에 저장 //DTO라는 그릇에 값을 저장하고
	BoardDTO dto = new BoardDTO();
	dto.setBoard_key(board_key);
	dto.setBoard_title(board_title);
	dto.setBoard_content(board_content);
	
	//DB에 반영
	BoardDAO dao = new BoardDAO();
	int result = dao.updateEdit(dto);
	dao.close();
	
	//성공or실패 처리
	if(result == 1){
		response.sendRedirect("View.jsp?num=" + dto.getBoard_key());
	}else{
		Script.back("수정하기에 실패하였습니다.", response);
	}
	
%>