package service;

import java.util.List;

import entities.Category;
import entities.Item;
import service.memory.ItemServiceImpl;

public interface ItemService {
	
	public static ItemService getInstance() {
		return ItemServiceImpl.getItemService();
	}
	
	List<Item> search(Category c,String itemname);
	
	void add(Item item);
	
	void add(String path);
}
