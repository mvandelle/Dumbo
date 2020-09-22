package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Dumbo extends Application{
	public static int WIDTH_PC = 1500;
	public static int HEIGHT_PC = 1000;
	public static int A_X = -90;
	public static int A_Y = -90;
	public static double SCALE= 0.5;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//fenêtre de base
		primaryStage.setTitle("Dumbo 1.0");
		Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        
        //logo athenee
        File ath = new File("Dumbo's brain/athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(A_X);
        path.setLayoutY(A_Y);
        path.setScaleX(SCALE);
        path.setScaleY(SCALE);
        root.getChildren().add(path);
        
        
        
        
        
        // Choix gestionnaire
        ArrayList<String> gest = new ArrayList<>(Arrays.asList("JC","FA","Anonyme"));
        ObservableList<String> options = 
	        	    FXCollections.observableArrayList(
	        	        gest
	        	        );
	    ComboBox com = new ComboBox(options);
	    com.setMinWidth(200);
	    com.setLayoutY(200);
	    com.setLayoutX(150);
	    com.setPromptText("Gestionnaire");
	    root.getChildren().add(com);
        
  
	    Button but = new Button();
	    but.setLayoutX(175);
	    but.setLayoutY(250);
	    but.setText("Faire des brousoufs !");
	    but.setOnAction(new EventHandler<ActionEvent>() {

	            public void handle(ActionEvent event) {
	            	
	            	int index = com.getSelectionModel().getSelectedIndex(); 
	            	
	            	if ( index == -1)
	            	{
	            		Text error = new Text (130, 450, "Veuillez selectionner un gestionnaire");
						error.setFill(Color.RED);
						error.setScaleX(1);
						error.setScaleY(1);
						root.getChildren().add(error);
	            	} else
	            	{
	            		String user = (String) com.getSelectionModel().getSelectedItem();
	            		Loby lob = new Loby(user);
	            		primaryStage.close();
	            		try {
							lob.showLoby().show();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
	            	}
        
        
	            }
	        });
	    root.getChildren().add(but);
	        
	     
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}
	
	public static void main(String[] args)
	{
		launch(args);
	}

}
