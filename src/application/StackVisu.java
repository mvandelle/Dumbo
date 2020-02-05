package application;

import java.io.File;
import java.net.MalformedURLException;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class StackVisu {
	
	private String id;
	
	
	public StackVisu(String id)
	{
		this.id = id;
	}
	
	
	public Stage showStackVisu() throws MalformedURLException
	{
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
		
		
		return stageStackVisuWindow;
	}

}
