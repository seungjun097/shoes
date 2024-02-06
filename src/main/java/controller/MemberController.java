package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.member.Member;
import domain.member.dto.JoinReqDto;
import domain.member.dto.LoginReqDto;
import service.MemberService;
import util.Script;


@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberController() {
        super();
        
    }

    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	//요청시 전달된 파라미터 값("cmd")을 반환
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	//서비스 객체 생성
    	MemberService memberService = new MemberService();
    	
    	//로그인 폼페이지 요청
    	if(cmd.equals("loginForm")) {
    		//응답페이지 지정
    		req.getRequestDispatcher("member/loginForm.jsp")
    		.forward(req, res);
    	
    	//로그아웃 요청
    	}else if(cmd.equals("logout")) {
    		System.out.println("로그아웃 요청");
    		HttpSession session = req.getSession();
    		session.removeAttribute("principal");
    		res.sendRedirect("index.jsp");
    	}
    	
    	//회원가입 폼페이지 요청
    	else if(cmd.equals("joinForm")) {
    		//응답페이지 지정
    		req.getRequestDispatcher("/member/joinForm.jsp")
    		.forward(req, res);
    	}
    	
    	//회원가입 기능 요청(post요청)
    	else if(cmd.equals("join")) {
    		res.setContentType("text/html; charset=utf-8");
    		String member_name = req.getParameter("member_name");
    		String member_id = req.getParameter("member_id");
    		String member_pw = req.getParameter("member_pw");
    		String member_email = req.getParameter("member_email");
    		String member_address = req.getParameter("member_address");
    		String member_phone = req.getParameter("member_phone");
    		String member_cancel = req.getParameter("member_cancel");
    		JoinReqDto dto = new JoinReqDto();
    		dto.setMember_name(member_name);
    		dto.setMember_id(member_id);
    		dto.setMember_pw(member_pw);
    		dto.setMember_email(member_email);
    		dto.setMember_address(member_address);
    		dto.setMember_phone(member_phone);
    		dto.setMember_cancel(member_cancel);
    		System.out.println(dto.getMember_name());
    		int result = memberService.join(dto);
    		if(result==1) { //결과에 따라 응답해줄 페이지로 넘겨준다.
    			Script.alertMsg("회원가입 되었습니다.", "/shoes/index.jsp", res);
    		}else {
    			Script.back("회원가입 실패", res);
    		}
    	}
    	
    	//아이디 중복확인 체크(ajax요청)
    	else if(cmd.equals("member_idCheck")) {
    		//1.입력스트림 생성(폼전송이 아닐때)
    		BufferedReader br = req.getReader();
    		String member_id = br.readLine();
    		System.out.println("아이디는 : " + member_id);
    		int result = memberService.member_idCheck(member_id);
    		PrintWriter out = res.getWriter();
    		if(result==0) {
    			out.print("ok");
    		}else {
    			out.print("fail");
    		}
    		out.flush();
    		out.close();
    	}
    	
    	//로그인 기능 요청(post요청)
    	else if(cmd.equals("login")) {
    		String member_id = req.getParameter("member_id");
    		String member_pw = req.getParameter("member_pw");
    		LoginReqDto dto = new LoginReqDto();
    		dto.setMember_id(member_id);
    		dto.setMember_pw(member_pw);
    		Member memberEntity = memberService.login(dto);
    		if(memberEntity!=null) {
    			HttpSession session = req.getSession();
    			session.setAttribute("principal", memberEntity);
    			res.sendRedirect("index.jsp");
    		}else {
    			Script.back("로그인 실패", res);
    		}
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
