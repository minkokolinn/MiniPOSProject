package service;

import java.time.LocalDate;
import java.util.List;

import entities.Category;
import entities.Item;
import entities.OrderDetail;
import entities.Voucher;
import service.memory.SaleServiceImpl;

public interface SaleService {
	
	public static SaleService getInstance() {
		return SaleServiceImpl.getInstance();
	}
	
	void paid(Voucher voucher);
	
	List<OrderDetail> search(Category c,Item item,LocalDate dateFrom,LocalDate dateTo);
	
}
