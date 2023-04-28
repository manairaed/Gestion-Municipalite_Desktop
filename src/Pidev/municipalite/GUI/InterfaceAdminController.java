
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class InterfaceAdminController implements Initializable {

    @FXML
    private TableView<User> listUtilisateurs;
    
    private List<User> l ;
    
    ServiceUser us = new ServiceUser();
    @FXML
    private TableColumn<?, ?> emailCol;
    @FXML
    private TableColumn<?, ?> roleCol;
    @FXML
    private TableColumn<?, ?> nomCol;
    @FXML
    private TableColumn<?, ?> prenomCol;
    @FXML
    private TableColumn<?, ?> telCol;
    @FXML
    private TableColumn<?, ?> adresseCol;
    @FXML
    private Button btn_logout;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        l=us.getAll();
        
        ObservableList <User> list = FXCollections.observableArrayList();
      for (User user : l){
          list.add(user);
      }
        listUtilisateurs.setItems(list);
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        roleCol.setCellValueFactory(new PropertyValueFactory<>("roles"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nomUtil"));
          prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenomUtil"));
              telCol.setCellValueFactory(new PropertyValueFactory<>("tel"));
               adresseCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
              
          
    }    

    @FXML
    private void logout(ActionEvent event) {
        
        try {
        File sessionFile = new File("session.txt");
        if (sessionFile.exists()) {
            sessionFile.delete();
            System.out.println("Logged out successfully.");
              try{
                            Stage stage = (Stage) btn_logout.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
        } else {
            System.out.println("Session file does not exist.");
        }
    } catch (Exception ex) {
        System.out.println("Error logging out: " + ex.getMessage());
    }
    }

    @FXML
    private void GoToProfil(MouseEvent event) {
        try{
                            Stage stage = (Stage) btn_logout.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceProfil.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }
    
}
