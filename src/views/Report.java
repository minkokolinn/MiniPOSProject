package views;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import entities.Category;
import entities.Item;
import entities.OrderDetail;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import service.ItemService;
import service.SaleService;

public class Report implements Initializable{

    @FXML
    private ComboBox<Category> category;

    @FXML
    private ComboBox<Item> item;

    @FXML
    private DatePicker datefrom;

    @FXML
    private DatePicker dateto;
    
    @FXML
    private TableView<OrderDetail> table;
    
    private SaleService saleService;
    
    private ItemService itemService;

    @FXML
    void clear() {
    	category.setValue(null);
    	item.setValue(null);
    	datefrom.setValue(null);
    	dateto.setValue(null);
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		saleService=SaleService.getInstance();
		itemService=ItemService.getInstance();
		
		category.getItems().addAll(Category.values());
		
		category.valueProperty().addListener((a,b,c)->{
			item.getItems().clear();
			Category cat=category.getValue();
			if (cat!=null) {
				List<Item> list = itemService.search(cat, null);
				item.getItems().addAll(list);
			}
			search();
		});
		
		item.valueProperty().addListener((a,b,c)->{
			search();
		});
		
		datefrom.valueProperty().addListener((a,b,c)->{
			search();
		});
		
		dateto.valueProperty().addListener((a,b,c)->{
			search();
		});
		
		datefrom.setValue(LocalDate.now());
	}
	
	private void search() {
		table.getItems().clear();
		
		List<OrderDetail> list = saleService.search(category.getValue(), item.getValue(), datefrom.getValue(), dateto.getValue());
		
		table.getItems().addAll(list);
	}

}
