package entities;

import java.io.Serializable;

public class Item implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Category category;
	private int price;
	
	public Item() {
		
	}
	
	public Item(String[] line) {	//to get list from item.txt file (fixed)	//item.txt mha item tay ko space khwel pee thr array ya array mhr 4 khan shi
		id=Integer.parseInt(line[0]);
		category=Category.valueOf(line[1]);
		name=line[2];
		price=Integer.parseInt(line[3]);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return name;
	}
	
	
}
