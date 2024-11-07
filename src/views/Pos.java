package views;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.ToIntFunction;

import entities.Category;
import entities.Item;
import entities.OrderDetail;
import entities.Voucher;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import service.ItemService;
import service.SaleService;
import util.MessageForHandler;
import util.PosException;

public class Pos implements Initializable{
	
	private ItemService itemService;
	private SaleService saleService;

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField idName;

    @FXML
    private Label headTotal;

    @FXML
    private TableView<Item> itemsTable;

    @FXML
    private TableView<OrderDetail> cartTable;

    @FXML
    private Label subTotal;

    @FXML
    private Label total;

    @FXML
    private Label tax;

    @FXML
    void clearCart() {
    	
    	cartTable.getItems().clear();
    	calculate();
    }
    
    private Integer getData(ToIntFunction<OrderDetail> value) {		//value -> subTotal ll win ng, tax ll win ng
    	return cartTable.getItems().stream().mapToInt(value).sum();	//tointfunction ko tone dr value pay pee int pyn say chin loh
    }

    private void calculate() {
    	
		subTotal.setText(getData(order->order.getSubTotal()).toString());
		tax.setText(getData(order->order.getTax()).toString());
		total.setText(getData(order->order.getTotal()).toString());
		
	}

	@FXML
    void clearSearch() {
    	category.setValue(null);
    	idName.clear();
    }

    @FXML
    void delete() {
    	try {
			OrderDetail order=cartTable.getSelectionModel().getSelectedItem();
			if (order!=null) {
				cartTable.getItems().remove(order);
				calculate();
			}else {
				throw new PosException("Please select item in cart table to delete!");
			}
		} catch (Exception e) {
			// TODO: handle exception
			MessageForHandler.showAlert(e);
			MessageForHandler.toFront();
		}
    }

    @FXML
    void paid() {
    	try {
			if (cartTable.getItems().isEmpty()) {
				throw new PosException("Please select and add item to cart!");
			}
    		
			Voucher voucher=new Voucher();
			voucher.setSaleDate(LocalDate.now());
			voucher.setSaleTime(LocalTime.now());
			voucher.setId(voucher.getSaleTime().toSecondOfDay());
			
			voucher.getList().addAll(cartTable.getItems());
			
			saleService.paid(voucher);
			clearCart();
			
			
			MessageForHandler.showalert("Size : "+
						saleService.search(null, null, null, null).size()
						+"\nVoucher ID : "+voucher.getId());
			
		} catch (Exception e) {
			// TODO: handle exception
			MessageForHandler.showAlert(e);
		}
    }
    
    public void search() {
    	try {
			List<Item> items=itemService.search(category.getValue(), idName.getText());
			itemsTable.getItems().clear();
			itemsTable.getItems().addAll(items);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			MessageForHandler.showAlert(e);
		}
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemService=ItemService.getInstance();
		saleService=SaleService.getInstance();
		
		category.getItems().addAll(Category.values());
		
		headTotal.textProperty().bind(total.textProperty());	//subTotal mhr pyg dr nk headTotal like pyg ag
		
		category.valueProperty().addListener((a,b,c)->search());
		idName.textProperty().addListener((a,b,c)->search());
		
		itemsTable.setOnMouseClicked(event->{
			if (event.getClickCount()==2) {
				Item item=itemsTable.getSelectionModel().getSelectedItem();
				
				//cart htl mhr same order detail shi yin count bl toe
				OrderDetail detail=cartTable.getItems().stream()
						.filter(order->order.getItem().getId()==item.getId())
						.findAny()
						.orElse(null);	//br mha find ma ya yin null pyn

				
				if (detail==null) {
					detail=new OrderDetail();
					detail.setItem(item);
					cartTable.getItems().add(detail);
				}

				detail.setCount(detail.getCount()+1);
				detail.calculate();
				
				cartTable.refresh();	//table ui mhr shi dk data nk nout khwel ka data ko synchorized pyn loke
				
				calculate();
			}
		});
		
		search();	//Pos ko pyn htr dk nay mhr ->d har kygt data pyn tat ng
	}

}
