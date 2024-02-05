package domain.member;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private int member_key;
	private String member_name;  
	private String member_id;  
	private String member_pw;  
	private String member_email; 
	private String member_address; 
	private String member_phone;
	private Date member_regidate;
	private String member_cancel;
}
