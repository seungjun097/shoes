package service;

import domain.member.Member;
import domain.member.MemberDao;
import domain.member.dto.EditReqDto;
import domain.member.dto.JoinReqDto;
import domain.member.dto.LoginKakaoReqDto;
import domain.member.dto.LoginReqDto;

public class MemberService {

	private MemberDao memberDao;

	// 매번 UserService인스턴스가 생성될때 MemberDao인스턴스를 필드로 할당한다.
	public MemberService() {
		this.memberDao = new MemberDao();
	}

	// 회원가입, 로그인, 로그아웃, 아이디중복체크, 회원정보수정
	//회원가입
	public int join(JoinReqDto dto) {
		return memberDao.save(dto);
	}

	//아이디중복체크
	public int member_emailCheck(String member_email) { 
		return memberDao.findByEmail(member_email); 
	}
  
	//로그인
	public Member login(LoginReqDto dto) {
		return memberDao.findBymember_IdAndMember_pw(dto);
	}
	
	//카카오로그인
	public Member kakaologin(LoginKakaoReqDto dto) {
		return memberDao.findBymember_emailAndMember_id(dto);
	}
	
	//회원정보수정
	public int edit(EditReqDto dto) {
		return memberDao.updateMember(dto);
	}
	
	//회원정보 조회
	public Member memberlist(int member_key) {
		return memberDao.memberlist(member_key);
	}
	
	//회원정보 삭제
	public int delete(int member_key) {
		return memberDao.deleteMember(member_key);
	}
}
