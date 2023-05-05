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
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class ReserveListController implements Initializable {

    @FXML
    private Pane content_area;
    @FXML
    private GridPane offreListContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        
        ReservationCrud  ls= new ReservationCrud();


         List<Reservation> list=ls.getAll(); 
            
   
      

        // product list ------------------------------------------------
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < list.size() ; i++) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("OneReserveList.fxml"));
                VBox OneoutilCard = fxmlLoader.load();
               OneReserveListController outilCardController = fxmlLoader.getController();
                outilCardController.setOffreData(list.get(i));
                outilCardController.confrimerEtat(list.get(i)); 
               outilCardController.AnnulerReservation(list.get(i)); 
                
                  

                if (column == 1) {
                    column = 0;
                    ++row;
                }
                offreListContainer.add(OneoutilCard, column++, row);
                // GridPane.setMargin(oneProductCard, new Insets(10));
                GridPane.setMargin(OneoutilCard, new javafx.geometry.Insets(0, 10, 25, 10));
                OneoutilCard.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.09), 25, 0.1, 0, 0);");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // TODO
    }    
    
}
