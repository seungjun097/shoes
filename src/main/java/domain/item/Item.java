package domain.item;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Item {
	private int item_key;
	private String item_name;
	private int item_price;
	private String item_cate;
	private int item_size;
	private int item_stock;
	private String item_gender;
	private Date item_date;
	private String item_list_img;
	private String item_detail_img;
	private String item_cart_img;
	private String item_color;
	private String item_content;
}
