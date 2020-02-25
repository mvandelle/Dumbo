package application;


import Mechanic.MemoryReaderClient;
import Mechanic.MemoryReaderTitre;
import Mechanic.OrdreClient;
import Mechanic.Sens;
import Mechanic.Titre;
import Mechanic.TypeTitre;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import Mechanic.Client;
import Mechanic.MemoryClone;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActParClient {
	private String id;
	private ArrayList<String> clientID;
	private MemoryReaderTitre TitRead;
	private OrdreClient ordre;
	private MemoryClone clone;
	
	
	
	public ActParClient(MemoryClone m)
	{
		this.id = m.getId();
		ordre = new OrdreClient();
		clone = m;
		clone.loadClient();
		clientID = new ArrayList<>();
		this.TitRead = new MemoryReaderTitre("memoryTitre.txt");
		TitRead.sortTitre();
		for ( int i = 0; i < clone.getClient().size(); ++i)
		{
			clientID.add(clone.getClient().get(i).getName()+" "+clone.getClient().get(i).getnCompte()+" "+clone.getClient().get(i).getDep());
		}
		Collections.sort(clientID);
		
	}
	
	public Stage showActParClient() throws MalformedURLException
	{
		//**********PRESENTATION GENERAL**********
		
		
		Stage stageActParClientWindow = new Stage();
		stageActParClientWindow.setTitle("Action par client " + id );
    	Group rootstageActParClientWindow= new Group();
        Scene scenestageActParClientWindow = new Scene(rootstageActParClientWindow,2000,2000, Color.WHITE);
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
        
        
        //*******************************************************
        
        
        //**********BOUTON**********
        
        Button buy = new Button();
        buy.setText("Acheter");
        buy.setLayoutX(500);
        buy.setLayoutY(500);
        rootstageActParClientWindow.getChildren().add(buy);
		
        
        Button sell = new Button();
        sell.setText("Vendre");
        sell.setLayoutX(600);
        sell.setLayoutY(500);
        rootstageActParClientWindow.getChildren().add(sell);
        
        Button passOrdre = new Button();
        passOrdre.setText("Passer l'ordre");
        passOrdre.setLayoutX(700);
        passOrdre.setLayoutY(500);
        rootstageActParClientWindow.getChildren().add(passOrdre);
        
        //**********************************************************
        
        //**********COMBO**********
        
        
        ObservableList<String> optionsClient = 
        	    FXCollections.observableArrayList(
        	        clientID
        	        );
        
        ObservableList<String> optionsTitre = 
        	    FXCollections.observableArrayList(
        	         new ArrayList<String>(Arrays.asList(TypeTitre.ACTION.toString(),TypeTitre.OBLIGATION.toString(),TypeTitre.FUTURE.toString(),TypeTitre.OPTION.toString() ,TypeTitre.OPC.toString(),
        	        		 TypeTitre.FOREX.toString() ,TypeTitre.COMMODITIES.toString()))
        	        );
        
        ComboBox<String> comboClient = new ComboBox<String>(optionsClient);
        comboClient.setMaxWidth(400);
        comboClient.setLayoutY(150);
        comboClient.setLayoutX(150);
        comboClient.setPromptText("Nom   Compte   Banque");
        rootstageActParClientWindow.getChildren().add(comboClient);
        
        ComboBox<String> comboTitres = new ComboBox<String>(optionsTitre);
		comboTitres.setMaxWidth(400);
		comboTitres.setLayoutY(400);
		comboTitres.setLayoutX(150);
		comboTitres.setPromptText("Type de titre");
		rootstageActParClientWindow.getChildren().add(comboTitres);
        
		ComboBox<String> sortTitre = new ComboBox<String>();
        sortTitre.setMaxWidth(400);
        sortTitre.setLayoutY(400);
        sortTitre.setLayoutX(300);
        rootstageActParClientWindow.getChildren().add(sortTitre);
       
        
        //****************************************************************
        
        
        //**********TEXT**********
        
        
        Text resumeClient = new Text();
        resumeClient.setLayoutX(100);
        resumeClient.setLayoutY(200);
        rootstageActParClientWindow.getChildren().add(resumeClient);
        
        
        Text resumeTitre = new Text();
        resumeTitre.setLayoutX(600);
        resumeTitre.setLayoutY(200);
        rootstageActParClientWindow.getChildren().add(resumeTitre);
        
        Text resumeOrdre = new Text();
        resumeOrdre.setLayoutX(800);
        resumeOrdre.setLayoutY(200);
        resumeOrdre.setText("ordre : ");
        rootstageActParClientWindow.getChildren().add(resumeOrdre);
        
        Text ErrorBuy = new Text();
        ErrorBuy.setText("Information incomplete pour l'achat");
        ErrorBuy.setLayoutX(500);
        ErrorBuy.setLayoutY(600);
        
        
        
        //************************************************
        
        
        //********** SET ON ACTION **********
        
        
        
        
        comboClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
				Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
				resumeClient.setText(selectedClient.showCLient());
				ordre = new OrdreClient(selectedClient);
				resumeOrdre.setText("ordre : " +ordre.toString());
				
				
			}
        
		});
        
        
       
        comboTitres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ArrayList<String> sortTitreList = new ArrayList<>();
				
		        String typeSelected = comboTitres.getSelectionModel().getSelectedItem();
		        
		        switch ( typeSelected)
		        {
		        	case "ACTION":
		        		TitRead.getTitresAction().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        		
		        	case "OBLIGATION":
		        		TitRead.getTitresObligation().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        		
		        	case "FUTURE":
		        		TitRead.getTitresFuture().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        		
		        	case "OPTION":
		        		TitRead.getTitresOption().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        	
		        	case "OPC":
		        		TitRead.getTitresOPC().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        		
		        	case "FOREX":
		        		TitRead.getTitresForex().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        		
		        	case "COMMODITIES":
		        		TitRead.getTitresCommodities().forEach(x->sortTitreList.add(x.showTitre()));
		        		break;
		        	
		        	default:
		        		throw new IllegalArgumentException("Type selected was not found");
		        		
		        }
		        
		        sortTitre.setItems(FXCollections.observableArrayList(sortTitreList));
		        
		        
		        
		        
				
			}
        	
        });
        
        sortTitre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if (sortTitre.getSelectionModel().getSelectedIndex()>-1 )
				{
					resumeTitre.setText(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()).resumeTitre());
				} else
				{
					resumeTitre.setText("");
				}
			}
        	
        	
        });
        
        
        
        buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ( comboClient.getSelectionModel().getSelectedIndex() == -1 || comboTitres.getSelectionModel().getSelectedIndex() == -1 || sortTitre.getSelectionModel().getSelectedIndex() == -1)
				{
					if ( rootstageActParClientWindow.getChildren().contains(ErrorBuy))
					{
						
					}else
					{
						rootstageActParClientWindow.getChildren().add(ErrorBuy);
					}
				} else
				{
					if ( rootstageActParClientWindow.getChildren().contains(ErrorBuy))
					{
						rootstageActParClientWindow.getChildren().remove(ErrorBuy);
					}
					
					ordre.addTitre(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()), Sens.BUY);
					resumeOrdre.setText("ordre : " +ordre.toString());
				}
				
			}
        	
        });
        
        passOrdre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clone.passOrdre(ordre);
				
				
				
			}
        	
        	
        }
        		
        		
       );
        
        
        
        
        return stageActParClientWindow;
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
	
	public Titre findTitre(int index, String type)
	{
		if ( index == -1)
		{
			return null;
		} else
		{
			switch(type)
			{
				case "ACTION":
					return TitRead.getTitresAction().get(index);
			
				case "OBLIGATION":
					return TitRead.getTitresObligation().get(index);
				
				case "FUTURE":
					return TitRead.getTitresFuture().get(index);
				
				case "OPTION":
					return TitRead.getTitresOption().get(index);
				
				case "OPC":
					return TitRead.getTitresOPC().get(index);
				
				case "FOREX":
					return TitRead.getTitresForex().get(index);
				
				case "COMMODITIES":
					return TitRead.getTitresCommodities().get(index);
			
				default:
					throw new IllegalArgumentException("Type was not selected");
			}
		}
	}

}
