package application;
import java.io.File;
import java.net.MalformedURLException;

import Mechanic.Client;
import Mechanic.MemoryClone;
import Mechanic.OrdreStack;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Loby {
	private String id;
	private MemoryClone data;
	
	
	public Loby(String id)
	{
		this.id = id;
		this.data = new MemoryClone(this.id);
		if ( data.getClient().isEmpty())
		{
			data.cloneFile();
		}
		this.data.getStack().setClient(this.data.getClient());
		this.data.getStack().loadOldOrdre();
		
		if ( data.getStack().isEmpty() && data.isSync())
			{
				this.data.initialize();
				this.data.cloneFile();
			}else
			{
				
			}
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Stage showLoby() throws MalformedURLException
	{
		//base de la fenetre
		Stage stageLoby = new Stage();
    	stageLoby.setTitle(id + " Loby");
    	Group rootLoby = new Group();
        Scene sceneLoby = new Scene(rootLoby, 500, 500, Color.WHITE);
        stageLoby.setScene(sceneLoby);
        
        
      //logo athenee
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        rootLoby.getChildren().add(path);
        
        Button actionClient = new Button();
        actionClient.setText("Par client");
        actionClient.setLayoutX(80);
        actionClient.setLayoutY(200);
        rootLoby.getChildren().add(actionClient);
        
        Button sync = new Button();
        sync.setText("Synchroniser");
        sync.setLayoutX(350);
        sync.setLayoutY(20);
        rootLoby.getChildren().add(sync);
        
       
        
        sync.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				data.sync();
				
			}
        	
        });
        
        actionClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ActParClient apc = new ActParClient(data, true);
				try {
					apc.showActParClient().show();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        	
        	
        
        });
        
        Button actionTitre = new Button();
        actionTitre.setText("Par titre");
        actionTitre.setLayoutX(330);
        actionTitre.setLayoutY(200);
        rootLoby.getChildren().add(actionTitre);
        actionTitre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				OrdreParTitre opc = new OrdreParTitre(data);
				try {
					opc.showOrdreParTitre().show();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        	
        	
        
        });
        
        Button seeStack = new Button();
        seeStack.setText("voir les ordres en attente");
        seeStack.setLayoutX(160);
        seeStack.setLayoutY(300);
        rootLoby.getChildren().add(seeStack);
        
        seeStack.setOnAction(new EventHandler<ActionEvent>() {

			@Override
		public void handle(ActionEvent event) {
			data.getStack().loadOldOrdre();
			StackVisu sv = new StackVisu(id, data);
			try {
					sv.showStackVisu().show();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        });
        
        
        
        
        
        return stageLoby;
	}

}
