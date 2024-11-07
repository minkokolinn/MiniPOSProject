package views;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import util.LoginSetting;
import util.PasswordEncrypter;

public class Login implements Initializable{

    @FXML
    private Label message;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    void cancel() {
    	Platform.exit();
    }

    @FXML
    void login() {
    	try {
			LoginSetting.login(login.getText(), PasswordEncrypter.encrypt(password.getText()));	//throw tay nk exception or error tat ag louk htr dr / dr kygt ae tat tk error ka try catch mhr twr mhi pee / exception,error tat yin phyik chin dk har ko catch mhr yay ka mhr
			
			MainFrame.show();	//should be static becuase static can load eailer than instance
			
			message.getScene().getWindow().hide();
			
		} catch (Exception e) {
			// TODO: handle exception
			message.setText(e.getMessage());
		}
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		login.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if (arg0.getCode()==KeyCode.ENTER) {
					login();
				}
			}
		});
		
		password.setOnKeyPressed(e->{
			if (e.getCode()==KeyCode.ENTER) {
				login();
			}
		});
	}

}
