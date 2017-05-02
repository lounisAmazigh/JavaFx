package pobj.pinboard.document;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class test extends Application {

	public static void main(String[] args)  {
		// TODO Auto-generated method stub
		launch(args);
	}
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button btn = new Button();
		btn.setText("Say hello");
		btn.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				System.out.println("hello");
			}
		});
		
		/*GridPane grid = new GridPane();
		grid.add(btn ,0,2);
		
		Scene scene = new Scene(grid , 200 , 300);
		
		primaryStage.setScene(scene);	*/
		primaryStage.setTitle("Salut");
		primaryStage.show();
	}

}
