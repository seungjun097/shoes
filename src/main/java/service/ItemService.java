package service;

import java.util.List;

import domain.item.Item;
import domain.item.ItemDao;

public class ItemService {
	
	private ItemDao itemDao;
	
	public ItemService() {
		this.itemDao = new ItemDao();
	}

	public int itemSave(Item item) {
		return itemDao.insertItem(item);
	}
	
	public List<Item> womanList(int page){
		System.out.println("우먼리스트서비스");
		return itemDao.findWomanList(page);
	}
	public List<Item> manList(){
		return itemDao.findManList();
	}
	
	 public Item itemDetail(int id){ 
		this.itemDao = new ItemDao();
		return itemDao.findById(id); 
	}
	 public int pagenum() {
			return itemDao.count();
	}
}
