/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import com.jfoenix.controls.JFXTimePicker;
import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class EditmeetingController implements Initializable {

    @FXML
    private TextField textdesc;
    @FXML
    private DatePicker textdate;
    
    private RendezVous rendezVous;
    @FXML
    private JFXTimePicker texttime;
    @FXML
    private Button savebtn;
    @FXML
    private TextField textid;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setRendezVous(RendezVous rendezVous) {

                this.rendezVous = rendezVous;
                textid.setText(String.valueOf(rendezVous.getId()));
        
        textdesc.setText(rendezVous.getDescription());
        LocalDateTime dateTime = rendezVous.getDate_ren();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    String dateString = dateTime.format(dateFormatter);
    String timeString = dateTime.format(timeFormatter);
     textdate.setValue(LocalDate.parse(dateString));
     texttime.setValue(LocalTime.parse(timeString));
     
    }

    @FXML
    private void update(ActionEvent event) {
        RendezVousCRUD cs = new RendezVousCRUD();

        RendezVous c = new RendezVous();
        
        c.setId(Integer.parseInt(textid.getText()));
        

        c.setDescription(textdesc.getText());

        LocalDate date = textdate.getValue();
        LocalTime time = texttime.getValue();
        LocalTime modifiedTime = LocalTime.of(time.getHour(), time.getMinute(), 00);
         LocalDateTime dateTime = date.atTime(modifiedTime);  // Combine date and time into a LocalDateTime value
        //c.setDate_ren(dateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = dateTime.format(formatter);
    LocalDateTime parsedDateTime = LocalDateTime.parse(formattedDateTime, formatter);
    c.setDate_ren(parsedDateTime);

        cs.modifierrendez(c);
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // Update the pattern to include seconds
//    String formattedDateTime = dateTime.format(formatter);  // Format LocalDateTime to a string with seconds
    System.out.println("Formatted date and time: " + formattedDateTime);  // Print the formatted date and time

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText("rendez vous is updated successfully!");
        alert.show();
        
//         Stage currentStage = (Stage) textid.getScene().getWindow();
//    currentStage.close();

    
   
        
    }
     
     

    
}