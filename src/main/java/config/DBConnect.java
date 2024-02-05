package config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnect {
	
	public Connection conn;
	public PreparedStatement psmt;
	public Statement stmt;
	public ResultSet rs;
	
	//커넥트 객체 생성 Dao를 만들 때 DBConnect를 Extend해서 사용. 선생님 fileUpload의 gallerydao를 참조
	
	public DBConnect() {
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			DataSource ds = (DataSource)envContext.lookup("jdbc/myoracle");
			conn = ds.getConnection();
			System.out.println("Shoesmall_DB연결");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(conn != null) conn.close();
			if(psmt != null) psmt.close();
			if(stmt != null) stmt.close();
			if(rs != null) rs.close();
			System.out.println("Shoesmall_DB종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
