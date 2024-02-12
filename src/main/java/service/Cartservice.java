package service;

import java.util.List;

import domain.cart.CartDao;
import domain.cart.dto.SaveReqCartDto;
import domain.cart.dto.listReqCartDto;

public class Cartservice {
	CartDao cartDao;
	
	public Cartservice() {
		this.cartDao = new CartDao();
	}

	public int cartSave(SaveReqCartDto dto) {
		return cartDao.cartSave(dto);
	}
	public List<listReqCartDto> cartlist(int member_key) {
		return cartDao.cartlist(member_key);
	}
	
	public int cartDelete(int cart_key) {
		return cartDao.cartDelete(cart_key);
	}
	
	public int cartlistnum(int member_key) {
		return cartDao.cartListNum(member_key);
	}
}
