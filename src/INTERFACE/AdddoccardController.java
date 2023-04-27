/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.Document;
import entities.RendezVous;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import services.DocumentCRUD;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AdddoccardController implements Initializable {

    @FXML
    private Text productName;
    @FXML
    private TextField nameInput;
    @FXML
    private HBox nameInputErrorHbox;
    @FXML
    private Text nameInputError;
    @FXML
    private ImageView imageInput;
    @FXML
    private HBox choose_photoBtn;
    @FXML
    private HBox photoInputErrorHbox;
    @FXML
    private Text photoInputError;
    @FXML
    private Button add_new_offreBtn;
    @FXML
    private Button update_offreBtn;
    
      private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    
    private int photoTest = 0;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameInputErrorHbox.setVisible(false);
        
        photoInputErrorHbox.setVisible(false);

        if (Document.actionTest == 0) { // add offre
            update_offreBtn.setVisible(false);

        } else { // update offre
            add_new_offreBtn.setVisible(false);

            // Instancier le service de produit
              DocumentCRUD cs = new DocumentCRUD();
            Document offre = new Document();
           try {
             offre = cs.getOnedoc(Document.getIddoc());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        nameInput.setText(offre.getName());
        
        Image image = new Image(getClass().getResource("/assets/OffresUploads/" + offre.getImage()).toExternalForm());
        imageInput.setImage(image);
        imageName = offre.getImage();

            nomTest = 1;
            photoTest = 1;
           
            

        }
        // TODO
    }    

    @FXML
    private void nameTypedInput(KeyEvent event) {
        String nameText = ((TextField) event.getSource()).getText();
        if (!nameText.isEmpty()) {
            nameInputErrorHbox.setVisible(false);
            nomTest = 1;
        }
    }

    @FXML
    private void ajouter_image(MouseEvent event) throws IOException {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
        if (selectedImageFile != null) {
            Image image = new Image(selectedImageFile.toURI().toString());
            imageInput.setImage(image);

            // Générer un nom de fichier unique pour l'image
            String uniqueID = UUID.randomUUID().toString();
            String extension = selectedImageFile.getName().substring(selectedImageFile.getName().lastIndexOf("."));
            imageName = uniqueID + extension;

            Path destination = Paths.get(System.getProperty("user.dir"), "src", "assets", "OffresUploads", imageName);
            Files.copy(selectedImageFile.toPath(), destination, StandardCopyOption.REPLACE_EXISTING);

            // pour le controle de saisie
            photoTest = 1;
            photoInputErrorHbox.setVisible(false);
        }
    }

     @FXML
    private void addNewOffre(ActionEvent event) {
        Document offre = new Document();

        if (nameInput.getText().isEmpty()) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                offre.setName(nameInput.getText());
            }
        }

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            offre.setImage(imageName);
        }

        if (nomTest == 1 && photoTest == 1) {
            // Instancier le service de produit
            DocumentCRUD cs = new DocumentCRUD();
            try {
                cs.ajouterdocument2(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("listdocadmin.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
              //  Parent root = FXMLLoader.load(getClass().getResource("ListeOffre.fxml"));
              //  Main.stage.getScene().setRoot(root);
                
              Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @FXML
    private void updateOffre(MouseEvent event) {
        Document offre = new Document();

        offre.setId(Document.getIddoc());

        if (nameInput.getText().isEmpty()) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                offre.setName(nameInput.getText());
            }
        }

       

       

        
        
        

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            offre.setImage(imageName);
        }

        System.out.println("nom: " +nomTest+"photo: " + photoTest);
        if (nomTest == 1 
                && photoTest == 1) {
            // Instancier le service de produit
            DocumentCRUD cs = new DocumentCRUD();
            try {
                cs.modifier(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeOffre.fxml"));

                Parent root = loader.load();
                // Accéder à la pane content_area depuis le controller de
                // OneProductListCard.fxml
              //  Parent root = FXMLLoader.load(getClass().getResource("ListeOffre.fxml"));
              //  Main.stage.getScene().setRoot(root);
                
              Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");

                // Vider la pane et afficher le contenu de ProductsList.fxml
                contentArea.getChildren().clear();
                contentArea.getChildren().add(root);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    
    
}
