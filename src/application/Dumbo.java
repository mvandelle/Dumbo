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

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		//fenêtre de base
		primaryStage.setTitle("Dumbo 1.0");
		Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);
        
        //logo athenee
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        root.getChildren().add(path);
        
        //elephant
        File el = new File("gangsta.png");
        String ele = el.toURI().toURL().toString();
        ImageView path2 = new ImageView();
        path2.setImage(new Image(ele));
        path2.setLayoutX(-90);
        path2.setLayoutY(-300);
        path2.setScaleX(0.3);
        path2.setScaleY(0.3);
        root.getChildren().add(path2);
        
        
        // Choix gestionnaire
        ArrayList<String> gest = new ArrayList<>(Arrays.asList("JC","FA","Anonyme"));
        ObservableList<String> options = 
	        	    FXCollections.observableArrayList(
	        	        gest
	        	        );
	    ComboBox com = new ComboBox(options);
	    com.setMinWidth(200);
	    com.setLayoutY(340);
	    com.setLayoutX(150);
	    com.setPromptText("Gestionnaire");
	    root.getChildren().add(com);
        
  
	    Button but = new Button();
	    but.setLayoutX(175);
	    but.setLayoutY(370);
	    but.setText("Faire des brousoufs !");
	    but.setOnAction(new EventHandler<ActionEvent>() {

	            public void handle(ActionEvent event) {
	            	
	            	int index = com.getSelectionModel().getSelectedIndex(); 
	            	
	            	if ( index == -1)
	            	{
	            		Text error = new Text (200, 450, "Yo dude, you need \n to select your gangsta");
						error.setFill(Color.BLACK);
						error.setScaleX(2);
						error.setScaleY(2);
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
