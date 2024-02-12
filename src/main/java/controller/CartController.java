package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import domain.cart.dto.SaveReqCartDto;
import domain.cart.dto.listReqCartDto;
import domain.member.Member;
import service.Cartservice;
import util.Script;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    
    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("utf-8");
    	res.setContentType("text/html; charset=utf-8");
    	String cmd = req.getParameter("cmd");
    	Cartservice cartservice = new Cartservice();
    	
    	if(cmd.equals("save")) {
    		BufferedReader br = req.getReader();
    		String data = br.readLine();
    		Gson gson = new Gson();
    		SaveReqCartDto dto = gson.fromJson(data, SaveReqCartDto.class);
    		int result = cartservice.cartSave(dto);
    		if(result == 1) {
    			PrintWriter out = res.getWriter();
        		out.print("성공");
        		out.flush();
        		out.close();
    		};
    	}else if(cmd.equals("list")) {
    		HttpSession session = req.getSession();
    		Member member = (Member) session.getAttribute("principal");
    		List<listReqCartDto> cartlist = new ArrayList<listReqCartDto>();
    		cartlist = cartservice.cartlist(member.getMember_key());
    		req.setAttribute("cartlist", cartlist);
    		req.getRequestDispatcher("cart/shopingCart.jsp").forward(req, res);
    		
    	}else if(cmd.equals("delete")) {
    		int cart_key = Integer.parseInt(req.getParameter("cartkey"));
    		int result = cartservice.cartDelete(cart_key);
    		if(result == 1) {
    			req.getRequestDispatcher("cart?cmd=list").forward(req, res);
    		}else {
    			Script.back("삭제실패", res);
    		}
    		
    	}else if(cmd.equals("cartlistnum")) {
    		HttpSession session = req.getSession();
    		Member member = (Member) session.getAttribute("principal");
    		PrintWriter out = res.getWriter();
    		if(member != null) {
    			int result = cartservice.cartlistnum(member.getMember_key());
        		out.print(result);
    		}else {
    			out.print(0);
    		}
    		out.flush();
    		out.close();
    	}else if(cmd.equals("checkout")) {
    		HttpSession session = req.getSession();
    		Member member = (Member) session.getAttribute("principal");
    		List<listReqCartDto> cartlist = new ArrayList<listReqCartDto>();
    		cartlist = cartservice.cartlist(member.getMember_key());
    		req.setAttribute("cartlist", cartlist);
    		req.getRequestDispatcher("cart/checkout.jsp").forward(req, res);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
