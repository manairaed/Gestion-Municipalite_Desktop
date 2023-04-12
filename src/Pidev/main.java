
package Pidev;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {
    
    @Override
    public void start (Stage primaryStage){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("municipalite/GUI/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    public static void main (String [] args){
        launch(args);
    }
}
