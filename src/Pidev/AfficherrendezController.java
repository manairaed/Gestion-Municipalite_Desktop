///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
// */
//package pidev;
//
//import entities.RendezVous;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.text.ParseException;
//import java.time.Instant;
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
//import java.util.Date;
//import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.scene.image.ImageView;
//import javafx.scene.input.MouseEvent;
//import javafx.stage.Stage;
//import services.RendezVousCRUD;
//import tools.MyConnection;
//
///**
// * FXML Controller class
// *
// * @author Farhat
// */
//public class AfficherrendezController implements Initializable {
//
//    @FXML
//    private TableView<RendezVous> tabrendez;
//    @FXML
//    private TableColumn<RendezVous, String> colid;
//    @FXML
//    private TableColumn<RendezVous, String> coldesc;
//    @FXML
//    private TableColumn<RendezVous, String> coldate;
//    @FXML
//    private TextField textid;
//    @FXML
//    private DatePicker textdate;
//    @FXML
//    private TextField textdesc;
//    @FXML
//    private Button btnsup;
//    @FXML
//    private Button btnedit;
//    @FXML
//    private Button document;
//    @FXML
//    private Button rendezvous;
//    
//     
//    
//    
//
//
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        setCellValue();
//
//        try {
//            loadDate();
//        } catch (ParseException ex) {
//            Logger.getLogger(AfficherrendezController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    private void loadDate() throws ParseException {
//        RendezVousCRUD ls= new RendezVousCRUD();
//         ObservableList<RendezVous> list=ls.afficherrendezvous();
//
//        
//
//        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
//        coldesc.setCellValueFactory(new PropertyValueFactory<>("description"));
//        coldate.setCellValueFactory(new PropertyValueFactory<>("date_ren"));
//        
//        tabrendez.setItems(list);
//
//    }
//    public void setCellValue() {
//
//        tabrendez.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            public void handle(MouseEvent event) {
//
//                RendezVous p1 = tabrendez.getItems().get(tabrendez.getSelectionModel().getSelectedIndex());
//                colid.setText(String.valueOf(p1.getId()));
//
//                //date_livraison.setValue(p1.getDateLivraison()); 
//                textid.setText(String.valueOf(p1.getId()));
//                textdesc.setText(String.valueOf(p1.getDescription()));
//                textdate.setValue(p1.getDate_ren());
//            }
//        });
//    }
//
//    @FXML
//    private void supp(ActionEvent event) throws ParseException {
//        RendezVousCRUD cs = new RendezVousCRUD();
//
//        RendezVous c = new RendezVous();
//
//        int id = Integer.parseInt(textid.getText());
//
//        c.setId(id);
//
//        cs.supprimerrendez(id);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setContentText("rendez-vous is deleted successfully!");
//        alert.show();
//        loadDate();
//
//    }
//
//    @FXML
//    private void edit(ActionEvent event) throws ParseException {
//        RendezVousCRUD cs = new RendezVousCRUD();
//
//        RendezVous c = new RendezVous();
//
//        c.setId(Integer.parseInt(textid.getText()));
//        c.setDescription(textdesc.getText());
//
//        c.setDate_ren(textdate.getValue());
//        
//
//        cs.modifierrendez(c);
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Success");
//        alert.setContentText("rendez vous is updated successfully!");
//        alert.show();
//        loadDate();
//    }
//
//    @FXML
//    private void godocument(ActionEvent event) {
//         try {
//            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("ajouterDOC.fxml"));
//            Parent root = loader.load();
//            Scene scene = new Scene(root);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @FXML
//    private void gorendez(ActionEvent event) {
//          try {
//        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("afficherrendez.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        stage.setScene(scene);
//        stage.show();
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    }
//}
//    
//
//   
//    
//    
//  
