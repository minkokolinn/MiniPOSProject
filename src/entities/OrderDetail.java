package entities;

import java.io.Serializable;
import java.time.LocalDate;

public class OrderDetail implements Serializable{	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//for one item
	private int count;
	private int subTotal;
	private int total;
	private int tax;
	
	//***
	private Item item;
	//
	
	//****
	private Voucher voucher;	//lat shih orderdetail shi dk voucher yk info pyn u yan
	//
	
	public Voucher getVoucher() {
		return voucher;
	}
	
	public void setVoucher(Voucher voucher) {
		this.voucher=voucher;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(int subTotal) {
		this.subTotal = subTotal;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public LocalDate getSaleDate() {	//order detail mha->thu shi nay dk voucher ko kyih, ae voucher yk sale date ko u
		return voucher.getSaleDate();
	}
	
	public Category getCategory() {
		return item.getCategory();
	}
	
	//TODO: Customer method
	public int getUnitPrice() {
		return null==item?0:item.getPrice();
	}
	
	public void calculate() {
		subTotal=getUnitPrice()*count;
		Double rawTax=subTotal*0.05;
		tax=rawTax.intValue();
		total=tax+subTotal;
	}
}
