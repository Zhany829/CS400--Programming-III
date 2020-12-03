package application;
////////////////FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
//Title: cs400 JavaFX practice
//Files:Main.java
//Author: ZHAN YU
//Email: zyu293@wisc.edu
//Course: SU20 CS 400 002
//Deadline: 7/10/2020
//Description: This file aims to create and edit a simple JavaFX application
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
//Students who get help from sources other than their partner and the course
//staff must fully acknowledge and credit those sources here. If you did not
//receive any help of any kind from outside sources, explicitly indicate NONE
//next to each of the labels below.
//
//Persons: NONE
//Online Sources: https://www.tutorialspoint.com/javafx/layout_borderpane.htm
//                https://stackoverflow.com/questions/32781362/centering-an-image-in-an-imageview
//
///////////////////////////////////////////////////////////////////////////////
import javafx.application.Application; 
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane; 
import javafx.stage.Stage; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
/***
 * Create and edit a simple JavaFX application
 * @author Zhan Yu
 *
 */
public class Main extends Application {

		@Override
		public void start(Stage primaryStage) throws Exception {
		
		    //Instantiating the BorderPane class  
		      BorderPane bPane = new BorderPane();   
		       
		      //Setting the top, bottom, center, right and left nodes to the pane 
		      //add a label in the top of the pane
		      bPane.setTop(new Label("CS400 My First JavaFX Program")); 
		      
		     //add a ComboBox in the left pane
		      ComboBox<String> myComboBox = new ComboBox<String>();
		      myComboBox.getItems().addAll("grade A","grade B","grade C","grade D","grade E");
		      myComboBox.setEditable(true);        
		      bPane.setLeft(myComboBox); 
		      
		      //add an ImageView of an Image in the center panel
		      Image image = new Image(("myface.png"));
		        ImageView imageView = new ImageView();
		        imageView.setImage(image);
		        imageView.setPreserveRatio(true);
		        imageView.setFitWidth(400);
		        imageView.setFitHeight(300);
		      bPane.setCenter(imageView); 
		    
		     //add a Button in the bottom panel with the label "Done"
		      bPane.setBottom(new Button("Done")); 
		     
		    //add a ComboBox in the right pane
		      ComboBox<String> myRightComboBox = new ComboBox<String>();
		      myRightComboBox.getItems().addAll("good drawing","looks cool","he is handsome");
		      myRightComboBox.setEditable(true);       
		      bPane.setRight(myRightComboBox); 
		      
		      //Creating a scene object 
		      Scene scene = new Scene(bPane);  
		      
		      //Setting title to the Stage
		      primaryStage.setTitle("BorderPane for p2"); 
		         
		      //Adding scene to the stage 
		      primaryStage.setScene(scene);          
		      
		      //Displaying the contents of the stage 
		      primaryStage.show(); 
		   } 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
