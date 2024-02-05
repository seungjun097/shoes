package domain.member;

import java.sql.SQLException;

import config.DBConnect;
import domain.member.dto.JoinReqDto;

public class MemberDao extends DBConnect{
	//회원가입, 회원수정, 아이디중복체크, 로그인
	
	//회원가입
	public int save(JoinReqDto dto) {
		
		int result = 0;
		String query = "insert into member(member_key, member_name, member_id, member_pw, member_email, member_address, member_phone, member_cancel)"
				+ " values(member_seq.nextval,?,?,?,?,?,?,?)";
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
}
