package service.memory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entities.Category;
import entities.Item;
import service.ItemService;
import util.MessageForHandler;

public class ItemServiceImpl implements ItemService{
	
	private static ItemService itemService=new ItemServiceImpl();
	
	private List<Item> items;
	
	ItemServiceImpl() {
		if (null==items) 
			items=new ArrayList<>();
	}
	
	public static ItemService getItemService() {
		return itemService;
	}

	@Override
	public List<Item> search(Category c, String idname) {
		//findAll, find by Category, find by Name, find by CategoryAndName
		Predicate<Item> cond=a->true;	//only boolean return -> predicate
		
		if (null!=c) {
			cond=cond.and(item->item.getCategory()==c);
		}
		
		if (null!=idname && !idname.isEmpty()) {
			Predicate<Item> id=item->String.valueOf(item.getId())
					.equals(idname);
			Predicate<Item> name=item->item.getName().toLowerCase()
					.startsWith(idname.toLowerCase());
			//id yike pee find or name yike pee find/ 2 khu lone yike pee find dr ma phyik ng
			
			cond=cond.and(id.or(name));
		}
		return items.stream().filter(cond).collect(Collectors.toList());
	}

	@Override
	public void add(Item item) {
		
	}

	@Override
	public void add(String path) {
		// TODO Auto-generated method stub
		try {
			items=Files.lines(Paths.get(path)).skip(1)	//.lines ka stream ko tee sout pee thrr phyik
					.map(line->line.split("\t"))
					.map(arr->new Item(arr))
					.collect(Collectors.toList());
			
//			MessageForHandler.showalert("Size: "+items.size());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
