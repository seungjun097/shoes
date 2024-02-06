package domain.cart.dto;

import lombok.Data;

@Data
public class SaveReqCartDto {
	private int member_key;
	private int item_key;
	private int item_size;
	private int item_amount;
}
