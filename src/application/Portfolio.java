package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;

import Mechanic.Client;
import Mechanic.MemoryClone;
import Mechanic.MemoryReaderTitre;
import Mechanic.OrdreClient;
import Mechanic.UnityTitreVisual;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
		stage.setTitle("Portefeuille Client " + id );
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
        
        ObservableList<String> optionsClient = 
        	    FXCollections.observableArrayList(
        	        clientID
        	        );
        
        
        Text resumeClient = new Text();
        
       
        
        TableView<UnityTitreVisual> table = new TableView<UnityTitreVisual>();
        TableColumn<UnityTitreVisual, String> nameCol = new TableColumn<UnityTitreVisual, String>("Name");
        nameCol.setMinWidth(325);
        TableColumn<UnityTitreVisual, String> isinCol = new TableColumn<UnityTitreVisual, String>("ISIN");
        isinCol.setMinWidth(200);
        TableColumn<UnityTitreVisual, Integer> quantCol = new TableColumn<UnityTitreVisual, Integer>("Quantite");
        quantCol.setMinWidth(75);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        isinCol.setCellValueFactory(new PropertyValueFactory<>("isin"));
        quantCol.setCellValueFactory(new PropertyValueFactory<>("quant"));
        table.setLayoutX(500);
        table.setLayoutY(150);
        table.setMinWidth(600);
        table.getColumns().addAll(nameCol,isinCol,quantCol);
        rootstageActParClientWindow.getChildren().add(table);
        
        
        ComboBox<String> comboClient = new ComboBox<String>(optionsClient);
        comboClient.setMaxWidth(400);
        comboClient.setLayoutY(150);
        comboClient.setLayoutX(100);
        comboClient.setPromptText("Nom   Compte   Banque");
        rootstageActParClientWindow.getChildren().add(comboClient);
        
        comboClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				resumeClient.setText("");
				Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
				for ( String isin : selectedClient.gettitreISIN().keySet())
				{
					
					try {
							UnityTitreVisual u = new UnityTitreVisual(TitRead.findTitre(isin), selectedClient.gettitreISIN().get(isin));
							table.getItems().add(u);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				
				
			}
        
		});
        
        return stage;
		
	}
	
	public Client findClientFromClientId(String id)
	{
		for ( int i = 0; i < clone.getClient().size(); ++i)
		{
			if ( id.equals(clone.getClient().get(i).getName()+" "+clone.getClient().get(i).getnCompte()+" "+clone.getClient().get(i).getDep()))
			{
				return clone.getClient().get(i);
			} 
		}
		throw new IllegalArgumentException("Client was not found");
		
	}
	

}
