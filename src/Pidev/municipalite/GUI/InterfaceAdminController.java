
package Pidev.municipalite.GUI;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    
}
