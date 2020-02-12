package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;

import Mechanic.OrdreStack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StackVisu {
	
	private String id;
	private OrdreStack stack;
	
	
	
	public StackVisu(String id, OrdreStack stack)
	{
		this.id = id;
		this.stack = stack;
		
	}
	
	
	public Stage showStackVisu() throws MalformedURLException
	{
		
		// PRESENTATION ******************************
		
		Stage stageStackVisuWindow = new Stage();
		stageStackVisuWindow.setTitle("Ordres en attente " + id );
    	Group rootStackVisuWindow= new Group();
        Scene sceneStackVisuWindow = new Scene(rootStackVisuWindow,2000,2000, Color.WHITE);
        stageStackVisuWindow.setScene(sceneStackVisuWindow);
        
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        rootStackVisuWindow.getChildren().add(path);
        
        //*********************************************
        
        ListView<String> listOrdres = new ListView<String>();
        listOrdres.setItems(FXCollections.observableArrayList(stack.showOrdre()));
        rootStackVisuWindow.getChildren().add(listOrdres);
        listOrdres.setLayoutX(300);
        listOrdres.setLayoutY(50);
        listOrdres.setMinSize(600, 600);
        
        Button remove = new Button();
        remove.setText("Supprimer ordre");
        rootStackVisuWindow.getChildren().add(remove);
        remove.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ( listOrdres.getSelectionModel().getSelectedIndex()>-1)
				{
					stack.removeOrdre(listOrdres.getSelectionModel().getSelectedIndex());
					listOrdres.setItems(FXCollections.observableArrayList(stack.showOrdre()));
				}
				
			}
        	
        });
        
		
		
		return stageStackVisuWindow;
	}

}
