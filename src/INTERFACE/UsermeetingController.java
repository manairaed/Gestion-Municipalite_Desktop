/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.RendezVous;
import java.awt.Desktop;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import services.RendezVousCRUD;
import javafx.geometry.Insets;


/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class UsermeetingController implements Initializable {

    @FXML
    private AnchorPane anchore;
    @FXML
    private Button ajouter;
    @FXML
    private Pane content;
    @FXML
    private GridPane cards;
    @FXML
    private Button zoom;
   

    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Instancier le service de produit
            RendezVousCRUD cs = new RendezVousCRUD();
            
            
            List<RendezVous> rendez = cs.afficherrendezvous() ;
            
            
            
            
            
            // product list ------------------------------------------------
            int column = 0;
            int row = 1;
            try {
                for (int i = 0; i < rendez.size() ; i++) {
                    
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("rendezcard.fxml"));
                    HBox cardrendez = fxmlLoader.load();
                    RendezcardController Card = fxmlLoader.getController();
                    Card.setOffreData(rendez.get(i));
                    
                    if (column == 1) {
                        column = 0;
                        ++row;
                    }
                    cards.add(cardrendez, column++, row);
                    // GridPane.setMargin(oneProductCard, new Insets(10));
                    GridPane.setMargin(cardrendez, new Insets(0, 10, 25, 10));
                    cardrendez.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");
                    
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParseException ex) {
            Logger.getLogger(UsermeetingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void add(ActionEvent event) throws IOException {
        RendezVous.actionTest = 0;
        Parent fxml = FXMLLoader.load(getClass().getResource("Addrendez.fxml"));
        content.getChildren().removeAll();
        content.getChildren().setAll(fxml);
        
    }

    @FXML
    private void zoom(ActionEvent event) {
        
      
        RendezVousCRUD cs = new RendezVousCRUD();

        // Add event listener to the 'Schedule Meeting' button to open the Zoom web page for scheduling a new meeting
        String apiKey = "BFSfbVeUQSms7caXzOANpg";
        String apiSecret = "4cIRnX29CaSmZkA2vffV4xdzKD6uaKbF93gy";
            // Generate a new access token using the ZoomApi class from the RendezvousCrud service
            String accessToken = cs.generateAccessToken();

            // Construct the URL for scheduling a meeting with the Zoom web app
            String url = "https://zoom.us/meeting/schedule?apiKey=" + apiKey + "&apiSecret=" + apiSecret + "&token=" + accessToken;
        
            try {
                // Open the Zoom web page in the user's default web browser
                Desktop.getDesktop().browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                // Handle any errors that occur while trying to open the web page
                e.printStackTrace();
            }
        
    }
    
    // Other methods for your controller class...

        
//         String hostEmail = "host@example.com"; // Replace with the email of the host
//    String url = "https://zoom.us/schedule"; // URL to the Zoom scheduling page
//    
//    try {
//        // Build the URL with the email of the host
//        url += "?benfarhat.achref@esprit.tn=" + hostEmail;
//        // Open the URL in the default system browser
//        java.awt.Desktop.getDesktop().browse(java.net.URI.create(url));
//    } catch (IOException e) {
//        // Handle the exception
//        e.printStackTrace();
//    }
    

   
    


     
    

    
  

  
}

    


    
    

