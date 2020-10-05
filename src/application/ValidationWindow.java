package application;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import Mechanic.LogWriter;
import Mechanic.MemoryClone;
import Mechanic.OrdreClient;
import Mechanic.SyncDifManager;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ValidationWindow {
	private String id;
	private OrdreClient ordre;
	private MemoryClone data;
	private ListView<String> listOrdre;
	private SyncDifManager syncMan;
	private boolean priceIsSet;
	
	
	public ValidationWindow(String id, OrdreClient ordre, MemoryClone data,ListView<String> listOrdre)
	{
		this.id = id;
		this.ordre = ordre;
		this.data = data;
		this.listOrdre = listOrdre;
		syncMan = new SyncDifManager();
		priceIsSet = false;
	}
	
	public Stage showValidation(Stage st) throws MalformedURLException
	{
		
		Stage stage = new Stage();
		stage.setTitle("Ordres en attente " + id );
    	Group root= new Group();
        Scene scene = new Scene(root,500,500, Color.WHITE);
        stage.setScene(scene);
        
        File ath = new File("Dumbo's brain/athenee.png");
        String at = ath.toURI().toURL().toString();
        ImageView path = new ImageView();
        path.setImage(new Image(at));
        path.setLayoutX(Dumbo.A_X);
        path.setLayoutY(Dumbo.A_Y);
        path.setScaleX(Dumbo.SCALE);
        path.setScaleY(Dumbo.SCALE);
        root.getChildren().add(path);
        
        Button v = new Button();
        v.setText("Valider");
        v.setLayoutX(150);
        v.setLayoutY(350);
        root.getChildren().add(v);
        
        Button annuler = new Button();
        annuler.setText("Annuler");
        annuler.setLayoutX(300);
        annuler.setLayoutY(350);
        root.getChildren().add(annuler);
        
        Text ordresValid = new Text();
        ordresValid.setLayoutX(100);
        ordresValid.setLayoutY(150);
        root.getChildren().add(ordresValid);
        
        TextField priceSet = new TextField();
    	priceSet.setPromptText("prix d'entré");
    	priceSet.setLayoutX(175);
    	priceSet.setLayoutY(250);
        
        v.setOnAction(new EventHandler<ActionEvent>()
        		{

					@Override
					public void handle(ActionEvent event) {
						if ( priceIsSet)
						{
							PopupControl p = new PopupControl("Odre validé", true, st);
							p.show();
							stage.close();
							data.getStack().removeOrdre( listOrdre.getSelectionModel().getSelectedIndices());
							listOrdre.setItems(FXCollections.observableArrayList(data.getStack().showOrdre()));
							data.getStack().writeOnStack();
							data.ValidOrdre(ordre);
							syncMan.update(id, 1);

							LogWriter l = new LogWriter();
							try {
								l.writeLogOnfile(ordre, id);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else
						{
							try {
							
							double pr = Double.parseDouble(priceSet.getText());
							ordre.setPrice(pr);
							PopupControl p = new PopupControl("Odre validé", true, st);
							p.show();
							stage.close();
							data.getStack().removeOrdre( listOrdre.getSelectionModel().getSelectedIndices());
							listOrdre.setItems(FXCollections.observableArrayList(data.getStack().showOrdre()));
							data.getStack().writeOnStack();
							data.ValidOrdre(ordre);
							syncMan.update(id, 1);
							
							} catch (NumberFormatException e)
							{
								PopupControl p = new PopupControl("Prix non valide", false, stage);
								p.show();
							}
						
							
							
						}
						
					}
        	
        		});
        
        annuler.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.close();
				
			}
        	
        	
        	
        });
        
       
        
        if ( ordre.getPrice().get(0) == Math.PI)
        {
        	
        
        	root.getChildren().add(priceSet);
        	
        	ordresValid.setText("Sens : "+ordre.getSens().get(0)+"\nQuantité : "+ordre.getQuant().get(0)+"\nClient : "+ordre.getClientName()+"\nTitre : "+ordre.getTitre().get(0).showTitre());
        	
        	
		
			
		
	        
			
        	
        	
        } else
        {
        	priceIsSet = true;
        	
        	ordresValid.setText("Sens : "+ordre.getSens().get(0)+"\nQuantité : "+ordre.getQuant().get(0)+"\nClient : "+ordre.getClientName()+"\nTitre : "+ordre.getTitre().get(0).showTitre()+"\nPrix : "+ordre.getPrice().get(0));
        }
        
        
       
        return stage;
	}
	
	
	
	
	
	

}
