package domain.cart.dto;

import lombok.Data;

@Data
public class listReqCartDto {
	private int cart_key;
	private int member_key;
	private int item_key;
	private int item_size;
	private int item_amount;
	private String item_cart_img;
	private String item_name;
	private int item_price;
}
