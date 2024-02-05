package domain.member.dto;

import lombok.Data;

@Data
public class JoinReqDto {
	 private String member_name;
	 private String member_id;
	 private String member_pw;
	 private String member_email;
	 private String member_address;
	 private String member_phone;
	 private String member_cancel;
}
