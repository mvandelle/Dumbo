package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;

import Mechanic.MemoryReaderTitre;
import Mechanic.MemoryWriterTitre;
import Mechanic.Titre;
import Mechanic.TypeTitre;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AjoutTitre {
	
	private MemoryWriterTitre w;
	private MemoryReaderTitre r;
	
	
	public AjoutTitre(MemoryReaderTitre r)
	{
		w = new MemoryWriterTitre();
		this.r = r;
		
	}

	
	public Stage showAjoutTitre() throws MalformedURLException
	{
		Stage s = new Stage();
		s.setTitle("Ajout d'un titre");
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
		
		TextField name = new TextField();
		name.setPromptText("Nom du titre");
		name.setLayoutX(370);
		name.setLayoutY(150);
		root.getChildren().add(name);
		
		TextField ticker = new TextField();
		ticker.setPromptText("Ticker/ISIN");
		ticker.setLayoutX(570);
		ticker.setLayoutY(150);
		root.getChildren().add(ticker);
		
		TextField devise = new TextField();
		devise.setPromptText("Devise");
		devise.setLayoutX(770);
		devise.setLayoutY(150);
		root.getChildren().add(devise);
		
		Button add = new Button("Ajouter le titre");
		add.setLayoutX(770);
		add.setLayoutY(250);
		root.getChildren().add(add);
		
		Text error = new Text("Champs manquants ou invalides");
		error.setFill(Color.RED);
		error.setLayoutX(370);
		error.setLayoutY(250);
		
		
		add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				root.getChildren().remove(error);
				if ( name.getText().isEmpty() || ticker.getText().isEmpty() || devise.getText().isEmpty() || comboTitres.getSelectionModel().getSelectedIndex() == -1)
				{
					root.getChildren().add(error);
				} else
				{
					
					switch (comboTitres.getSelectionModel().getSelectedItem())
					{
						case "ACTION":
							Titre newTitreAct = new Titre(name.getText(), ticker.getText(), TypeTitre.ACTION, devise.getText(), 0);
							try {
								w.writeMemoryTitreAction(newTitreAct,r);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							break;
		        		
						case "OBLIGATION":
							Titre newTitreObl = new Titre(name.getText(), ticker.getText(), TypeTitre.OBLIGATION, devise.getText(), 0);
							try {
								w.writeMemoryTitreObligation(newTitreObl,r);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
		        		
						case "FUTURE":
							Titre newTitreFut = new Titre(name.getText(), ticker.getText(), TypeTitre.FUTURE, devise.getText(), 0);
							try {
								w.writeMemoryTitreFuture(newTitreFut,r);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
		        		
						case "OPTION":
							Titre newTitreOpt = new Titre(name.getText(), ticker.getText(), TypeTitre.OPTION, devise.getText(), 0);
							try {
								w.writeMemoryTitreOption(newTitreOpt,r);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
		        	
						case "OPC":
							Titre newTitreOPC = new Titre(name.getText(), ticker.getText(), TypeTitre.OPC, devise.getText(), 0);
							try {
								w.writeMemoryTitreOPC(newTitreOPC,r);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							break;
		        		
						case "FOREX":
							Titre newTitreFor = new Titre(name.getText(), ticker.getText(), TypeTitre.FOREX, devise.getText(), 0);
							break;
		        		
						case "COMMODITIES":
							Titre newTitreCommo = new Titre(name.getText(), ticker.getText(), TypeTitre.COMMODITIES, devise.getText(), 0);
							break;
		        	
						default:
							throw new IllegalArgumentException("Type selected was not found");
					}
				}
				
			}
			
			
			
		});
		
		
        
        return s;
	}
}
