package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import domain.cart.dto.SaveReqCartDto;
import service.Cartservice;

@WebServlet("/cart")
public class CartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    
    protected void process(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String cmd = req.getParameter("cmd");
    	Cartservice cartservice = new Cartservice();
    	if(cmd.equals("save")) {
    		BufferedReader br = req.getReader();
    		String data = br.readLine();
    		Gson gson = new Gson();
    		SaveReqCartDto dto = gson.fromJson(data, SaveReqCartDto.class);
    		int result = cartservice.cartSave(dto);
    		System.out.println(result);
    		if(result == 1) {
    			PrintWriter out = res.getWriter();
        		out.print("성공");
        		out.flush();
        		out.close();
    		};
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

}
