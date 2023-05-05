/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.Reservation;
import piidev.municipalite.services.EnvoyerMail;
import piidev.municipalite.services.ReservationCrud;
import piidev.municipalite.services.outilsCRUD;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class AddReservationController implements Initializable {

    @FXML
    private Text productName;

    @FXML
    private HBox cinInputErrorHbox;
    @FXML
    private Text cinInputError;
    @FXML
    private DatePicker dateduInput;
    @FXML
    private HBox dateduErrorInputHbox;
    @FXML
    private Text dateduErrorInput;
    @FXML
    private DatePicker dateauInput;
    @FXML
    private HBox dateauErrorInputHbox;
    @FXML
    private Text dateauInputError;
    @FXML
    private HBox matInputErrorHbox;
    @FXML
    private Text matInputError;
    @FXML
    private Button add_new_ContratBtn;
    @FXML
    private TextField textquantite;
    @FXML
    private Text outilsid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO 
                       outilsCRUD ls= new outilsCRUD();  

        System.out.println(ls.getoutilsReserve());
        outilsid.setText(ls.getoutilsReserve());
        
            
    }    

    @FXML
    private void addNewContrat(MouseEvent event)throws Exception {
        LocalDate currentDate = LocalDate.now();
               
                    LocalDate datedeb= Date.valueOf(dateduInput.getValue()).toLocalDate();
                         LocalDate datefin= Date.valueOf(dateauInput.getValue()).toLocalDate();
           System.out.println(currentDate);
               ReservationCrud ls= new ReservationCrud ();
       
            Reservation l =new Reservation();

             // int out= Integer.parseInt(cinInput.getText()) ; 
           Date date1= Date.valueOf(dateduInput.getValue());
           Date date2= Date.valueOf(dateauInput.getValue()); 
           
           l.setEtat("non confirmé ");
           l.setOutil_id(67) ; 
          l.setDate_deb(date1);
          l.setDate_fin(date2);
          l.setQuantite(textquantite.getText());
           System.out.println("l"+l);
        try {
            ls.ajouter(l) ;
              EnvoyerMail.sendMail("amineallah.mekni@esprit.tn","Vous avez un nouveau reservation /n Date Debut " +l.getDate_deb()+"date Fin" + l.getDate_fin());
          
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("reservation is added successfully!");}
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
