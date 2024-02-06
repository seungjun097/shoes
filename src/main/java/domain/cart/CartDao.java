package domain.cart;

import java.sql.SQLException;

import config.DBConnect;
import domain.cart.dto.SaveReqCartDto;

public class CartDao extends DBConnect{
	
	public int cartSave(SaveReqCartDto dto) {
		int result = 0;
		String query = "insert into cart(cart_key, member_key, item_key, item_size, item_amount)"
				+ " values(cart_seq.nextval, ?, ?, ?, ?)";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, dto.getMember_key());
			psmt.setInt(2, dto.getItem_key());
			psmt.setInt(3, dto.getItem_size());
			psmt.setInt(4, dto.getItem_amount());
			result = psmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
