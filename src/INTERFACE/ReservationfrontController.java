/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;

import edu.esprit.entities.Reservation;
import edu.esprit.services.ReservationCrud;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class ReservationfrontController implements Initializable {

    @FXML
    private TableView<Reservation> reservationtable;
   
    @FXML
    private TextField textoutil;
    @FXML
    private DatePicker textdatedebut;
    @FXML
    private DatePicker textdatefin;
    @FXML
    private TextField textquantite;
    @FXML
    private TableColumn<Reservation, Integer> outil;
    
    @FXML
    private TableColumn<Reservation,Date> date2;
    @FXML
    private TableColumn<Reservation, String> quant;
    @FXML
    private TableColumn<Reservation,Date> date10;
    @FXML
    private TextField idreservation;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       showOutils();
         setCellValue();
     
        // TODO
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
     private void Addreservation(ActionEvent event) throws Exception { 
         if (textoutil.getText().isEmpty()||textdatedebut.getValue()== null||textquantite.getText().isEmpty()){
             Alert a= new Alert (Alert.AlertType.ERROR, "champ must not be empty",ButtonType.OK) ;
             a.showAndWait();
         }
         else { 
             
             //Date date_deb = textdatedebut.getValue();
             //Date currentDate = LocalDate.now();
             
//             if (date_deb.isBefore(currentDate)){
//                 Alert a = new Alert(Alert.AlertType.ERROR, "date cannot be lower than today's date", ButtonType.OK);
//                 a.showAndWait ();
//             }
                ReservationCrud ls= new ReservationCrud ();
       
            Reservation l =new Reservation();
          
           int out= Integer.parseInt(textoutil.getText()) ; 
           Date date1= Date.valueOf(textdatedebut.getValue());
           Date date2= Date.valueOf(textdatefin.getValue());
          l.setOutil_id(out);
          l.setDate_deb(date1);
          l.setDate_fin(date2);
          l.setQuantite(textquantite.getText());
         
          System.out.println("l"+l);
        
            try {
            ls.ajouter(l) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("reservation is added successfully!");
           // EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau client: \n" +"Son identifiant est:");
            alert.show(); 
            showOutils() ; 
         
        }
          catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
     }
     @FXML
    private void Supprimerreservation(ActionEvent event) {
            ReservationCrud  cs= new ReservationCrud ();
            Reservation  c =new Reservation (); 
            int out= Integer.parseInt(idreservation.getText()) ;  
            c.setId(out);
                       
            try 
            {
            cs.supprimer(out); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("reservation is deleted successfully!");
            alert.show();
         showOutils(); 
            
            } 
               catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


 }
     public void setCellValue(){
    
    reservationtable.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    { 
       
        Reservation p1=reservationtable.getItems().get(reservationtable.getSelectionModel().getSelectedIndex());
        System.out.println(p1);
        textoutil.setText(String.valueOf(p1.getOutil_id()));
        LocalDate date1=  p1.getDate_deb().toLocalDate();
        LocalDate date2=  p1.getDate_fin().toLocalDate();
        idreservation.setText(String.valueOf(p1.getId()));
        textdatedebut.setValue(date1);
        textdatefin.setValue(date2);
        textquantite.setText(String.valueOf(p1.getQuantite()));
//     //date_livraison.setValue(p1.getDateLivraison()); 
//           textid.setText(String.valueOf(p1.getId()));
//               textlabeloutils.setText(String.valueOf(p1.getLabel_outils()));
//                   textquantite.setText(String.valueOf(p1.getQuantite()));
//                       textimage.setText(String.valueOf(p1.getImage()));*/
                       
    }
});
}
   
    
}
