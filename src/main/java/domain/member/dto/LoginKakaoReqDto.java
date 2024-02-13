package domain.member.dto;

import lombok.Data;

@Data
public class LoginKakaoReqDto {
	private String member_email;
	private String member_id;
}
