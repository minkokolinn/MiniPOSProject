package service.memory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import entities.Category;
import entities.Item;
import entities.OrderDetail;
import entities.Voucher;
import service.SaleService;

public class SaleServiceImpl implements SaleService{

	private static SaleService saleService=new SaleServiceImpl();
	private static final String FILE_NAME="sale.obj";
	
	private List<Voucher> vouchers;
	
	@SuppressWarnings("unchecked")	//warning ma tat ag
	SaleServiceImpl() {
		// TODO Auto-generated constructor stub
		if (vouchers==null) 
			vouchers=new ArrayList<>();
		
		try(ObjectInputStream input=new ObjectInputStream(
				new FileInputStream(FILE_NAME))) {
			vouchers=(List<Voucher>) input.readObject();
		} catch (Exception e) {
			System.err.println("First time loading..");
		}
	}
	
	public static SaleService getInstance() {
		return saleService;
	}
	
	@Override
	public void paid(Voucher voucher) {
		// TODO Auto-generated method stub
		voucher.getList().forEach(od->od.setVoucher(voucher));
		
		vouchers.add(voucher);
		
		saveFile(vouchers);
	}

	private void saveFile(List<Voucher> vouchers2) {
		// TODO Auto-generated method stub
		try (ObjectOutputStream output=new ObjectOutputStream(
				new FileOutputStream(FILE_NAME))){
			
			output.writeObject(vouchers);	//file htl ko save / file htl ko yout ag loke
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderDetail> search(Category c, Item item, LocalDate dateFrom, LocalDate dateTo) {
		// TODO Auto-generated method stub
		Predicate<OrderDetail> cond=a->true;
		
		if (null!=c) {
			cond=cond.and(detail->detail.getItem().getCategory()==c);
		}
		
		if (null!=item) {
			cond=cond.and(detail->detail.getItem().equals(item));
		}
		
		if (null!=dateFrom) {
			cond=cond.and(detail->detail.getVoucher()
					.getSaleDate().compareTo(dateFrom)>=0);
		}
		
		if (null!=dateTo) {
			cond=cond.and(detail->detail.getVoucher()
					.getSaleDate().compareTo(dateTo)<=0);
		}
		//stream ti sout yin vouchers list ka voucher 1 saung chin c a phyik pyn khwel htwt
		//vouchers list ko breakdown loke pee stream yk data structure aphyik pyg
		//vouchers ko break down loke yin id,saleDate,list so pee khwel htwt pay mk list ka List 1 khu phyik nay tok stream mhr shi dk method tay tone loh ma ya pyn woo
		
		return vouchers.stream()
				.flatMap(voucher->voucher.getList().stream())
				.filter(cond)
				.collect(Collectors.toList());
	}
	
}
