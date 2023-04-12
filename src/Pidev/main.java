
package Pidev;

import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class main extends Application {
    
    @Override
    public void start (Stage primaryStage){
        File sessionFile = new File("session.txt");
        if(sessionFile.exists()){
              try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("municipalite/GUI/InterfaceAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }else{
            try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("municipalite/GUI/InterfaceInscription.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root );
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
                    primaryStage.show();
        }catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        }
    }
    
    public static void main (String [] args){
        launch(args);
    }
}
