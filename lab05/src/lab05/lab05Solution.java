package lab05;

import javafx.application.Application;
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
import java.lang.*;

public class lab05Solution extends Application{
	
	private TableView<StudentRecord> table;
	private ObservableList<StudentRecord> list;
	
	@Override
    public void start(Stage stage) {
		table = new TableView<>();
		
        //table.setEditable(true);
		
		list = DataSource.getAllMarks();
        System.out.println(list);
        table.setItems(list);
 
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
        root.add(table,0,0,5,4); //span over 5 column (rightwise) and 4 row (downwise)
        
        root.add(new Label("SID:"), 0, 5);
        String sid = "SID";
        TextField sidTxt = new TextField(sid);
        root.add(sidTxt, 1, 5);
        
        root.add(new Label("Assignments:"), 2, 5);
        String asmt = "Assignments/100";
        TextField asmtTxt = new TextField(asmt);
        root.add(asmtTxt, 3, 5);
        
        root.add(new Label("Midterm:"), 0, 6);
        String mid = "Midterm/100";
        TextField midTxt = new TextField(mid);
        root.add(midTxt, 1, 6);
        
        root.add(new Label("Final Exam:"), 2, 6);
        String fin = "Final Exam/100";
        TextField finTxt = new TextField(fin);
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
        	
        	
        	StudentRecord newData = new StudentRecord(b_sid, b_asmt, b_mid, b_fin);
        	table.getItems().add(newData);
    	});
        
        
        Scene scene = new Scene(root,600,500);
        stage.setTitle("Lab 05 Solution");
 
        stage.setScene(scene);
        stage.show();
    }
	
	public static void main(String[] args) {
		launch(args);
	}

}


class dataAdder extends DataSource{
	{
	for(StudentRecord rec: DataSource.getAllMarks()) {
		System.out.println(rec.getID());
		}
	}
}