package domain.member;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
import java.sql.SQLException;

import config.DBConnect;
import domain.member.dto.JoinReqDto;
import domain.member.dto.LoginReqDto;

public class MemberDao extends DBConnect{
	//회원가입, 회원수정, 아이디중복체크, 로그인
	
	//회원가입
	public int save(JoinReqDto dto) {
		
		int result = 0;
		String query = "insert into member(member_key, member_name, member_id, member_pw, member_email, member_address, member_phone, member_cancel)"
				+ " values(member_seq.nextval,?,?,?,?,?,?,?)";
		//PreparedStatement psmt = null;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getMember_name());
			psmt.setString(2, dto.getMember_id());
			psmt.setString(3, dto.getMember_pw());
			psmt.setString(4, dto.getMember_email());
			psmt.setString(5, dto.getMember_address());
			psmt.setString(6, dto.getMember_phone());
			psmt.setString(7, dto.getMember_cancel());
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	//회원정보찾기 - 로그인
	public int findById(String member_id) {
		int result = 0;
		String query = "select * from member where member_id=?";
		//PreparedStatement psmt = null;
		//ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, member_id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	//로그인
	public Member findBymember_idAndMember_pw(LoginReqDto dto) {
		Member member = null;
		String query = "select * from member where member_id=? and member_pw=?";
		//PreparedStatement psmt = null;
		//ResultSet rs = null;
		try {
			psmt = conn.prepareStatement(query);
			psmt.setString(1, dto.getMember_id());
			psmt.setString(2, dto.getMember_pw());
			rs = psmt.executeQuery();
			if(rs.next()) {
				member = Member.builder()
							.member_key(rs.getInt("member_key"))
							.member_id(rs.getString("member_id"))
							.member_pw(rs.getString("member_pw"))
							.member_name(rs.getString("member_name"))
							.member_address(rs.getString("member_address"))
							.member_email(rs.getString("member_email"))
							.member_phone(rs.getString("member_phone"))
							.member_cancel(rs.getString("member_cancel"))
							.build();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return member;
	}
	
	
}
