package entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Voucher implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private LocalDate saleDate;
	private LocalTime saleTime;
	private List<OrderDetail> list;
	
	public Voucher() {
		list=new ArrayList<>();
	}
	
	public void setSaleTime(LocalTime saleTime) {
		this.saleTime = saleTime;
	}
	
	public LocalTime getSaleTime() {
		return saleTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(LocalDate saleDate) {
		this.saleDate = saleDate;
	}

	public List<OrderDetail> getList() {
		return list;
	}

	public void setList(List<OrderDetail> list) {
		this.list = list;
	}
}
