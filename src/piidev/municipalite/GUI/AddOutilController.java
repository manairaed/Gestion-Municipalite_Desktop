/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package piidev.municipalite.GUI;

import piidev.municipalite.entites.outils;
import piidev.municipalite.services.outilsCRUD;
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

/**
 * FXML Controller class
 *
 * @author Amen
 */
public class AddOutilController implements Initializable {

    @FXML
    private Text productName;
    @FXML
    private TextField nameInput;
    @FXML
    private HBox nameInputErrorHbox;
    @FXML
    private Text nameInputError;
    @FXML
    private TextField priceInput;
    @FXML
    private HBox priceInputErrorHbox;
    @FXML
    private Text priceInputError;
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
    private Button update_offreBtn;

    /**
     * Initializes the controller class.
     */
    private File selectedImageFile;
    private String imageName = null;
    private int nomTest = 0;
    private int priceTest = 0;
    private int photoTest = 0;
    @FXML
    private Button add_new_offreBtn1;
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nameInputErrorHbox.setVisible(false);
     
        priceInputErrorHbox.setVisible(false);
        photoInputErrorHbox.setVisible(false);

        if (outils.actionTest == 0) { // add offre
          //  update_offreBtn.setVisible(false);

        //} 
       // else { // update offre
            add_new_offreBtn.setVisible(true);

            // Instancier le service de produit
              outilsCRUD  ls= new outilsCRUD();
            outils outil = new outils();
           try {
             outil = ls.getOneOffre(outil.getId());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        nameInput.setText(outil.getLabel_outils());
      
        priceInput.setText(outil.getQuantite());
       // Image image = new Image(getClass().getResource("/assets/OffresUploads/" + outil.getImage()).toExternalForm());
       // imageInput.setImage(image);
        imageName = outil.getImage();

            nomTest = 1;
          
            priceTest = 1;
            

        }
}
@FXML
    void addNewOffre(MouseEvent event) throws SQLException, IOException{

        outils outil = new outils();

        if (nameInput.getText().isEmpty()) {
            nomTest = 0;
            nameInputErrorHbox.setVisible(true);
        } else {
            if (nomTest == 1) {
                outil.setLabel_outils(nameInput.getText());
            }
        }

        if (priceInput.getText().isEmpty()) {
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            if (priceTest == 1) {
                outil.setQuantite(priceInput.getText());
            }
        }

        if (imageName == null) {
            photoTest = 0;
            photoInputErrorHbox.setVisible(true);
        } else {
            photoTest = 1;
            outil.setImage(imageName);
        }

        if (nomTest == 1  && priceTest == 1
                && photoTest == 1) {
            // Instancier le service de produit
             outilsCRUD  ls= new outilsCRUD();
         
            try {
                ls.ajouter(outil); 
            }
                catch (SQLException e) {
                e.printStackTrace();
            }
           FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOutils.fxml"));
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
            } 

        }

    

    @FXML
    void ajouter_image(MouseEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Images", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedImageFile = fileChooser.showOpenDialog(imageInput.getScene().getWindow());
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
    void nameTypedInput(KeyEvent event) {
        String nameText = ((TextField) event.getSource()).getText();
//        if (!nameText.isEmpty()) {
//            nameInputErrorHbox.setVisible(false);
//            nomTest = 1;
//        }
    }

 

    @FXML
    void priceTypedInput(KeyEvent event) {
        String priceText = ((TextField) event.getSource()).getText();
//        if (!priceText.matches("-?\\d+")) {
//            priceInputError.setText("le prix doit etre un entier");
//            priceInputErrorHbox.setVisible(true);
//            priceTest = 0;
//        } else {
//            int price = Integer.parseInt(priceText);
//            if (price < 0) {
//                priceInputError.setText("le prix doit etre positif");
//                priceInputErrorHbox.setVisible(true);
//                priceTest = 0;
//            } else {
//                priceInputErrorHbox.setVisible(false);
//                priceTest = 1;
//            }
//        }
    }

//    void updateOffre(MouseEvent event) throws SQLException {
//        
//            outils outil = new outils();
//
//        outil.setId(outil.getId());
//
//        if (nameInput.getText().isEmpty()) {
//            nomTest = 0;
//            nameInputErrorHbox.setVisible(true);
//        } else {
//            if (nomTest == 1) {
//                outil.setLabel_outils(nameInput.getText());
//            }
//        }
//
//    
//       
//
//      
//
//        if (priceInput.getText().isEmpty()) {
//            priceTest = 0;
//            priceInputErrorHbox.setVisible(true);
//        } else {
//            if (priceTest == 1) {
//                outil.setQuantite(priceInput.getText());
//            }
//        }
//
//        
//
//        if (imageName == null) {
//            photoTest = 0;
//            photoInputErrorHbox.setVisible(true);
//        } else {
//            photoTest = 1;
//            outil.setImage(imageName);
//        }
//
//        System.out.println("nom: " +nomTest+"prix: " + priceTest+"photo: " + photoTest);
//        if (nomTest == 1 && priceTest == 1
//                && photoTest == 1) {
//            // Instancier le service de produit
//            outilsCRUD  ls= new outilsCRUD();
//            try {
//                
//                ls.modifier( outil );
//                
//                
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("ListOutils.fxml"));
//
//                Parent root = loader.load();
//                // Accéder à la pane content_area depuis le controller de
//                // OneProductListCard.fxml
//              //  Parent root = FXMLLoader.load(getClass().getResource("ListeOffre.fxml"));
//              //  Main.stage.getScene().setRoot(root);
//                
//              Pane contentArea = (Pane) ((Node) event.getSource()).getScene().lookup("#content_area");
//
//                // Vider la pane et afficher le contenu de ProductsList.fxml
//                contentArea.getChildren().clear();
//                contentArea.getChildren().add(root);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//        }
//
//    }

    @FXML
    private void Annuler(ActionEvent event) {
         nameInput.setText("");
        priceInput.setText("");
        
    }
    

  

    
}
  