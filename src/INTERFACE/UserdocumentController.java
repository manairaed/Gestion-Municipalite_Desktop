/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import entities.Document;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import services.DocumentCRUD;
import static sun.management.GcInfoCompositeData.getId;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class UserdocumentController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Text CName;
    @FXML
    private Text stockProduit;
    @FXML
    private Text stockProduit11;
    @FXML
    private HBox priceHbox;
    @FXML
    private Button telecharger;
    
    private Document document;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    public void setContratData(Document document) {
        this.document = document;
        
            // Instancier le service de produit
            DocumentCRUD cs = new DocumentCRUD();
            

            Image image = new Image(
                    getClass().getResource("/assets/OffresUploads/" + document.getImage()).toExternalForm());
            img.setImage(image);
    
            CName.setText("" + document.getName());
            // get category Name
         
           
        // END deleteProduit btn click
       
        

    }

    @FXML
    private void telecharger(ActionEvent event) throws SQLException {
        DocumentCRUD cs = new DocumentCRUD();
        cs.getDownloadedCount(document.getId());

        try {
            // Get the image from the application's resources
            Image image = new Image(getClass().getResource("/assets/OffresUploads/" + document.getImage()).toExternalForm());

            // Prompt the user to select a location to save the image
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Image");
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                // Get the file extension of the selected file
                String extension = getFileExtension(file);

                // Save the image to the selected file
                BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
                ImageIO.write(bImage, extension, file);

                // Open the file
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFileExtension(File file) {
        String fileName = file.getName();
        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return fileName.substring(lastDotIndex + 1).toLowerCase();
        } else {
            return "png"; // default to PNG format if file extension is missing
        }
    }


}
