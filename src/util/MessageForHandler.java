package util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MessageForHandler {	//for about message alert
	
	private static Alert alert=new Alert(null);
	
	static {
		alert.setResizable(true);	//class level mhr alert ko sa sa sout chin htl a myl setResizable ko false htr chin loh
	}
	
	public static void showalert(String message) {
		
		alert.setAlertType(AlertType.INFORMATION);
		alert.setHeaderText("Message From POS Application");
		alert.setContentText(message);
		alert.setTitle("About");
		alert.show();
		
	}
	
	public static void showAlert(Exception e) {
		AlertType type=e instanceof PosException ? AlertType.WARNING:AlertType.ERROR;	//win lar dk exception ka PosException 1 khu lrr// PosException yk child lrr pop
		
		String message=null;
		if (null==e.getMessage()) 
			message="Please contact to Dev Team!";
		else 
			message=e.getMessage();
		
		
		alert.setAlertType(type);
		alert.setHeaderText("Error in Application");
		alert.setContentText(message);
		alert.setTitle("Error");
		alert.show();
	}
	
	public static void toFront() {
		Stage window=(Stage)alert.getDialogPane().getScene().getWindow();	//messagebox lay paw lar dk stage ko pyn swl htoke
		
		window.setAlwaysOnTop(true);
	}
}
