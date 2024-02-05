package domain.cart;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
	private int cart_key;
	private int member_key;
	private int item_key;
	private int cart_order_num;
	private Date cart_add_date;
}
