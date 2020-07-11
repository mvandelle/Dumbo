package application;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class PopupControl extends Popup{
	private Popup popup;
	private Stage stage;

	public PopupControl ( String message, boolean mode, Stage s)
	{
		popup = new Popup();
		StackPane stackpane = new StackPane();
		stackpane.setStyle("-fx-background-color:white;-fx-border-color: grey;-fx-border-width:1;");
		stackpane.setMinSize(300,100);
		stage = s;
		Text t = new Text(message);
		if (mode)
		{
			t.setFill(Color.GREEN);
		} else
		{
			t.setFill(Color.RED);
		}
		stackpane.getChildren().add(t);
		popup.getContent().add(stackpane);
		popup.setOpacity(1);
		popup.setAutoHide(true);
		
		
	}
	
	public void show()
	{
		popup.show(stage);
	}
}
