 package lab08;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.*;
import java.util.Scanner;

import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;



public class lab08solution extends Application{
	
	private TableView<StudentRecord> table;
	private ObservableList<StudentRecord> list;
	private File currentFileName = null;
	 
	@Override
    public void start(Stage stage) {
		table = new TableView<>();
		
		
		MenuBar menubar = new MenuBar();
        Menu menuFile = new Menu("File");
        
        MenuItem newItem = new MenuItem("New"); 
        newItem.setOnAction(e->{
        	table.getItems().clear();
        	currentFileName = null;
        });
        
        MenuItem OpenItem = new MenuItem("Open"); 
        OpenItem.setOnAction(e->{
        	FileChooser fileChooser = new FileChooser();
        	 fileChooser.setTitle("Open Resource File");
        	 fileChooser.getExtensionFilters().addAll(
        	         new ExtensionFilter("CSV Files", "*.csv"));
        	 File f = fileChooser.showOpenDialog(stage);
        	 if (f == null) {
          	    return;
          	 }
        	 currentFileName = f;
        	 list = FXCollections.observableArrayList();
        	 Scanner input;
			try {
				input = new Scanner(f);
				String data = "";
				input.nextLine(); //pass the headers
	             while(input.hasNextLine()) {
	                 data = input.nextLine() + "\n";
	                 String[] splitted = data.split(",");
	                 StudentRecord newData = new StudentRecord(splitted[0],Float.parseFloat(splitted[1]),
	                		 Float.parseFloat(splitted[2]),Float.parseFloat(splitted[3]));
	                table.setItems(list);
	             	table.getItems().add(newData);
	                 }
	             input.close();
	             } catch (FileNotFoundException e1) {
	            	 e1.printStackTrace();
	            	 }
        });
        
        MenuItem SaveItem = new MenuItem("Save"); 
        SaveItem.setOnAction(e->{
        	if(currentFileName == null) {
        		FileChooser fileChooser = new FileChooser();
	           	 fileChooser.setTitle("Open Resource File");
	           	 fileChooser.getExtensionFilters().addAll(
	   			     new ExtensionFilter("CSV Files", "*.csv"));
	           	 File f = fileChooser.showSaveDialog(stage);
	           	 if (f == null) {
	   			    return;
   			 }
	           	 currentFileName = f;
        	}
        	String csv = save(table);
			try {
			FileWriter f = new FileWriter(currentFileName);
			f.write(csv);
			f.close();
			} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}
        });
        
        MenuItem SaveAsItem = new MenuItem("SaveAs");
        SaveAsItem.setOnAction(e->{
        	FileChooser fileChooser = new FileChooser();
        	 fileChooser.setTitle("Open Resource File");
        	 fileChooser.getExtensionFilters().addAll(
        	         new ExtensionFilter("CSV Files", "*.csv"));
        	 File selectedFile = fileChooser.showSaveDialog(stage);
        	 if (selectedFile == null) {
         	    return;
         	 }
        	 String csv = save(table);
			try {
				FileWriter f = new FileWriter(selectedFile);
				f.write(csv);
	        	f.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	 
        });
        
        MenuItem ExitItem = new MenuItem("Exit"); 
        ExitItem.setOnAction(e->{
        	System.exit(0);
        });
        
        menuFile.getItems().addAll(newItem,OpenItem,SaveItem,SaveAsItem,ExitItem);
        menubar.getMenus().add(menuFile);
		
        TableColumn<StudentRecord,String> idCol = new TableColumn<StudentRecord,String>("SID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        
        TableColumn<StudentRecord,Float> asmtCol = new TableColumn<StudentRecord,Float>("Assignments");
        asmtCol.setCellValueFactory(new PropertyValueFactory<>("Asmt"));
        
        TableColumn<StudentRecord,Float> midCol = new TableColumn<StudentRecord,Float>("Midterm");
        midCol.setCellValueFactory(new PropertyValueFactory<>("Mid"));
        
        TableColumn<StudentRecord,Float> finCol = new TableColumn<StudentRecord,Float>("Final Exam");
        finCol.setCellValueFactory(new PropertyValueFactory<>("Final"));
        
        TableColumn<StudentRecord,Double> fmCol = new TableColumn<StudentRecord,Double>("Final Mark");
        fmCol.setCellValueFactory(new PropertyValueFactory<>("Mark"));
        
        TableColumn<StudentRecord,Double> gradeCol = new TableColumn<StudentRecord,Double>("Letter Grade");
        gradeCol.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        
        

     // Display row data
        table.getColumns().addAll(idCol,asmtCol,midCol,finCol,fmCol,gradeCol);
        table.prefHeightProperty().bind(stage.heightProperty());
        table.prefWidthProperty().bind(stage.widthProperty());
        
        
        
        
 
        
        GridPane root = new GridPane();
        root.setHgap(15.5);
        root.setVgap(15.5);
        root.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        root.add(table,0,1,5,4); //span over 5 column (rightwise) and 4 row (downwise)
        
        root.add(new Label("SID:"), 0, 5);
        String sid = "SID";
        TextField sidTxt = new TextField(sid);
        sidTxt.setStyle("-fx-text-fill: grey");
        root.add(sidTxt, 1, 5);
        
        root.add(new Label("Assignments:"), 2, 5);
        String asmt = "Assignments/100";
        TextField asmtTxt = new TextField(asmt);
        asmtTxt.setStyle("-fx-text-fill: grey");
        root.add(asmtTxt, 3, 5);
        
        root.add(new Label("Midterm:"), 0, 6);
        String mid = "Midterm/100";
        TextField midTxt = new TextField(mid);
        midTxt.setStyle("-fx-text-fill: grey");
        root.add(midTxt, 1, 6);
        
        root.add(new Label("Final Exam:"), 2, 6);
        String fin = "Final Exam/100";
        TextField finTxt = new TextField(fin);
        finTxt.setStyle("-fx-text-fill: grey");
        root.add(finTxt, 3, 6);
        
        Button btnAdd = new Button("Add");
        root.add(btnAdd, 1, 7);
        
        midTxt.setOnMousePressed(e -> {
        	if(midTxt.getText().equals(mid)) {
        		midTxt.clear();
        	}
        });
        
        sidTxt.setOnMousePressed(e -> {
        	if(sidTxt.getText().equals(sid)) {
        		sidTxt.clear();
        	}
        });
        
        asmtTxt.setOnMousePressed(e -> {
        	if(asmtTxt.getText().equals(asmt)) {
        		asmtTxt.clear();
        	}
        });
        
        finTxt.setOnMousePressed(e -> {
        	if(finTxt.getText().equals(fin)) {
        		finTxt.clear();
        	}
        });
        
        btnAdd.setOnAction(e -> {
        	String b_sid = sidTxt.getText();
        	Float b_asmt = new Float(asmtTxt.getText());
        	Float b_mid = new Float(midTxt.getText());
        	Float b_fin = new Float(finTxt.getText());
        	
        	StudentRecord newData = new StudentRecord(b_sid, b_mid,b_asmt, b_fin);
        	table.getItems().add(newData);
    	});
        
        root.add(menubar,0,0);
        
        Scene scene = new Scene(root,600,500);
        stage.setTitle("Lab 05 Solution");
 
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
	String save(TableView<StudentRecord> table) {
		TableColumn<StudentRecord,?> IDcol = table.getColumns().get(0) ;
		TableColumn<StudentRecord,?> midcol = table.getColumns().get(1) ;
   	    TableColumn<StudentRecord,?> asmtcol = table.getColumns().get(2) ;
   	    TableColumn<StudentRecord,?> fcol = table.getColumns().get(3) ;

   	    String csv = "ID,Midterm,Assignment,Final\n";
   	    for(StudentRecord r: table.getItems()) {
		    String i = (String) IDcol.getCellObservableValue(r).getValue();
		    String a = Float.toString((Float) asmtcol.getCellObservableValue(r).getValue());
		    String m = Float.toString((Float) midcol.getCellObservableValue(r).getValue());
		    String f = Float.toString((Float) fcol.getCellObservableValue(r).getValue());
		    csv += i + "," + a + "," + m + "," + f + "\n";
   	 }
   	    return csv;
	}


}
