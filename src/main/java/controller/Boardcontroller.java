package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.board.BoardDTO;
import domain.member.Member;
import service.BoardService;
import util.Script;

@WebServlet("/board")
public class Boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public Boardcontroller() {
        super();
    }
	
	protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=utf-8");
		String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	BoardService boardservice = new BoardService();
    	
    	if(cmd.equals("list")) {
    		List<BoardDTO> dto = boardservice.boardList();
    		req.setAttribute("boardList", dto);
    		req.getRequestDispatcher("board/List.jsp").forward(req, res);
    	}else if(cmd.equals("write")) {
    		String title = req.getParameter("board_title");
    		String content = req.getParameter("board_content");
    		HttpSession session = req.getSession();
    		Member member = (Member) session.getAttribute("principal");
    		
    		BoardDTO dto = new BoardDTO(); 
    		dto.setBoard_title(title);
    		dto.setBoard_content(content);
    		dto.setMember_key(String.valueOf(member.getMember_key()));
    		dto.setMember_name(member.getMember_name());
    		
    		int result = boardservice.insertWrite(dto);
    		
    		if (result == 1){
    			req.getRequestDispatcher("board?cmd=list").forward(req, res);
    		}else{
    			Script.back("글쓰기에 실패했습니다.", res);
    		}
    		
    	}else if(cmd.equals("view")) {
    		String board_key = req.getParameter("num");
    		boardservice.updateVisitCount(board_key);
    		boardservice = new BoardService();
    		BoardDTO dto = boardservice.selectView(board_key);
    		req.setAttribute("boardDto", dto);
    		req.getRequestDispatcher("board/View.jsp").forward(req, res);
    	}else if(cmd.equals("edit")) {
    		String board_key = req.getParameter("num");
    		BoardDTO dto = boardservice.selectView(board_key);
    		req.setAttribute("boardDto", dto);
    		req.getRequestDispatcher("board/Edit.jsp").forward(req, res);
    	}else if(cmd.equals("editprocess")) {
    		String board_key = req.getParameter("num");
    		String board_title = req.getParameter("title");
    		String board_content = req.getParameter("content");
    		BoardDTO dto = new BoardDTO();
    		dto.setBoard_key(board_key);
    		dto.setBoard_title(board_title);
    		dto.setBoard_content(board_content);
    		int result = boardservice.updateEdit(dto);
    		if(result == 1) {
    			Script.alertMsg("글이 수정되었습니다.", "board?cmd=view&num="+board_key, res);
    		}else {
    			Script.back("수정 실패", res);
    		}
    	}else if(cmd.equals("delete")) {
    		String board_key = req.getParameter("board_key");
    		int result = boardservice.deletePost(board_key);
    		if(result == 1) {
    			Script.alertMsg("글이 삭제되었습니다.", "board?cmd=list", res);
    		}else {
    			Script.back("수정 실패", res);
    		}
    	}
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
