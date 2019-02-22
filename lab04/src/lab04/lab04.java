package lab04;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.*;
//import org.apache.commons.validator.routines.UrlValidator;


public class lab04 extends Application{

	
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane();
		//pane.setAlignment(Pos.CENTER);
	    pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
	    pane.setHgap(5.5);
	    pane.setVgap(5.5);
	    
		pane.add(new Label("Username:"),0,0);
		TextField name = new TextField();
		pane.add(name,1,0);
		
		pane.add(new Label("Password:"),0,1);
		PasswordField pass = new PasswordField();
		pane.add(pass,1,1);
		
		pane.add(new Label("Full Name:"),0,2);
		TextField fname = new TextField();
		pane.add(fname,1,2);
		
		pane.add(new Label("E-Mail:"),0,3);
		TextField email = new TextField();
		pane.add(email,1,3);
		//UrlValidator urlValidator = new UrlValidator();
		//if(!urlValidator.isValid(email.getText())) {
		//	pane.add(new TextField("Invalid E-Mail Address"),2,3);
		//}
		
		pane.add(new Label("Phone #:"),0,4);
		pane.add(new TextField(),1,4);
		
		pane.add(new Label("Date of Birth:"),0,5);
		pane.add(new DatePicker(),1,5);
		
		Button reg = new Button("Register");
		reg.setOnAction(e -> {
			System.out.print("Name: ");
			System.out.println(name.getText());
			System.out.print("Password: ");
			System.out.println(pass.getText());
			System.out.print("Full name: ");
			System.out.println(fname.getText());
			System.out.print("Email: ");
			System.out.println(email.getText());
			
		});
		pane.add(reg, 1, 6);
		
		
		
		Scene mainScene = new Scene(pane,600,400);
		primaryStage.setTitle("Lab 04");
		primaryStage.setScene(mainScene);
		primaryStage.show();
	}
	
			
	
	
	public static void main(String[] args) {
		launch(args);
	}

}

