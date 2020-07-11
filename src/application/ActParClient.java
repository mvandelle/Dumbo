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

import org.controlsfx.control.PrefixSelectionComboBox;

import Mechanic.Client;
import Mechanic.ComboBoxAutoComplete;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
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
	private boolean isParClient;
	
	
	
	public ActParClient(MemoryClone m, boolean mode)
	{
		this.id = m.getId();
	    isParClient = mode;
		ordre = new OrdreClient();
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
        buy.setLayoutX(800);
        buy.setLayoutY(500);
        rootstageActParClientWindow.getChildren().add(buy);
		
        
        Button sell = new Button();
        sell.setText("Vendre");
        sell.setLayoutX(900);
        sell.setLayoutY(500);
        rootstageActParClientWindow.getChildren().add(sell);
        
        Button passOrdre = new Button();
        passOrdre.setText("Passer l'ordre");
        passOrdre.setLayoutX(1000);
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
        comboClient.setLayoutX(100);
        comboClient.setPromptText("Nom   Compte   Banque");
        rootstageActParClientWindow.getChildren().add(comboClient);
        
        ComboBox<String> comboTitres = new ComboBox<String>(optionsTitre);
		comboTitres.setMaxWidth(400);
		comboTitres.setLayoutY(500);
		comboTitres.setLayoutX(100);
		comboTitres.setPromptText("Type de titre");
		rootstageActParClientWindow.getChildren().add(comboTitres);
        
		PrefixSelectionComboBox<String> sortTitre = new PrefixSelectionComboBox<String>();
		sortTitre.setTypingDelay((int)Double.POSITIVE_INFINITY);
		sortTitre.setBackSpaceAllowed(true);
		sortTitre.setDisplayOnFocusedEnabled(true);
        sortTitre.setMaxWidth(400);
        sortTitre.setLayoutY(500);
        sortTitre.setLayoutX(250);
        rootstageActParClientWindow.getChildren().add(sortTitre);
        
        
       
     
        
        
        
        
       
        
        //****************************************************************
        
        
        //**********TEXT**********
        
        Text resumeClient = new Text();
        
       
        
        ScrollPane scrollClient = new ScrollPane();
        scrollClient.setPrefSize(400, 250);
        scrollClient.setContent(resumeClient);
        scrollClient.setLayoutX(100);
        scrollClient.setLayoutY(200);
        rootstageActParClientWindow.getChildren().add(scrollClient);
        
        Text resumeTitre = new Text();
        resumeTitre.setLayoutX(100);
        resumeTitre.setLayoutY(550);
        rootstageActParClientWindow.getChildren().add(resumeTitre);
        
        Text resumeOrdre = new Text();
        
        ScrollPane scrollOrdre = new ScrollPane();
        scrollOrdre.setLayoutX(800);
        scrollOrdre.setLayoutY(150);
        scrollOrdre.setPrefSize(400, 300);
        resumeOrdre.setText("ordre : ");
        scrollOrdre.setContent(resumeOrdre);
        rootstageActParClientWindow.getChildren().add(scrollOrdre);
        
       
        
        TextField Qua = new TextField();
        Qua.setPromptText("quant.");
        Qua.setLayoutX(600);
        Qua.setLayoutY(600);
        rootstageActParClientWindow.getChildren().add(Qua);
       
        
        
        //************************************************
        
        
        //********** SET ON ACTION **********
        
       
        
        
        
        
        comboClient.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if ( isParClient)
				{
					ordre.Empty();
					Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
					resumeClient.setText(selectedClient.showCLient());
					ordre = new OrdreClient(selectedClient);
					resumeOrdre.setText("ordre : " +ordre.toString(isParClient));
				} else
				{
					Client selectedClient = findClientFromClientId(comboClient.getSelectionModel().getSelectedItem());
					resumeClient.setText(selectedClient.showCLient());
				}
				
				
			}
        
		});
        
        
       
        comboTitres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ArrayList<String> sortTitreList = new ArrayList<>();
				if (comboTitres.getSelectionModel().getSelectedIndex()!= -1)
				{
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
		        
		        
		        
		        
				
			}
        	
        });
        
        sortTitre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if ( isParClient)
				{
					if (sortTitre.getSelectionModel().getSelectedIndex()>-1 )
					{
						resumeTitre.setText(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()).resumeTitre());
					} else
					{
						resumeTitre.setText("");
					}
				} else
				{
					if (sortTitre.getSelectionModel().getSelectedIndex()>-1 )
					{
						
						Titre sTitre = findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem());
						resumeTitre.setText(sTitre.resumeTitre());
						ordre.setTitre(sTitre);
					} else
					{
						resumeTitre.setText("");
					}
				}
			}
        	
        	
        });
        
        
        
        buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				
					if ( comboClient.getSelectionModel().getSelectedIndex() == -1 || comboTitres.getSelectionModel().getSelectedIndex() == -1 || sortTitre.getSelectionModel().getSelectedIndex() == -1)
					{
						PopupControl p = new PopupControl("Information incomplète", false, stageActParClientWindow);
				        p.show();
					
					} else
					{
						if ( Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
						{
							PopupControl p = new PopupControl("Quantité invalide", false, stageActParClientWindow);
					        p.show();
						} else
						{	
							ordre.addTitre(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()), Sens.BUY,Integer.parseInt(Qua.getCharacters().toString()));
							resumeOrdre.setText("ordre : " +ordre.toString(isParClient));
						}
					}
				
				
			}
        	
        });
        
        sell.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
					
					if ( comboClient.getSelectionModel().getSelectedIndex() == -1 || comboTitres.getSelectionModel().getSelectedIndex() == -1 || sortTitre.getSelectionModel().getSelectedIndex() == -1)
					{
						PopupControl p = new PopupControl("Information incomplète", false, stageActParClientWindow);
				        p.show();
					
					} else
					{
						if ( findClientFromClientId(comboClient.getSelectionModel().getSelectedItem()).ownTitre(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()).getisin()))
						{
							if ( Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
							{
								PopupControl p = new PopupControl("Quantité invalide", false, stageActParClientWindow);
						        p.show();
							} else
							{
								if (findClientFromClientId(comboClient.getSelectionModel().getSelectedItem()).hasEnough(findTitre(sortTitre.getSelectionModel().getSelectedIndex(), comboTitres.getSelectionModel().getSelectedItem()).getisin(), Integer.parseInt(Qua.getText())) )
								{
									ordre.addTitre(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()), Sens.SELL,-Integer.parseInt(Qua.getCharacters().toString()));
									resumeOrdre.setText("ordre : " +ordre.toString(isParClient));
								}else
								{
									PopupControl p = new PopupControl("Quantité insuffisante", false, stageActParClientWindow);
							        p.show();
								}
							}
						} else
						{
							PopupControl p = new PopupControl("Le client ne possède pas ce titre", false, stageActParClientWindow);
					        p.show();
						}
					}
				
				
			}
        	
        });
        
        passOrdre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ( ordre.isEmpty())
				{
					PopupControl p = new PopupControl("Ordre vide", false, stageActParClientWindow);
			        p.show();
				} else
				{
					clone.passOrdre(ordre, isParClient);
					comboTitres.getSelectionModel().select(-1);
					sortTitre.getSelectionModel().select(-1);
					ordre.initializeParClient();
					
					PopupControl p = new PopupControl("Ordre passé", true, stageActParClientWindow);
			        p.show();
				}
				
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
	
	public boolean isInt(String a)
	{
		try {
			Integer.parseInt(a);
		} catch (NumberFormatException e){
			return false;
		}
		return true;
	}
	
	public boolean clientHasEnough(Client c, String isin, int q)
	{
		if ( c.gettitreISIN().get(isin) > q)
		{
			return true;
		} else
		{
			return false;
		}
	}
	

}
