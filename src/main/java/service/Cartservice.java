package service;

import java.util.List;

import domain.cart.CartDao;
import domain.cart.dto.SaveReqCartDto;
import domain.cart.dto.listReqCartDto;

public class Cartservice {

	public int cartSave(SaveReqCartDto dto) {
		CartDao cartDao = new CartDao();
		return cartDao.cartSave(dto);
	}
	public List<listReqCartDto> cartlist(int member_key) {
		CartDao cartDao = new CartDao();
		return cartDao.cartlist(member_key);
	}
	
	public int cartDelete(int cart_key) {
		CartDao cartDao = new CartDao();
		return cartDao.cartDelete(cart_key);
	}
	
	public int cartlistnum(int member_key) {
		CartDao cartDao = new CartDao();
		return cartDao.cartListNum(member_key);
	}
}
