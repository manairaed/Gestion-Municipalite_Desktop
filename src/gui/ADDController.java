/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class ADDController implements Initializable {

    @FXML
    private DatePicker tfdate;
    @FXML
    private TextField tfdescription;
    @FXML
    private Button tfajouter;
    @FXML
    private Button doc;
    @FXML
    private Button rendezvous;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
  private void saverebdezvous(ActionEvent event) {
    if (tfdescription.getText().isEmpty() || tfdate.getValue() == null) {
        Alert a = new Alert(Alert.AlertType.ERROR, "description ou date invalide(s)", ButtonType.OK);
        a.showAndWait();
    } else {
        String description = tfdescription.getText();
        LocalDate date_ren = tfdate.getValue();
        LocalDate currentDate = LocalDate.now(); // Get today's date

        // Check if selected date is lower than today's date
       if (date_ren.isBefore(currentDate)) {
           Alert a = new Alert(Alert.AlertType.ERROR, "Date cannot be lower than today's date", ButtonType.OK);
            a.showAndWait();
            return; // Return early if date is lower than today's date
       }

        RendezVous R = new RendezVous(description,date_ren);
        RendezVousCRUD PC = new RendezVousCRUD();

        // Check if selected date already exists in the database
        

        PC.ajouterrendezvous(R);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailsrendez.fxml"));

        try {
            Parent root = loader.load();
            DetailsrendezController dwc = loader.getController();
            dwc.settextdescription(R.getDescription());
            dwc.settextdate(R.getDate_ren().toString());

            tfdescription.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("error:" + ex.getMessage());
        }
    }
}



    @FXML
    private void godoc(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherDOC.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void gorendezvous(ActionEvent event) {
       try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ADD.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}

    
    

