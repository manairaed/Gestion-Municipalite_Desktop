/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.Document;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.DocumentCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AdmindoccardController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text CName;
    @FXML
    private Text stockProduit;
    @FXML
    private HBox priceHbox;
    @FXML
    private ImageView edit;
    @FXML
    private ImageView supp;
    @FXML
    private Text count;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void edit(MouseEvent event) {
    }

    @FXML
    private void supp(MouseEvent event) {
    }
    
    
    public void setContratData(Document document) {
            // Instancier le service de produit
            DocumentCRUD cs = new DocumentCRUD();
            
            
              int downloadCount = cs.getDownloadCountById(document.getId());

            
           

            Image image = new Image(
                    getClass().getResource("/assets/OffresUploads/" + document.getImage()).toExternalForm());
            img.setImage(image);
    
            CName.setText("" + document.getName());
            
            
         
    // Display the updated download count
            count.setText(Integer.toString(downloadCount));
            
         
            // deleteProduit btn click
            supp.setId(String.valueOf(document.getId()));

            supp.setOnMouseClicked(event -> {
            //System.out.println("ID du offre à supprimer : " + document.getId());
            cs.supprimer(document.getId()); // supprimer le contenu de la liste et afficher la nouvelle liste(apres
                // supprimer)

            FXMLLoader loader = new FXMLLoader(getClass().getResource("listedocadmin.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }
            // end
        });
        // END deleteProduit btn click

        // editProduit btn click
        edit.setId(String.valueOf(document.getId()));

        edit.setOnMouseClicked(event -> {
            System.out.println("ID du produit à modifier : " + document.getId());
            Document.setIddoc(document.getId());

            Document.actionTest = 1; // pour afficher le bouton update

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Adddoccard.fxml"));
            try {
                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
                Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content");

                // Vider la pane et afficher le contenu de AddProduct.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        // END editProduit btn click


            
    
    
    
        }
    
}
