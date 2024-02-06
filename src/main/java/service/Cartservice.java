package service;

import domain.cart.CartDao;
import domain.cart.dto.SaveReqCartDto;

public class Cartservice {
	
	private CartDao cartDao;
	
	public Cartservice() {
		this.cartDao = new CartDao();
	}

	public int cartSave(SaveReqCartDto dto) {
		System.out.println("서비스 : "+cartDao.cartSave(dto));
		return cartDao.cartSave(dto);
	}
}
