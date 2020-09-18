package application;





import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.controlsfx.control.PrefixSelectionComboBox;

import Mechanic.Client;
import Mechanic.ClientNode;
import Mechanic.Depositaire;
import Mechanic.MemoryClone;
import Mechanic.MemoryReaderDepositaire;
import Mechanic.MemoryReaderTitre;
import Mechanic.OrdreClient;
import Mechanic.Sens;
import Mechanic.Titre;
import Mechanic.TreeClientNode;
import Mechanic.TypeTitre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.Window;

public class OrdreParTitre {
	private MemoryReaderTitre TitRead;
	private MemoryReaderDepositaire DepRead;
	private OrdreClient ordre;
	private MemoryClone clone;
	private int quant;
	private boolean isBuy;
	private TreeTableView<ClientNode> navig;
	
	public OrdreParTitre(MemoryClone m)
	{
		clone = m;
		clone.loadClient();
		TitRead = new MemoryReaderTitre();
		DepRead = new MemoryReaderDepositaire("Dumbo's brain/memoryDep.txt");
		TitRead.sortTitre();
		quant = 0;
		ordre = new OrdreClient();
		isBuy = true;
		navig = new TreeTableView<ClientNode>();
	}
	
	public Stage showOrdreParTitre(Stage st) throws MalformedURLException
	{
		Stage s = new Stage();
		s.setTitle("Passage d'ordre par Titre");
		Group root = new Group();
		Scene scene = new Scene(root, Dumbo.WIDTH_PC,Dumbo.HEIGHT_PC, Color.WHITE);
		s.setScene(scene);
		

        File ath = new File("Dumbo's brain/athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        root.getChildren().add(path);
        
        
        TextField quantTot = new TextField();
        quantTot.setPromptText("Quantité Total");
        quantTot.setLayoutX(800);
        quantTot.setLayoutY(50);
        quantTot.setScaleX(1.7);
        quantTot.setScaleY(1.7);
        root.getChildren().add(quantTot);
        
        TextField limite = new TextField();
        limite.setPromptText("Limite");
        limite.setLayoutX(800);
        limite.setLayoutY(450);
        root.getChildren().add(limite);
        
        TextField echeance = new TextField();
        echeance.setPromptText("Echeance");
        echeance.setLayoutX(800);
        echeance.setLayoutY(500);
        root.getChildren().add(echeance);
        
        ObservableList<String> optionsType = 
        	    FXCollections.observableArrayList(Arrays.asList("Au marché", "A cours limité", "A seuil de declenchment", "A plage de déclenchement", "STOP LOSS")
        	        );
        
        ComboBox<String> comboType = new ComboBox<String>(optionsType);
        comboType.setMaxWidth(150);
        comboType.setLayoutY(400);
        comboType.setLayoutX(800);
        comboType.setPromptText("Type");
        root.getChildren().add(comboType);
        
      
        
     
       
        
        
        
        Text resumeQuant = new Text("Quantité restante : " + quant);
        resumeQuant.setScaleX(2);
        resumeQuant.setScaleY(2);
        resumeQuant.setLayoutX(300);
        resumeQuant.setLayoutY(70);
        root.getChildren().add(resumeQuant);
        
        

        
        Button update = new Button();
        update.setText("Mise a jour \nde la quantité");
        update.setLayoutX(750);
        update.setLayoutY(100);
        root.getChildren().add(update);
        
        Button switche = new Button();
        switche.setText("MODE : BUY");
        switche.setLayoutX(875);
        switche.setLayoutY(100);
        root.getChildren().add(switche);
        
        Button add = new Button();
        add.setText("Ajouter un titre");
        add.setLayoutX(1000);
        add.setLayoutY(600);
        root.getChildren().add(add);
        
       
        
        
        ObservableList<String> optionsTitre = 
        	    FXCollections.observableArrayList(
        	         new ArrayList<String>(Arrays.asList(TypeTitre.ACTION.toString(),TypeTitre.OBLIGATION.toString(),TypeTitre.FUTURE.toString(),TypeTitre.OPTION.toString() ,TypeTitre.OPC.toString(),
        	        		 TypeTitre.FOREX.toString() ,TypeTitre.COMMODITIES.toString()))
        	        );
        
        ComboBox<String> comboTitres = new ComboBox<String>(optionsTitre);
		comboTitres.setMaxWidth(400);
		comboTitres.setLayoutY(150);
		comboTitres.setLayoutX(170);
		comboTitres.setPromptText("Type de titre");
		root.getChildren().add(comboTitres);
		
		PrefixSelectionComboBox<String> sortTitre = new PrefixSelectionComboBox<String>();
		sortTitre.setTypingDelay((int)Double.POSITIVE_INFINITY);
		sortTitre.setBackSpaceAllowed(true);
		sortTitre.setDisplayOnFocusedEnabled(true);
        sortTitre.setMaxWidth(400);
        sortTitre.setLayoutY(150);
        sortTitre.setLayoutX(320);
        root.getChildren().add(sortTitre);
        
        
        navig.setLayoutX(170);
        navig.setLayoutY(200);
        navig.setMinSize(500, 450);
        navig.setMaxSize(500, 450);
        root.getChildren().add(navig);
        
        TreeTableColumn<ClientNode, String> column1 = new TreeTableColumn<>("Client");
        column1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        column1.setMinWidth(300);
        
        TreeTableColumn<ClientNode, String> column2 = new TreeTableColumn<>("Quantité");
        column2.setCellValueFactory(new TreeItemPropertyValueFactory<>("quant"));
        column2.setMinWidth(100);
        
        TreeTableColumn<ClientNode, String> column3 = new TreeTableColumn<>("En cours");
        column3.setCellValueFactory(new TreeItemPropertyValueFactory<>("modif"));
        column3.setMinWidth(100);
        
        navig.getColumns().add(column1);
        navig.getColumns().add(column2);
        navig.getColumns().add(column3);
        
        Button buy = new Button();
        buy.setText("BUY");
        buy.setLayoutX(1000);
        buy.setLayoutY(350);
        root.getChildren().add(buy);
        
        Button sell = new Button();
        sell.setText("SELL");
        sell.setLayoutX(1050);
        sell.setLayoutY(350);
        root.getChildren().add(sell);
        
        Button passageOrdre = new Button();
        passageOrdre.setText("Passer l'ordre");
        passageOrdre.setLayoutX(1000);
        passageOrdre.setLayoutY(400);
        root.getChildren().add(passageOrdre);
        
        Button startOver = new Button();
        startOver.setText("Annuler et recommencer");
        startOver.setLayoutX(1000);
        startOver.setLayoutY(100);
        root.getChildren().add(startOver);
        
        TextField Qua = new TextField();
        Qua.setPromptText("quant.");
        Qua.setLayoutX(800);
        Qua.setLayoutY(350);
        root.getChildren().add(Qua);
        
        
        
        
        switche.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if ( ordre.isEmpty())
				{
					if ( isBuy)
					{
						isBuy = false;
						switche.setText("MODE : SELL");
					
					}else
					{
						isBuy = true;
						switche.setText("MODE : BUY");
					
					
					}
				} else
				{
					PopupControl p = new PopupControl("Ordre deja commencé", false, s);
					p.show();
				}
				
			}
        	
        	
        });
        
        add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				AjoutTitre addWindow = new AjoutTitre(TitRead);
				comboTitres.getSelectionModel().select(-1);
				try {
					addWindow.showAjoutTitre(s).show();;
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        	
        });
        
        startOver.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				quantTot.setText("");
				limite.setText("");
				echeance.setText("");
				comboType.getSelectionModel().select(-1);
				Qua.setText("");
				comboTitres.getSelectionModel().select(-1);
				sortTitre.getSelectionModel().select(-1);
				quant = 0;
				resumeQuant.setText("Quantité restante : " + quant);
				ordre.initializeParTitre();
				navig = new TreeTableView<ClientNode>();
				navig.setLayoutX(170);
				navig.setLayoutY(200);
				navig.setMinSize(500, 450);
				navig.setMaxSize(500, 450);
				root.getChildren().add(navig);
	        
				TreeTableColumn<ClientNode, String> column1 = new TreeTableColumn<>("Client");
				column1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
				column1.setMinWidth(300);
	        
				TreeTableColumn<ClientNode, String> column2 = new TreeTableColumn<>("Quantité");
				column2.setCellValueFactory(new TreeItemPropertyValueFactory<>("quant"));
				
				TreeTableColumn<ClientNode, String> column3 = new TreeTableColumn<>("En cours");
				column3.setCellValueFactory(new TreeItemPropertyValueFactory<>("modif"));
	        
				navig.getColumns().add(column1);
				navig.getColumns().add(column2);
				navig.getColumns().add(column3);
				PopupControl p = new PopupControl("Page reinitialisée", true, s);
				p.show();
				
			}
        	
        	
        });
        
        
        passageOrdre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if ( quant > 0)
				{
					PopupControl p = new PopupControl("Quantité restante non nulle", false, s);
			        p.show();
				} else
				{
				
					if ( ordre.isEmpty())
					{
						PopupControl p = new PopupControl("Ordre vide", false, s);
						p.show();
					} else
					{
						clone.passOrdre(ordre, false);
				
				      /*
						Qua.setText("");
						quantTot.setText("");
						comboTitres.getSelectionModel().select(-1);
						sortTitre.getSelectionModel().select(-1);
						quant = 0;
						resumeQuant.setText("Quantité restante : " + quant);
						ordre.initializeParTitre();
						navig = new TreeTableView<ClientNode>();
						navig.setLayoutX(170);
						navig.setLayoutY(200);
						navig.setMinSize(500, 450);
						navig.setMaxSize(500, 450);
						root.getChildren().add(navig);
		        
						TreeTableColumn<ClientNode, String> column1 = new TreeTableColumn<>("Client");
						column1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
						column1.setMinWidth(300);
		        
						TreeTableColumn<ClientNode, String> column2 = new TreeTableColumn<>("Quantité");
						column2.setCellValueFactory(new TreeItemPropertyValueFactory<>("quant"));
					
						TreeTableColumn<ClientNode, String> column3 = new TreeTableColumn<>("En cours");
						column3.setCellValueFactory(new TreeItemPropertyValueFactory<>("modif"));
		        
						navig.getColumns().add(column1);
						navig.getColumns().add(column2);
						navig.getColumns().add(column3);
						*/
						PopupControl p = new PopupControl("Ordre passé", true, st);
						p.show();
						s.close();
					}
			
				
				
				}
			}
        	
        	
        	
        });
        
        
        sell.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				if ( navig.getSelectionModel().getSelectedIndex() != -1 && !navig.getSelectionModel().getSelectedItem().getParent().getValue().getName().equals(" "))
				{
					if (Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
					{
						PopupControl p = new PopupControl("Quantité non valide", false, s);
				        p.show();
					} else
					{
						
						
						if ( isBuy)
						{
							PopupControl p = new PopupControl("Impossible de vendre en mode \n                      BUY", false, s);
					        p.show();
						}else
						{
							if( quant - Integer.parseInt(Qua.getCharacters().toString())>= 0 && findClientFromClientId(navig.getSelectionModel().getSelectedItem().getValue().getName()).hasEnough(findTitre(sortTitre.getSelectionModel().getSelectedIndex(),comboTitres.getSelectionModel().getSelectedItem()).getisin(), Integer.parseInt(Qua.getCharacters().toString())))
							{
								
								if ( comboType.getSelectionModel().getSelectedIndex() != -1)
								{ 	
									if ( !limite.getText().isEmpty())
									{
										if ( !echeance.getText().isEmpty())
										{
								
											navig.getSelectionModel().getSelectedItem().getValue().setModif(navig.getSelectionModel().getSelectedItem().getValue().getModif()-Integer.parseInt(Qua.getCharacters().toString()));
											navig.getColumns().remove(column3);
											navig.getColumns().add(column3);
											quant = quant - Integer.parseInt(Qua.getCharacters().toString());
											resumeQuant.setText("Quantité restante : " + quant);
											ordre.setTLE(comboType.getSelectionModel().getSelectedItem(), limite.getText(), echeance.getText());
											ordre.addClient(findClientFromClientId(navig.getSelectionModel().getSelectedItem().getValue().getName()), Sens.SELL, -Integer.parseInt(Qua.getCharacters().toString()));
											
										} else
										{
											PopupControl p = new PopupControl("Echance invalide", false, s);
									        p.show();
										}
									} else
									{
										PopupControl p = new PopupControl("Limite invalide", false, s);
								        p.show();
									}
								} else
								{
									PopupControl p = new PopupControl("Type invalide", false, s);
							        p.show();
								}
							} else
							{
								PopupControl p = new PopupControl("Quantité insuffisante", false, s);
						        p.show();
							}
						}
						
						
					}
					
				}
				
			}
        	
        });
        
        buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				;
				if ( navig.getSelectionModel().getSelectedIndex() != -1 && !navig.getSelectionModel().getSelectedItem().getParent().getValue().getName().equals(" "))
				{
					if (Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
					{
						PopupControl p = new PopupControl("Quantité non valide", false, s);
				        p.show();
					} else
					{
						
						if ( isBuy)
						{
							
							if ( quant - Integer.parseInt(Qua.getCharacters().toString()) >= 0)
							{
								
								
								if ( comboType.getSelectionModel().getSelectedIndex() != -1)
								{ 	
									if ( !limite.getText().isEmpty())
									{
										if ( !echeance.getText().isEmpty())
										{
								
											navig.getSelectionModel().getSelectedItem().getValue().setModif(navig.getSelectionModel().getSelectedItem().getValue().getModif()+Integer.parseInt(Qua.getCharacters().toString()));
											navig.getColumns().remove(column3);
											navig.getColumns().add(column3);
											quant = quant - Integer.parseInt(Qua.getCharacters().toString());
											resumeQuant.setText("Quantité restante : " + quant);
											ordre.setTLE(comboType.getSelectionModel().getSelectedItem(), limite.getText(), echeance.getText());
											ordre.addClient(findClientFromClientId(navig.getSelectionModel().getSelectedItem().getValue().getName()), Sens.BUY, Integer.parseInt(Qua.getCharacters().toString()));
											
										} else
										{
											PopupControl p = new PopupControl("Echance invalide", false, s);
									        p.show();
										}
									} else
									{
										PopupControl p = new PopupControl("Limite invalide", false, s);
								        p.show();
									}
								} else
								{
									PopupControl p = new PopupControl("Type invalide", false, s);
							        p.show();
								}
							} else
							{
								PopupControl p = new PopupControl("Quantité insuffisante", false, s);
						        p.show();
							}
						}else
						{
							PopupControl p = new PopupControl("Impossible d'acheter en mode \n                  SELL", false, s);
					        p.show();
						}
						
						
					}
					
				}
				
			}
        	
        });
        
        
        
        
        
        
        sortTitre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ( sortTitre.getSelectionModel().getSelectedIndex() != -1)
				{
					int index = sortTitre.getSelectionModel().getSelectedIndex();
					String isin = findTitre(index, comboTitres.getSelectionModel().getSelectedItem()).getisin();
					updateNavig(isin,clone,navig);
					ordre.Empty();
					ordre.setTitre(findTitre(index,comboTitres.getSelectionModel().getSelectedItem()));
					
				}
				
			}
        	
        });
        
       
        
        update.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				int newQuant = quant;
				
				try {
						newQuant = Integer.parseInt(quantTot.getText());
				} catch ( NumberFormatException e)
				{
					PopupControl p = new PopupControl("Quantité non valide", false, s);
			        p.show();
				}
				quant = quant + newQuant;
				resumeQuant.setText("Quantité restante : " + quant);
				
				
				
				
			}
        });
        
        comboTitres.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				ArrayList<String> sortTitreList = new ArrayList<>();
				if ( comboTitres.getSelectionModel().getSelectedIndex()!= -1)
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
        
       
        
        
        
		
		return s;
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
	
	public void updateNavig(String isin, MemoryClone clone, TreeTableView<ClientNode> navig)
	{
		
		
		TreeClientNode t = new TreeClientNode(clone);
		
		t.creatTree(isin);
 
        
        
        TreeItem<ClientNode> Tableroot = new TreeItem<>(new ClientNode(" ", 0));
        for ( int i = 0; i < t.getTree().size(); ++i)
        {
        	if ( !t.getTree().get(i).isEmpty())
        	{
        		TreeItem<ClientNode> item = new TreeItem<>(t.getTree().get(i).get(0));
        		for ( int j = 1; j < t.getTree().get(i).size(); ++j)
        			{
        		
        				item.getChildren().add(new TreeItem<ClientNode>(t.getTree().get(i).get(j)));
        			}
        		Tableroot.getChildren().add(item);
        	}
        }
        navig.setRoot(Tableroot);
        navig.setShowRoot(false);
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
	
	public Client findClientFromClientId(String id)
	{
	
		for ( int i = 0; i < clone.getClient().size(); ++i)
		{
			
			if ( id.equals(clone.getClient().get(i).getName()+" ("+clone.getClient().get(i).getnCompte()+")"))
			{
				return clone.getClient().get(i);
			} 
		}
		throw new IllegalArgumentException("Client was not found");
		
	}
	

	
	
	

}
