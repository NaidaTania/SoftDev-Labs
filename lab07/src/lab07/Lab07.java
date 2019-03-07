package lab07;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class Lab07 extends Application{
	
	public void start(Stage primaryStage) throws Exception{
		
		Color[] pieColours = {Color.AQUA, Color.GOLD, Color.DARKORANGE,Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
		
		//Open file
		String filename = "weatherwarnings-2015.csv";
		File f = new File(filename);
		/*
		 * Check if file exists
		if(f.exists()) System.out.println("File exists!");
		else System.out.println("Nope can't find it :(");*/
		
		//Parse csv to only count weather types
		Scanner input = new Scanner(f);
		Map<String,Integer> m = new HashMap<String,Integer>();
		String line;
		double total = 0;
		while(input.hasNextLine()) {
			line = input.nextLine();
			String[] words = line.split(",");
			//System.out.println("count" + (total+1) + "i got: " + words[5]);
			if(!m.containsKey(words[5])) {
				m.put(words[5], 1);
			}
			else m.put(words[5], m.get(words[5])+1);
			total++;
		}
		
		 GridPane grid = new GridPane();
		 //Set the legend
		 Rectangle r1 = new Rectangle(20,20);
		 r1.setFill(pieColours[0]);
		 r1.setStroke(Color.BLACK);
		 Rectangle r2 = new Rectangle(20,20);
		 r2.setFill(pieColours[1]);
		 r2.setStroke(Color.BLACK);
		 Rectangle r3 = new Rectangle(20,20);
		 r3.setFill(pieColours[2]);
		 r3.setStroke(Color.BLACK);
		 Rectangle r4 = new Rectangle(20,20);
		 r4.setFill(pieColours[3]);
		 r4.setStroke(Color.BLACK);
		 
		 //Add legend to pane
		 grid.add(r1, 0, 1);
		 grid.add(new Label("FLASH FLOOD"), 1, 1);
		 grid.add(r2, 0, 2);
		 grid.add(new Label("SEVERE THUNDERSTORM"), 1, 2);
		 grid.add(r3, 0, 3);
		 grid.add(new Label("SPECIAL MARINE"), 1, 3);
		 grid.add(r4, 0, 4);
		 grid.add(new Label("TORNADO"), 1, 4);
		 grid.setAlignment(Pos.CENTER);
		 grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		 grid.setVgap(5.5);
		 grid.setHgap(5.5);
		 
		 //Set proportion of pie
		 int RADIUS = 80;
		 double CIRC = (2*Math.PI*RADIUS);
		 double l_Flood = (m.get("FLASH FLOOD")/total)*CIRC;
		 double l_Thun = (m.get("SEVERE THUNDERSTORM")/total)*CIRC;
		 double l_Mar = (m.get("SPECIAL MARINE")/total)*CIRC;
		 double l_Torn = (m.get("TORNADO")/total)*CIRC;
		 
		 //Add each pie
		 Pane pane = new Pane();
		 
		 Arc arc1 = new Arc(150, 100, RADIUS, RADIUS, 0, l_Flood ); // Create an arc
		 arc1.setFill(pieColours[0]); // Set fill color
		 arc1.setType(ArcType.ROUND); // Set arc type
		 arc1.setStroke(Color.BLACK);
		 pane.getChildren().add(arc1);

		 Arc arc2 = new Arc(150, 100, RADIUS, RADIUS, (m.get("FLASH FLOOD")/total)*360, l_Thun); // Create an arc
		 arc2.setFill(pieColours[1]); // Set fill color
		 arc2.setType(ArcType.ROUND); // Set arc type
		 arc2.setStroke(Color.BLACK);
		 pane.getChildren().add(arc2);
		 
		 Arc arc3 = new Arc(150, 100, RADIUS, RADIUS, (m.get("FLASH FLOOD")/total)*360 + ((m.get("SEVERE THUNDERSTORM")/total) * 360), l_Mar); // Create an arc
		 arc3.setFill(pieColours[2]); // Set fill color
		 arc3.setType(ArcType.ROUND); // Set arc type
		 arc3.setStroke(Color.BLACK);
		 pane.getChildren().add(arc3);
		 
		 Arc arc4 = new Arc(150, 100, RADIUS, RADIUS,(m.get("FLASH FLOOD")/total)*360 + ((m.get("SEVERE THUNDERSTORM")/total) * 360) + (m.get("SPECIAL MARINE")/total)*360, l_Torn); // Create an arc
		 arc4.setFill(pieColours[3]); // Set fill color
		 arc4.setType(ArcType.ROUND); // Set arc type
		 arc4.setStroke(Color.BLACK);
		 pane.getChildren().add(arc4);
		 
		 //Add to stage
		 grid.add(pane, 2, 5);
		 Scene scene  = new Scene(grid,500,400);
		 primaryStage.setScene(scene);
		 primaryStage.show();
		 
		 //Close Scanner
		 input.close();
	}

	public static void main(String[] args){
		// TODO Auto-generated method stub
		launch(args);
		
	}

}
