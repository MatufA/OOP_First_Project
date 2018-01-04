package main.java.GUIPack;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class mainClass extends Application {
	@FXML private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) throws IOException {
		/*AnchorPane root = (AnchorPane) FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Our new app");
		this.primaryStage.setScene(scene);
		this.primaryStage.show();
		*/
		String sceneFile = "/GUIPack/Home.fxml";
	    Parent root = null;
	    URL    url  = null;
	    try
	    {
	        url  = getClass().getResource( sceneFile );
	        root = FXMLLoader.load( url );
	        System.out.println( "  fxmlResource = " + sceneFile );
	    }
	    catch ( Exception ex )
	    {
	        System.out.println( "Exception on FXMLLoader.load()" );
	        System.out.println( "  * url: " + url );
	        System.out.println( "  * " + ex );
	        System.out.println( "    ----------------------------------------\n" );
	        throw ex;
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
