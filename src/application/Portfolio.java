package application;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.controlsfx.control.PrefixSelectionComboBox;

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
import javafx.scene.input.KeyCode;
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
        Scene scenestageActParClientWindow = new Scene(rootstageActParClientWindow,Dumbo.WIDTH_PC,Dumbo.HEIGHT_PC, Color.WHITE);
        stage.setScene(scenestageActParClientWindow);
        
        File ath = new File("Dumbo's brain/athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(Dumbo.A_X);
        path.setLayoutY(Dumbo.A_Y);
        path.setScaleX(Dumbo.SCALE);
        path.setScaleY(Dumbo.SCALE);
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
        TableColumn<UnityTitreVisual, Integer> priceCol = new TableColumn<UnityTitreVisual, Integer>("Prix d'entré");
        priceCol.setMinWidth(75);
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        isinCol.setCellValueFactory(new PropertyValueFactory<>("isin"));
        quantCol.setCellValueFactory(new PropertyValueFactory<>("quant"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setLayoutX(500);
        table.setLayoutY(150);
        table.setMinWidth(600);
        table.getColumns().addAll(nameCol,isinCol,quantCol,priceCol);
        rootstageActParClientWindow.getChildren().add(table);
        
        
        PrefixSelectionComboBox<String> comboClient = new PrefixSelectionComboBox<String>();
        comboClient.setItems(FXCollections.observableArrayList(optionsClient));
        comboClient.setTypingDelay((int)Double.POSITIVE_INFINITY);
        comboClient.setBackSpaceAllowed(true);
        comboClient.setDisplayOnFocusedEnabled(true);
        comboClient.setMaxWidth(400);
        comboClient.setLayoutY(150);
        comboClient.setLayoutX(100);
        comboClient.setPromptText("Nom   Compte   Banque");
        rootstageActParClientWindow.getChildren().add(comboClient);
        
        
        Text research = new Text("Recherche : ");
        research.setLayoutX(100);
        research.setLayoutY(120);
        
    	rootstageActParClientWindow.getChildren().add(research);
       
        
       
        
        comboClient.setOnKeyPressed(e -> {
        	
        	ArrayList<String> alpha = new ArrayList<>();
        	alpha.addAll(Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"));
            	
           if ( alpha.contains(e.getCode().toString()))
           {
        	   research.setText(research.getText()+ e.getCode().toString());
           }
            
            
            if (e.getCode() == KeyCode.BACK_SPACE) {
            	
            	research.setText("Recherche : ");
                comboClient.getSelectionModel().select(-1);
            }
            
            
        });
        
        comboClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (comboClient.getSelectionModel().getSelectedIndex() != -1)
				{
					table.getItems().removeAll(table.getItems());
					resumeClient.setText("");
				
					Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
					for ( String isin : selectedClient.gettitreISIN().keySet())
					{
					
						try {
								UnityTitreVisual u = new UnityTitreVisual(TitRead.findTitre(isin), selectedClient.gettitreISIN().get(isin),selectedClient.getTitreISINP().get(isin));
								table.getItems().add(u);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
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
