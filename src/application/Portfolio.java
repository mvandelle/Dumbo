package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;

import Mechanic.MemoryClone;
import Mechanic.MemoryReaderTitre;
import Mechanic.OrdreClient;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Portfolio {
	private String id;
	private ArrayList<String> clientID;
	private MemoryReaderTitre TitRead;
	private MemoryClone clone;
	
	
	public Portfolio(MemoryClone m)
	{
		this.id = m.getId();
		clone = m;
		clone.loadClient();
		clientID = new ArrayList<>();
		this.TitRead = new MemoryReaderTitre();
		TitRead.sortTitre();
		for ( int i = 0; i < clone.getClient().size(); ++i)
		{
			clientID.add(clone.getClient().get(i).getName()+" "+clone.getClient().get(i).getnCompte()+" "+clone.getClient().get(i).getDep());
		}
		Collections.sort(clientID);
		
	}
	
	public Stage showPortfolio() throws MalformedURLException
	{

		Stage stage= new Stage();
		stage.setTitle("Portefeuille CLient " + id );
    	Group rootstageActParClientWindow= new Group();
        Scene scenestageActParClientWindow = new Scene(rootstageActParClientWindow,2000,2000, Color.WHITE);
        stage.setScene(scenestageActParClientWindow);
        
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        rootstageActParClientWindow.getChildren().add(path);
        
        return stage;
		
	}
	

}
