/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.Reservation;
import piidev.municipalite.entites.outils;
import piidev.municipalite.services.ReservationCrud;
import piidev.municipalite.services.outilsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class OneReserveListController  {

    @FXML
    private Text Quantite;
    @FXML
    private Text Date_debut;
    @FXML
    private Text Date_fin;
    @FXML
    private Text Etat;
    @FXML
    private Button confirmer;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
     public void setOffreData(Reservation reser) {
            // Instancier le service de produit
           ReservationCrud  ls= new ReservationCrud();
//
            
    
          
            // get category Name
           Quantite.setText(reser.getQuantite());
           Date_debut.setText(String.valueOf(reser.getDate_deb()));
           Date_fin.setText(String.valueOf(reser.getDate_fin())); 
            Etat.setText(reser.getEtat()); 
    
           // priceOffre.setText("" + offre.getPrix());





           // FXMLLoader loader = new FXMLLoader(getClass().getResource(".fxml"));
          //  try {
           //     Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
               // Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
             //   contentArea.getChildren().clear();
               // contentArea.getChildren().add(root);
          //  } catch (IOException e) {
           //     e.printStackTrace();
           // }
            // end
           
                   }
        public void confrimerEtat(Reservation reser) {
             ReservationCrud  ls= new ReservationCrud();
            confirmer.setOnMouseClicked(event -> { 
            System.out.println("ID du offre à modfier : " + reser.getId());
            try {
                ls.confirmeEtat(reser.getId(),"Confirmé");
                
             
            } catch (SQLException e) {
                e.printStackTrace();
            }
              FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserveList.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
 }
            
            public void AnnulerReservation(Reservation reser) {
             ReservationCrud  ls= new ReservationCrud();
            annuler.setOnMouseClicked(event -> { 
            System.out.println("ID du offre à modfier : " + reser.getId());
            try {
                ls.supprimer(reser.getId());
                
             
            } catch (SQLException e) {
                e.printStackTrace();
            }
               FXMLLoader loader = new FXMLLoader(getClass().getResource("ReserveList.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });

           
            
    
    
        }
}
    

