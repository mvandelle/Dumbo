package application;


import Mechanic.MemoryReaderClient;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import Mechanic.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActParClient {
	private String id;
	private MemoryReaderClient memRead;
	private ArrayList<Client> client;
	private ArrayList<String> clientID;
	
	
	
	public ActParClient(String id)
	{
		this.id = id;
		this.memRead = new MemoryReaderClient("fileClient"+id);
		this.client = new ArrayList<>();
		client = memRead.readMemoryClient();
		clientID = new ArrayList<>();
		for ( int i = 0; i < client.size(); ++i)
		{
			clientID.add(client.get(i).getName()+" "+client.get(i).getnCompte()+" "+client.get(i).getDep());
		}
		Collections.sort(clientID);
		
	}
	
	public Stage showActParClient() throws MalformedURLException
	{
		Stage stageActParClientWindow = new Stage();
		stageActParClientWindow.setTitle("Action par client " + id );
    	Group rootstageActParClientWindow= new Group();
        Scene scenestageActParClientWindow = new Scene(rootstageActParClientWindow, 500, 500, Color.WHITE);
        stageActParClientWindow.setScene(scenestageActParClientWindow);
        
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        rootstageActParClientWindow.getChildren().add(path);
        
        
        ObservableList<String> options = 
        	    FXCollections.observableArrayList(
        	        clientID
        	        );
        ComboBox<String> comboClient = new ComboBox<String>(options);
        comboClient.setMaxWidth(400);
        comboClient.setLayoutY(150);
        comboClient.setLayoutX(150);
        comboClient.setPromptText("Nom   Compte   Banque");
        rootstageActParClientWindow.getChildren().add(comboClient);
        
        
        Text resumeClient = new Text();
        resumeClient.setLayoutX(100);
        resumeClient.setLayoutY(200);
        rootstageActParClientWindow.getChildren().add(resumeClient);
        
        
        comboClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
				resumeClient.setText(selectedClient.showCLient());
				
			}
        
		});
        
        
        return stageActParClientWindow;
	}
	
	public Client findClientFromClientId(String id)
	{
		for ( int i = 0; i < client.size(); ++i)
		{
			if ( id.equals(client.get(i).getName()+" "+client.get(i).getnCompte()+" "+client.get(i).getDep()))
			{
				return client.get(i);
			} 
		}
		throw new IllegalArgumentException("Client was not found");
		
	}

}
