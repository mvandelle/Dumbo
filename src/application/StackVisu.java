package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import Mechanic.MemoryClone;
import Mechanic.OrdreClient;
import Mechanic.OrdreStack;
import Mechanic.PDFGenClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StackVisu {
	
	private String id;
	private MemoryClone data;
	
	
	
	public StackVisu(String id, MemoryClone data)
	{
		this.id = id;
		this.data = data;
		
	}
	
	
	public Stage showStackVisu() throws MalformedURLException
	{
		
		// PRESENTATION ******************************
		
		Stage stageStackVisuWindow = new Stage();
		stageStackVisuWindow.setTitle("Ordres en attente " + id );
    	Group rootStackVisuWindow= new Group();
        Scene sceneStackVisuWindow = new Scene(rootStackVisuWindow,2000,2000, Color.WHITE);
        stageStackVisuWindow.setScene(sceneStackVisuWindow);
        
        File ath = new File("athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(-30);
        path.setLayoutY(-40);
        path.setScaleX(0.7);
        path.setScaleY(0.7);
        rootStackVisuWindow.getChildren().add(path);
        
        //*********************************************
        
        ListView<String> listOrdres = new ListView<String>();
        listOrdres.setItems(FXCollections.observableArrayList(data.getStack().showOrdre()));
        rootStackVisuWindow.getChildren().add(listOrdres);
        listOrdres.setLayoutX(300);
        listOrdres.setLayoutY(50);
        listOrdres.setMinSize(600, 600);
        listOrdres.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        Button remove = new Button();
        remove.setText("Supprimer ordre");
        rootStackVisuWindow.getChildren().add(remove);
        remove.setLayoutX(100);
        remove.setLayoutY(300);
        remove.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if ( listOrdres.getSelectionModel().getSelectedIndex()>-1)
				{
					data.getStack().removeOrdre( listOrdres.getSelectionModel().getSelectedIndices());
					listOrdres.setItems(FXCollections.observableArrayList(data.getStack().showOrdre()));
					data.getStack().writeOnStack();
				}
				
			}
        	
        });
        
        Button valider = new Button();
        valider.setText("Valider ordre");
        rootStackVisuWindow.getChildren().add(valider);
        valider.setLayoutX(100);
        valider.setLayoutY(400);
        valider.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ArrayList<OrdreClient> interm = new ArrayList<OrdreClient>();
				
				interm = data.getStack().ordreToPass(listOrdres.getSelectionModel().getSelectedIndices());
				data.getStack().removeOrdre( listOrdres.getSelectionModel().getSelectedIndices());
				listOrdres.setItems(FXCollections.observableArrayList(data.getStack().showOrdre()));
				data.getStack().writeOnStack();
				data.ValidOrdre(interm);
				
				
			}
        });
        
        Button clientPDF = new Button();
        clientPDF.setText("Génerer PDF ordres par client");
        rootStackVisuWindow.getChildren().add(clientPDF);
        clientPDF.setLayoutX(1000);
        clientPDF.setLayoutY(300);
        Text error = new Text (200, 450, "  Des ordres pour des clients\n differents ont été selectionné");
		error.setFill(Color.RED);
		error.setX(1000);
		error.setY(350);
        clientPDF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ArrayList<OrdreClient> interm = new ArrayList<OrdreClient>();
				interm = data.getStack().ordreToPass(listOrdres.getSelectionModel().getSelectedIndices());
				String ncompte = interm.get(0).getClientNCompte();
				Boolean sameClient = true;
				for ( int i = 1; i < interm.size(); ++i)
				{
					if ( !interm.get(i).getClientNCompte().equals(ncompte))
					{
						sameClient = false;
					}
				}
				
				if ( sameClient == false)
				{
					rootStackVisuWindow.getChildren().add(error);
				} else
				{
					rootStackVisuWindow.getChildren().remove(error);
					PDFGenClient pdf = new PDFGenClient(interm);
					try {
						pdf.GenPdf();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
					
				
				
			}
        });
        
        
		return stageStackVisuWindow;
	}

}
