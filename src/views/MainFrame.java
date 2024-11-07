package views;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.ItemService;
import util.MessageForHandler;
import util.PosException;

public class MainFrame implements Initializable{
	
	private static final String POSSvg="M0 2v20h32v-20h-32zM30 20h-28v-16h28v16zM21 24h-10l-1 4-2 2h16l-2-2z";
	private static final String REPORTSvg="M27 0h-24c-1.65 0-3 1.35-3 3v26c0 1.65 1.35 3 3 3h24c1.65 0 3-1.35 3-3v-26c0-1.65-1.35-3-3-3zM26 28h-22v-24h22v24zM8 18h14v2h-14zM8 22h14v2h-14zM10 9c0-1.657 1.343-3 3-3s3 1.343 3 3c0 1.657-1.343 3-3 3s-3-1.343-3-3zM15 12h-4c-1.65 0-3 0.9-3 2v2h10v-2c0-1.1-1.35-2-3-2z";
	
	private static EventHandler<MouseEvent> showReport=null;
	private static EventHandler<MouseEvent> showPos=null;
	
	private ItemService itemService;
	private FileChooser chooser;
	
	@FXML
    private Label title_pos;
	
    @FXML
    private MenuItem close;

    @FXML
    private SVGPath icon;
    
    @FXML
    private HBox buttonicon;

    @FXML
    private StackPane content;

    @FXML
    private Label message;

    @FXML
    void about() {
    	String message=String.format("%n%-10s: 1.0%n%-11s: Min Ko Ko Linn","Version","Developer");
    	MessageForHandler.showalert(message);
    }

    @FXML
    void close() {
    	Platform.exit();
    }

    @FXML
    private MenuItem uploadmenu;
    
    @FXML
    void upload() {
    	try {
			File file=chooser.showOpenDialog(null);
			if (null==file) {
				throw new PosException("Please select a file to upload!");
			}
			
			itemService.add(file.getAbsolutePath());
			loadView("Pos.fxml"); 	//thu tat pee thrr view ko pyn khaw -> file upload loke pee pee chin data tat boh
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			MessageForHandler.showalert(e.getMessage());
		}
    }
    
    
    private void initFileChoose() {
    	chooser=new FileChooser();
    	
    	File path=new File(System.getProperty("user.home"),"Desktop");
    	
    	chooser.setInitialDirectory(path);
    	chooser.setTitle("Select text file to upload!!");
    	
    	ExtensionFilter extension=new FileChooser.ExtensionFilter("Text File Only", "*.txt");
    	chooser.setSelectedExtensionFilter(extension);
    	chooser.getExtensionFilters().add(extension);
    	
    }
    
    public static void show() {
    	try {
    		Parent mainframeroot=FXMLLoader.load(MainFrame.class.getResource("MainFrame.fxml"));
    		Stage stage=new Stage();
    		stage.setScene(new Scene(mainframeroot));
//    		stage.initStyle(StageStyle.UNDECORATED);
    		stage.setTitle("POS Application");
    		stage.show();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemService=ItemService.getInstance();
		
		itemService.add("items.txt");  //upload ma loke bl tin yan
		
		showPos=event->{
			loadView("Pos.fxml");		//to show Pos View
			title_pos.setText("Mini-POS/ Sale View");
			
			icon.setContent(POSSvg);
			buttonicon.setOnMouseClicked(showReport);	//report view pyg ma larr loh sout
			
			uploadmenu.setVisible(true);	//menu item
		};
		
		showReport=new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				// TODO Auto-generated method stub
				loadView("Report.fxml");		//to show Report View
				title_pos.setText("Mini-POS/ Report View");
				
				icon.setContent(REPORTSvg);
				buttonicon.setOnMouseClicked(showPos);	//pos view pyg ma lar loh sout
				
				uploadmenu.setVisible(false);	//menu item
			}
		};
		
		buttonicon.setOnMouseClicked(showReport);	//report ko pya pay pr loh sout nay
		loadView("Pos.fxml");
		title_pos.setText("Mini-POS/ Sale View");
		
		initFileChoose();
		
	}

	private void loadView(String fxml) {
		try {
			Parent root=FXMLLoader.load(getClass().getResource(fxml));
			content.getChildren().clear();
			content.getChildren().add(root);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
