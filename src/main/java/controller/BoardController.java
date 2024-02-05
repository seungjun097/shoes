package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
        
    }

    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	//요청시 전달된 파라미터 값("cmd")을 반환
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	//서비스 객체 생성
    	//UserService userService = new UserService();
    	
    	//로그인 폼페이지 요청
    	if(cmd.equals("write")) {
    		req.getRequestDispatcher("board/write.jsp")
    		.forward(req, res);
    	}
    	
    }
    
    //get 요청이 오면 doGet호출
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	//post 요청이 오면 doPost호출
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
