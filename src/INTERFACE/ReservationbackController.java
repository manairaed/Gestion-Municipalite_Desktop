/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import edu.esprit.entities.Reservation;
import edu.esprit.services.ReservationCrud;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class ReservationbackController implements Initializable {

    @FXML
    private TableView<Reservation> reservationtable;
    @FXML
    private TableColumn<Reservation, Integer> outil;
    @FXML
    private TableColumn<Reservation,Date> date10;
    @FXML
    private TableColumn<Reservation,Date> date2;
    @FXML
    private TableColumn<Reservation, String> quant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        showOutils();
    }    
     
    public void showOutils()
    {   ReservationCrud  ls= new ReservationCrud();
   
     ObservableList<Reservation> list=ls.getAll();
   
 
   outil.setCellValueFactory(new PropertyValueFactory<Reservation,Integer>("outil_id"));
   quant.setCellValueFactory(new PropertyValueFactory<Reservation,String>("quantite"));
   date2.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("date_fin"));
   date10.setCellValueFactory(new PropertyValueFactory<Reservation,Date>("date_deb"));

   
   reservationtable.setItems(list);
     
        
    }

   @FXML
    private void Homeback(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
