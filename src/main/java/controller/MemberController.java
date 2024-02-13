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

import com.google.gson.Gson;

import domain.member.Member;
import domain.member.MemberDao;
import domain.member.dto.EditReqDto;
import domain.member.dto.JoinReqDto;
import domain.member.dto.LoginKakaoReqDto;
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
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=utf-8");
    	String cmd = req.getParameter("cmd");
    	System.out.println(cmd);
    	//서비스 객체 생성
    	MemberService memberService;
    	
    	//회원정보 수정 폼페이지 요청
    	if(cmd.equals("editForm")) {
    		//세션생성
    		memberService = new MemberService();
    		HttpSession session = req.getSession();
    		//세션에 담겨있는 Member리턴 
    		Member smember = (Member) session.getAttribute("principal");
    		//세션에 담겨있는 Member의 member_key반 받기 
    		//member_key로 회원정보 조회 
    		Member member = memberService.memberlist(smember.getMember_key());
    		//req에 속성으로 dto키에 회원정보 담아줌 
    		req.setAttribute("dto", member);
    		req.getRequestDispatcher("member/editForm.jsp")
    		.forward(req, res);
    	}
    	
    	//회원정보 수정 기능 요청(post요청)
    	else if(cmd.equals("edit")) {
    		memberService = new MemberService();
    		int member_key = Integer.parseInt(req.getParameter("member_key"));
    		String member_name = req.getParameter("member_name");
    		String member_id = req.getParameter("member_id");
    		String member_pw = req.getParameter("member_pw");
    		String member_email = req.getParameter("member_email");
    		String member_address = req.getParameter("member_address");
    		String member_phone = req.getParameter("member_phone");
    		String member_cancel = req.getParameter("member_cancel");
    		
    		EditReqDto dto = new EditReqDto();
    		dto.setMember_key(member_key);
    		dto.setMember_name(member_name);
    		dto.setMember_id(member_id);
    		dto.setMember_pw(member_pw);
    		dto.setMember_email(member_email);
    		dto.setMember_address(member_address);
    		dto.setMember_phone(member_phone);
    		dto.setMember_cancel(member_cancel);
   			
			int result = 0;
			result = memberService.edit(dto);
			if(result!=0) { 
				req.getRequestDispatcher("member?cmd=login")
        		.forward(req, res);
			}else { 
				Script.back("회원정보 수정 실패", res);
			}
			
    	}
    	
    	//회원정보 삭제 요청
    	else if(cmd.equals("delete")) {
    		memberService = new MemberService();
    		int member_key = Integer.parseInt(req.getParameter("member_key"));
    		int result = memberService.delete(member_key);
    		if(result==1) {
    			req.getRequestDispatcher("member?cmd=logout")
        		.forward(req, res);
    		}else {
    			Script.back("삭제실패", res);
    		}
    	}
    	
    	//로그인 폼페이지 요청
    	else if(cmd.equals("loginForm")) {
    		//응답페이지 지정
    		req.getRequestDispatcher("member/loginForm.jsp")
    		.forward(req, res);
    	
    	//로그아웃 요청
    	}else if(cmd.equals("logout")) {
    		HttpSession session = req.getSession();
    		session.invalidate();
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
    		memberService = new MemberService();
    		
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
    	
    	//이메일 중복확인 체크(ajax요청)
    	else if(cmd.equals("member_emailCheck")) {
    		//1.입력스트림 생성(폼전송이 아닐때)
    		memberService = new MemberService();
    		BufferedReader br = req.getReader();
    		String member_email = br.readLine();
    		int result = memberService.member_emailCheck(member_email);
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
    		memberService = new MemberService();
    		String member_email = req.getParameter("member_email");
    		String member_pw = req.getParameter("member_pw");
    		LoginReqDto dto = new LoginReqDto();
    		dto.setMember_email(member_email);
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
    	
    	//카카오 로그인 요청(post요청 ajax요청)
    	else if(cmd.equals("kakaologin")) {
    		//요청 문자 인코딩 설정
    		req.setCharacterEncoding("utf-8");
    		memberService = new MemberService();
    		//외부 데이터를 읽기 위한 입력 스트림 생성(폼전송이 아닌경우)
    		BufferedReader br = req.getReader();
    		//readLine() 외부데이터 읽어서 문자열로 리턴
    		String data = br.readLine();
    		
    		System.out.println("여기에요 : " + data);
    		//json --> 자바객체로 컨버팅
    		Gson gson = new Gson();
    		LoginKakaoReqDto dto = gson.fromJson(data, LoginKakaoReqDto.class);
    		System.out.println("로그인 dto: "+ dto);
    		//서비스에서 요청 후 Member타입으로 리턴
    		Member memberEntity = memberService.kakaologin(dto);
    		//응답하기 위한 출력스트림 생성
    		PrintWriter out = res.getWriter();
    		//로그인처리를 위한 세션 생성
    		HttpSession session = req.getSession();
    		session.setAttribute("kakao", dto); //이 정보로 접근해서 회원가입
    		if(memberEntity!=null) {
    			session.setAttribute("principal", memberEntity);
    			out.print("ok");
    		}else {
    			out.print("fail");
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
