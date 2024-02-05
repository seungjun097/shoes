package service;

import domain.member.MemberDao;
import domain.member.dto.JoinReqDto;

public class MemberService {

	private MemberDao memberDao;

	// 매번 UserService인스턴스가 생성될때 MemberDao인스턴스를 필드로 할당한다.
	public MemberService() {
		this.memberDao = new MemberDao();
	}

	// 회원가입, 로그인, 로그아웃, 아이디중복체크, 회원정보수정
	public int join(JoinReqDto dto) {
		return memberDao.save(dto);
	}

	
	public int member_idCheck(String member_id) { 
		return memberDao.findById(member_id); 
	}

}
