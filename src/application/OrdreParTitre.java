package application;



import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OrdreParTitre {
	private MemoryReaderTitre TitRead;
	private MemoryReaderDepositaire DepRead;
	private OrdreClient ordre;
	private MemoryClone clone;
	private int quant;
	
	public OrdreParTitre(MemoryClone m)
	{
		clone = m;
		clone.loadClient();
		TitRead = new MemoryReaderTitre("memoryTitre.txt");
		DepRead = new MemoryReaderDepositaire("memoryDep.txt");
		TitRead.sortTitre();
		quant = 0;
		ordre = new OrdreClient();
	}
	
	public Stage showOrdreParTitre() throws MalformedURLException
	{
		Stage s = new Stage();
		s.setTitle("Ordre par titre");
		Group root = new Group();
		Scene scene = new Scene(root, 2000,2000, Color.WHITE);
		s.setScene(scene);
		

        File ath = new File("athenee.png");
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
        
        
        Text resumeQuant = new Text("Quantité restante : " + quant);
        resumeQuant.setScaleX(2);
        resumeQuant.setScaleY(2);
        resumeQuant.setLayoutX(300);
        resumeQuant.setLayoutY(70);
        root.getChildren().add(resumeQuant);
        
        Text errorQuant = new Text("La quantité selectionné est eronnée");
        errorQuant.setLayoutX(800);
        errorQuant.setLayoutY(100);

        
        Button update = new Button();
        update.setText("Mise a jour \nde la quantité");
        update.setLayoutX(1050);
        update.setLayoutY(40);
        root.getChildren().add(update);
        
        
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
		
		ComboBox<String> sortTitre = new ComboBox<String>();
        sortTitre.setMaxWidth(400);
        sortTitre.setLayoutY(150);
        sortTitre.setLayoutX(320);
        root.getChildren().add(sortTitre);
        
        TreeTableView<ClientNode> navig = new TreeTableView<ClientNode>();
        navig.setLayoutX(170);
        navig.setLayoutY(200);
        navig.setMinSize(400, 450);
        navig.setMaxSize(400, 450);
        root.getChildren().add(navig);
        
        TreeTableColumn<ClientNode, String> column1 = new TreeTableColumn<>("Client");
        column1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
        column1.setMinWidth(200);
        
        TreeTableColumn<ClientNode, String> column2 = new TreeTableColumn<>("Quantité");
        column2.setCellValueFactory(new TreeItemPropertyValueFactory<>("quant"));
        
        TreeTableColumn<ClientNode, String> column3 = new TreeTableColumn<>("En cours");
        column3.setCellValueFactory(new TreeItemPropertyValueFactory<>("modif"));
        
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
        
        TextField Qua = new TextField();
        Qua.setPromptText("quant.");
        Qua.setLayoutX(800);
        Qua.setLayoutY(350);
        root.getChildren().add(Qua);
        
        Text Error = new Text();
        Error.setText("");
        Error.setLayoutX(800);
        Error.setLayoutY(400);
        root.getChildren().add(Error);
        
        
        passageOrdre.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				clone.passOrdre(ordre, false);
				
			}
        	
        	
        	
        });
        
        
        sell.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Error.setText("");
				if ( navig.getSelectionModel().getSelectedIndex() != -1 && !navig.getSelectionModel().getSelectedItem().getParent().getValue().getName().equals(" "))
				{
					if (Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
					{
						Error.setText("Quantité invalide");
					} else
					{
						navig.getSelectionModel().getSelectedItem().getValue().setModif(navig.getSelectionModel().getSelectedItem().getValue().getModif()-Integer.parseInt(Qua.getCharacters().toString()));
						navig.getColumns().remove(column3);
						navig.getColumns().add(column3);
						quant = quant + Integer.parseInt(Qua.getCharacters().toString());
						resumeQuant.setText("Quantité restante : " + quant);
						ordre.addClient(findClientFromClientId(navig.getSelectionModel().getSelectedItem().getValue().getName()), Sens.SELL, Integer.parseInt(Qua.getCharacters().toString()));
						
					}
					
				}
				
			}
        	
        });
        
        buy.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Error.setText("");
				if ( navig.getSelectionModel().getSelectedIndex() != -1 && !navig.getSelectionModel().getSelectedItem().getParent().getValue().getName().equals(" "))
				{
					if (Qua.getCharacters().toString().isEmpty() || !isInt(Qua.getCharacters().toString()))
					{
						Error.setText("Quantité invalide");
					} else
					{
						navig.getSelectionModel().getSelectedItem().getValue().setModif(navig.getSelectionModel().getSelectedItem().getValue().getModif()+Integer.parseInt(Qua.getCharacters().toString()));
						navig.getColumns().remove(column3);
						navig.getColumns().add(column3);
						quant = quant - Integer.parseInt(Qua.getCharacters().toString());
						resumeQuant.setText("Quantité restante : " + quant);
						ordre.addClient(findClientFromClientId(navig.getSelectionModel().getSelectedItem().getValue().getName()), Sens.BUY, Integer.parseInt(Qua.getCharacters().toString()));
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
				root.getChildren().remove(errorQuant);
				int newQuant = quant;
				
				try {
						newQuant = Integer.parseInt(quantTot.getText());
				} catch ( NumberFormatException e)
				{
					
					root.getChildren().add(errorQuant);
				}
				quant = newQuant;
				resumeQuant.setText("Quantité restante : " + quant);
				
				
				
				
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
        	TreeItem<ClientNode> item = new TreeItem<>(t.getTree().get(i).get(0));
        	for ( int j = 1; j < t.getTree().get(i).size(); ++j)
        	{
        		
        		item.getChildren().add(new TreeItem<ClientNode>(t.getTree().get(i).get(j)));
        	}
        	Tableroot.getChildren().add(item);
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
