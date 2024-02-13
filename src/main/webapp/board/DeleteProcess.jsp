<%@page import="util.Script"%>
<%@page import="domain.board.BoardDAO"%>
<%@page import="domain.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	String num = request.getParameter("board_key");
	BoardDTO dto = new BoardDTO();
	BoardDAO dao = new BoardDAO();
		int delResult = 0;
		dto.setBoard_key(num);
		/* delResult = dao.deletePost(dto); */
		dao.close();
		
		//성공or실패처리
		if(delResult == 1){
			//성공 시 목록 페이지로 이동
			Script.alertMsg("삭제되었습니다.", "List.jsp", response);
		}else{
			//실패 시 이전 페이지로 이동
			Script.back("삭제에 실패하였습니다.", response);
		}
  	
	return;
	
%>