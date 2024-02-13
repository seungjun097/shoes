package domain.member.dto;

import lombok.Data;

@Data
public class LoginReqDto {
	private String member_email;
	private String member_pw;
}
