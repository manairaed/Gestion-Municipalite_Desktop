/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package INTERFACE;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import entities.RendezVous;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.RendezVousCRUD;

/**
 * FXML Controller class
 *
 * @author Farhat
 */
public class AddrendezcardController implements Initializable {

    @FXML
    private Text productName;
    @FXML
    private TextArea descriptionInput;
    @FXML
    private HBox descriptionInputErrorHbox;
    @FXML
    private Text descriptionInputError;
    @FXML
    private JFXDatePicker date;
    @FXML
    private HBox priceInputErrorHbox;
    @FXML
    private Text priceInputError;
    @FXML
    private JFXTimePicker time;
    @FXML
    private HBox validiteInputErrorHbox;
    @FXML
    private Text validiteInputError;
    @FXML
    private Button add_new_offreBtn;
    @FXML
    private Button update_offreBtn;
    
    
   

    private int descriptionTest = 0;
    private int validiteTest = 0;
    private int priceTest = 0;
    
    private LocalDateTime lastMeetingDateTime;
    
    RendezVousCRUD cs = new RendezVousCRUD();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
        descriptionInputErrorHbox.setVisible(false);
        validiteInputErrorHbox.setVisible(false);
        priceInputErrorHbox.setVisible(false);
     

        if (RendezVous.actionTest == 0) { // add offre
            update_offreBtn.setVisible(false);

        } else { // update offre
            add_new_offreBtn.setVisible(false);

            // Instancier le service de produit
            RendezVousCRUD cs = new RendezVousCRUD();
            RendezVous offre = new RendezVous();
           try {
             offre = cs.getOnerendez(RendezVous.getIdrendez());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
        descriptionInput.setText(offre.getDescription());
        LocalDateTime dateTime = offre.getDate_ren();
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    String dateString = dateTime.format(dateFormatter);
    String timeString = dateTime.format(timeFormatter);
     date.setValue(LocalDate.parse(dateString));
     time.setValue(LocalTime.parse(timeString));

            
            descriptionTest = 1;
            validiteTest = 1;
            priceTest = 1;
            

        }
    }    

    @FXML
    private void addNewOffre(ActionEvent event) {
        RendezVous offre = new RendezVous();

        

        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
           offre.setDescription(descriptionInput.getText());
        descriptionTest = 1;
        }
        

       

        // Get the current date and time
LocalDateTime now = LocalDateTime.now();

// Get the selected date and time
        LocalDate selectedDate = date.getValue();
        LocalTime selectedTime = time.getValue();

// Check if the selected date and time are valid
        if (selectedDate == null || selectedTime == null) {
            // Handle error when either date or time is null
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            // Combine date and time values into a single LocalDateTime object
            LocalDateTime dateTime = LocalDateTime.of(selectedDate, selectedTime);

            // Check if the selected date and time are valid
            if (dateTime.isBefore(now)) {
                // Handle error when selected date and time is before current date and time
                validiteTest = 0;
                validiteInputErrorHbox.setVisible(true);
                priceTest = 0;
                priceInputErrorHbox.setVisible(true);
            } else if (selectedTime.isBefore(LocalTime.of(8, 0)) || selectedTime.isAfter(LocalTime.of(17, 0))) {
                // Handle error when selected time is before 8am or after 5pm
                validiteTest = 0;
                validiteInputErrorHbox.setVisible(true);
                priceTest = 0;
                priceInputErrorHbox.setVisible(true);
            } else if (!cs.canAddMeeting(selectedDate, selectedTime)) {
            // Handle error when selected date and time is less than 30 minutes after the last meeting in the database
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            // Set the combined LocalDateTime object to the date_ren column
            offre.setDate_ren(dateTime);
            validiteTest = 1;
            priceTest = 1;
        }
    }

        // Check if the selected date and time are at least 30 minutes after the last meeting in the database




        
        System.out.println("des: " + descriptionTest+ "va: " + validiteTest+"prix:" + priceTest );
       

        if ( descriptionTest == 1 && validiteTest == 1 && priceTest == 1
                ) {
            // Instancier le service de produit
            RendezVousCRUD cs = new RendezVousCRUD();
            try {
                cs.ajouterrendezvous(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("usermeeting.fxml"));

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
    void descriptionTypedInput(KeyEvent event) {
        String descriptionText = ((TextArea) event.getSource()).getText();
        if (!descriptionText.isEmpty()) {
            descriptionInputErrorHbox.setVisible(false);
            descriptionTest = 1;
        }
    }
   @FXML
    void validiteTypedInput(KeyEvent event) {
        validiteTest = 1;
        validiteInputErrorHbox.setVisible(false);

        LocalDate validiteDate = ((JFXDatePicker) event.getSource()).getValue();
        String date = "";
        if (validiteDate != null) {
            date = validiteDate.toString();
            validiteInputErrorHbox.setVisible(false);
            validiteTest = 1;
        }
    }
     @FXML
    private void priceTypedInput(KeyEvent event) {
           priceTest = 1;
        priceInputErrorHbox.setVisible(false);

        LocalTime time = ((JFXTimePicker) event.getSource()).getValue();
        String validiteText = "";
        if (time != null) {
            validiteText = time.toString();
            priceInputErrorHbox.setVisible(false);
            validiteTest = 1;
        }
    }

   
    
    
    
//    public boolean isDateValid(LocalDateTime dateTime) {
//        // Query the database to get the last meeting date and time
//        RendezVousCRUD cs = new RendezVousCRUD();
//        lastMeetingDateTime = cs.queryLastMeetingDateTime();
//
//        // Check if the selected date and time are at least 30 minutes after the last meeting in the database
//        LocalDateTime minDateTime = lastMeetingDateTime.plusMinutes(30);
//        return dateTime.isAfter(minDateTime);
//    }

    @FXML
    private void updateOffre(ActionEvent event) {
         RendezVous offre = new RendezVous();

        offre.setId(RendezVous.getIdrendez());

       
        if (descriptionInput.getText().isEmpty()) {
            descriptionTest = 0;
            descriptionInputErrorHbox.setVisible(true);
        } else {
            if (descriptionTest == 1) {
                offre.setDescription(descriptionInput.getText());
            }
        }

       

        LocalDateTime now = LocalDateTime.now();

// Get the selected date and time
        LocalDate selectedDate = date.getValue();
        LocalTime selectedTime = time.getValue();

// Check if the selected date and time are valid
        if (selectedDate == null || selectedTime == null) {
            // Handle error when either date or time is null
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            // Combine date and time values into a single LocalDateTime object
            LocalDateTime dateTime = LocalDateTime.of(selectedDate, selectedTime);

            // Check if the selected date and time are valid
            if (dateTime.isBefore(now)) {
                // Handle error when selected date and time is before current date and time
                validiteTest = 0;
                validiteInputErrorHbox.setVisible(true);
                priceTest = 0;
                priceInputErrorHbox.setVisible(true);
            } else if (selectedTime.isBefore(LocalTime.of(8, 0)) || selectedTime.isAfter(LocalTime.of(17, 0))) {
                // Handle error when selected time is before 8am or after 5pm
                validiteTest = 0;
                validiteInputErrorHbox.setVisible(true);
                priceTest = 0;
                priceInputErrorHbox.setVisible(true);
            } 
             else if (!cs.canAddMeeting(selectedDate, selectedTime)) {
            // Handle error when selected date and time is less than 30 minutes after the last meeting in the database
            validiteTest = 0;
            validiteInputErrorHbox.setVisible(true);
            priceTest = 0;
            priceInputErrorHbox.setVisible(true);
        } else {
            // Set the combined LocalDateTime object to the date_ren column
            offre.setDate_ren(dateTime);
            validiteTest = 1;
            priceTest = 1;
        }
    }
    

        

       

        System.out.println("des: " + descriptionTest+ "va: " + validiteTest+"prix: " );
        if ( descriptionTest == 1 && validiteTest == 1 && priceTest == 1) {
            // Instancier le service de produit
            RendezVousCRUD cs = new RendezVousCRUD();
            try {
                cs.modifierrendez(offre);
                

                FXMLLoader loader = new FXMLLoader(getClass().getResource("usermeeting.fxml"));

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

   
   

   

    
}
