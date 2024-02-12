package domain.board;

import java.sql.Date;

public class BoardDTO {
	//멤버 변수 선언
	private String board_key;  //num
	private String member_key;	//id
	private String board_content;
	private String board_title;
	private Date board_date;
	private String board_visitcount;
	private String member_name;
	public String getBoard_key() {
		return board_key;
	}
	public void setBoard_key(String board_key) {
		this.board_key = board_key;
	}
	public String getMember_key() {
		return member_key;
	}
	public void setMember_key(String member_key) {
		this.member_key = member_key;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public java.sql.Date getBoard_date() {
		return board_date;
	}
	public void setBoard_date(java.sql.Date board_date) {
		this.board_date = board_date;
	}
	public String getBoard_visitcount() {
		return board_visitcount;
	}
	public void setBoard_visitcount(String board_visitcount) {
		this.board_visitcount = board_visitcount;
	}
	public String getMember_name() {
		return member_name;
	}
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	
}
