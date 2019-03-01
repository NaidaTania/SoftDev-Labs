package lab06;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import java.util.Arrays;
import javafx.scene.layout.Pane;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;

public class lab06 extends Application{
	
	public void start(Stage primaryStage) { 
		
		final int WIDTH = 800;
		final int HEIGHT = 600;
		
		HBox hBox = new HBox();
		
		double[] avgHousingPricesByYear = {
				247381.0,264171.4,287715.3,294736.1,
				308431.4,322635.9,340253.0,363153.7};
		double[] back_p = Arrays.copyOf(avgHousingPricesByYear,avgHousingPricesByYear.length);
		Arrays.sort(back_p);
		double maxPrice = back_p[0];
		
		double[] avgCommercialPricesByYear = {
				1121585.3,1219479.5,1246354.2,1295364.8,
				1335932.6,1472362.0,1583521.9,1613246.3};
		double[] comm_p = Arrays.copyOf(avgHousingPricesByYear,avgHousingPricesByYear.length);
		Arrays.sort(comm_p);
		double maxComm = comm_p[0];
		
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> chart = new BarChart<String,Number>(xAxis,yAxis);
		
		System.out.println(avgHousingPricesByYear[0]);
		
		XYChart.Series house = new XYChart.Series();
		XYChart.Series commerce = new XYChart.Series();
		house.setName("House prices/year");
		commerce.setName("Commerce prices/year");
		for(int i=0;i<avgHousingPricesByYear.length;i++) {
			house.getData().add(new XYChart.Data(Integer.toString(i),(avgHousingPricesByYear[i]/maxPrice)*HEIGHT));
			commerce.getData().add(new XYChart.Data(Integer.toString(i),(avgCommercialPricesByYear[i]/maxComm*HEIGHT)));
		}
		
		String[] ageGroups = {
				"18-25", "26-35", "36-45", "46-55", "56-65", "65+"};
				
		int[] purchasesByAgeGroup = {
				648, 1021, 2453, 3173, 1868, 2247};
		
		Color[] pieColours = {
				Color.AQUA, Color.GOLD, Color.DARKORANGE,
				Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM};
		
		final PieChart pie = new PieChart();
		for(int j=0;j<ageGroups.length;j++) {
			pie.getData().add(new PieChart.Data(ageGroups[j], purchasesByAgeGroup[j]));
		}
		
		hBox.getChildren().addAll(chart,pie);
		
		
		Scene scene  = new Scene(hBox,1000,600);
		scene.getStylesheets().add(this.getClass().getResource("lab06.css").toExternalForm());
        chart.getData().addAll(house,commerce);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
