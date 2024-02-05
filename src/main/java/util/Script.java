package util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Script {
	//경고창 출력 후 이전 페이지로 이동
	public static void back(String msg, HttpServletResponse response) {
		//history.back(); 라는 함수가 있는데 이전페이지로 이동한다.
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('" +msg+ "');");
			out.print("history.back();");
			out.print("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//경고창 출력후 지정된 경로로 페이지 이동
	public static void alertMsg(String msg, String url, HttpServletResponse response) {
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print("<script>");
			out.print("alert('" +msg+ "');");
			out.print("location.href='" +url+ "';");
			out.print("</script>");
			out.flush();
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
