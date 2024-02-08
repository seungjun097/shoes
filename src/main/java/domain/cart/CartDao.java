package domain.cart;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DBConnect;
import domain.cart.dto.SaveReqCartDto;
import domain.cart.dto.listReqCartDto;

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
	
	public List<listReqCartDto> cartlist(int member_key) {
		
		List<listReqCartDto> cartlist = new ArrayList<listReqCartDto>();
		
		String query = "select b.cart_key, b.item_key, b.member_key,"
				+ " b.item_amount, b.item_size, i.item_cart_img, i.item_name, i.item_price"
				+ " from cart b join item i on b.item_key = i.item_key"
				+ " where member_key ="+member_key;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				listReqCartDto dto = new listReqCartDto();
				dto.setCart_key(rs.getInt(1));
				dto.setItem_key(rs.getInt(2));
				dto.setMember_key(rs.getInt(3));
				dto.setItem_amount(rs.getInt(4));
				dto.setItem_size(rs.getInt(5));
				dto.setItem_cart_img(rs.getString(6));
				dto.setItem_name(rs.getString(7));
				dto.setItem_price(rs.getInt(8));
				cartlist.add(dto);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return cartlist;
	}
	
	public int cartDelete(int cart_key) {
		int result = 0;
		String query = "delete from cart where cart_key =?";
		try {
			psmt = conn.prepareStatement(query);
			psmt.setInt(1, cart_key);
			result = psmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public int cartListNum(int member_key) {
		int result = 0;
		String query = "select count(*) from cart where member_key ="+member_key;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
