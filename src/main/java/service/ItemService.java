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
		return itemDao.findWomanList(page);
	}
	public List<Item> manList(int page){
		return itemDao.findManList(page);
	}
	
	 public Item itemDetail(int id){ 
		this.itemDao = new ItemDao();
		return itemDao.findById(id); 
	}
	 public int pagenum() {
			return itemDao.count();
	}
}
