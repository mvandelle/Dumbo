
package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import Mechanic.LogWriter;
import Mechanic.MemoryClone;
import Mechanic.OrdreClient;
import Mechanic.OrdreStack;
import Mechanic.PDFGenActif;
import Mechanic.PDFGenClient;
import Mechanic.SyncDifManager;
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
	private SyncDifManager syncMan;
	
	
	
	public StackVisu(String id, MemoryClone data)
	{
		this.id = id;
		this.data = data;
		syncMan = new SyncDifManager();
	}
	
	
	public Stage showStackVisu() throws MalformedURLException
	{
		
		// PRESENTATION ******************************
		
		Stage stageStackVisuWindow = new Stage();
		stageStackVisuWindow.setTitle("Ordres en attente " + id );
    	Group rootStackVisuWindow= new Group();
        Scene sceneStackVisuWindow = new Scene(rootStackVisuWindow,Dumbo.WIDTH_PC,Dumbo.HEIGHT_PC, Color.WHITE);
        stageStackVisuWindow.setScene(sceneStackVisuWindow);
        
        File ath = new File("Dumbo's brain/athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(Dumbo.A_X);
        path.setLayoutY(Dumbo.A_Y);
        path.setScaleX(Dumbo.SCALE);
        path.setScaleY(Dumbo.SCALE);
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
					
					PopupControl p = new PopupControl("Ordre supprimé", true, stageStackVisuWindow);
			        p.show();
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
				syncMan.update(id, interm.size());
				
				LogWriter l = new LogWriter();
				try {
					l.writeLogOnfile(interm, id);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				PopupControl p = new PopupControl("Ordre validé", true, stageStackVisuWindow);
		        p.show();
				
				
			}
        });
        
        Button clientPDF = new Button();
        clientPDF.setText("Génerer PDF ordres par client");
        rootStackVisuWindow.getChildren().add(clientPDF);
        clientPDF.setLayoutX(1000);
        clientPDF.setLayoutY(300);
        
        Button actifPDF = new Button();
        actifPDF.setText("Génerer PDF ordres par titre");
        rootStackVisuWindow.getChildren().add(actifPDF);
        actifPDF.setLayoutX(1000);
        actifPDF.setLayoutY(400);
        
        
        Text error = new Text (200, 450, "  Des ordres pour des clients\n differents ont été selectionnés");
		error.setFill(Color.RED);
		error.setX(1000);
		error.setY(350);
		
		Text error2 = new Text (200, 450, "  Des ordres differents ou des\n depositaires differents sont selectionnés");
		error2.setFill(Color.RED);
		error2.setX(1000);
		error2.setY(350);
		
		Text error3 = new Text(200, 450, "Aucun ordre selectionné");
		error3.setFill(Color.RED);
		error3.setX(1000);
		error3.setY(350);
		
        clientPDF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<Integer> indices = listOrdres.getSelectionModel().getSelectedIndices();
			
				
				if ( indices.isEmpty())
				{
					
					PopupControl p = new PopupControl("Aucun ordre selectionné", false, stageStackVisuWindow);
			        p.show();
				} else
				{
					Boolean sameClient = data.getStack().checkSameClient(indices);
				
					if ( sameClient == false)
					{
						PopupControl p = new PopupControl("Des ordres pour des clients\ndifferents ont été selectionnés", false, stageStackVisuWindow);
				        p.show();
					} else
					{
					
						indices = data.getStack().addForgottenOrdreClient(indices);
						PDFGenClient pdf = new PDFGenClient(data.getStack().createOrdreForPdf(indices));
						try {
							pdf.GenPdf();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
					
				
				
			}
        });
        
        actifPDF.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ObservableList<Integer> indices = listOrdres.getSelectionModel().getSelectedIndices();
				if ( indices.isEmpty())
				{
					PopupControl p = new PopupControl("Aucun ordre selectionné", false, stageStackVisuWindow);
			        p.show();
					
				} else
				{
					Boolean sameOrdresAndSameDepo = data.getStack().checkSameOrdreAndSameDepo(indices);
				
					if ( sameOrdresAndSameDepo == false)
					{
						PopupControl p = new PopupControl("Des dépositaires ou des titres\ndifférents ont été sélectionnés", false, stageStackVisuWindow);
				        p.show();
					} else
					{
					
						
						indices = data.getStack().addForgottenOrdreActif(indices);
						
						PDFGenActif pdf = new PDFGenActif(data.getStack().createOrdreForPdfparOrdre(indices));
						try {
							pdf.GenPdf();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					
					}
				}
					
				
				
			}
        });
        
        
        
        
		return stageStackVisuWindow;
	}

}
