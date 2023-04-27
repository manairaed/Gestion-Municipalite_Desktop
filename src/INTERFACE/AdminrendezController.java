/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.RendezVous;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.RendezVousCRUD;
import com.itextpdf.text.*;
import services.RendezVousCRUD.PDFGenerator;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AdminrendezController implements Initializable {

    @FXML
    private AnchorPane content;
    @FXML
    private AnchorPane anchore;
    @FXML
    private Button ajouter;
    @FXML
    private GridPane cards;
    @FXML
    private Button pdf;

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
                    fxmlLoader.setLocation(getClass().getResource("admincardrendez.fxml"));
                    HBox cardrendez = fxmlLoader.load();
                    AdmincardrendezController Card = fxmlLoader.getController();
                    Card.setOffreData(rendez.get(i));
                    
                    if (column == 1) {
                        column = 0;
                        ++row;
                    }
                    cards.add(cardrendez, column++, row);
                    // GridPane.setMargin(oneProductCard, new Insets(10));
                    GridPane.setMargin(cardrendez, new javafx.geometry.Insets(0, 10, 25, 10));
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
private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, ParseException {
    // Instanciate the RendezVousCRUD service
    RendezVousCRUD cs = new RendezVousCRUD();
    
    // Retrieve the list of rendez-vous from the database
    List<RendezVous> rendezVousList = cs.afficherrendezvous();
    
    
    // Generate a PDF document containing the rendez-vous data
     RendezVousCRUD.PDFGenerator pdfGenerator = cs.new PDFGenerator();
    pdfGenerator.generateRendezVousPDF(rendezVousList, "rendezvous.pdf");
    
    // Display a success message to the user
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("PDF généré");
    alert.setHeaderText(null);
    alert.setContentText("Le PDF a été généré avec succès.");
    alert.showAndWait();
}


    
}
