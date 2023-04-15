/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vehicule.gui;

import entities.Categorie;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import static jdk.nashorn.internal.runtime.Debug.id;
import services.ServiceCat;

/**
 * FXML Controller class
 *
 * @author Ala
 */
public class CategorieController implements Initializable {

    @FXML
    private TextField tflabel;
    @FXML
    private Button btnajoutcat;
    @FXML
    private TableView<Categorie> tbaffichcategorie;
    @FXML
    private TableColumn<Categorie, String> affichagecategorie;
      private ObservableList<Categorie> eventList = FXCollections.observableArrayList();
    @FXML
    private Button btnmodifcat;
    @FXML
    private Button btnsupp;
    @FXML
    private Button versvehicule;

    /**
     * Initializes the controller class.
     */
     

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      ObservableList<Categorie> listU = FXCollections.observableArrayList();  
      ServiceCat l = new ServiceCat();

        /* ID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));*/
        affichagecategorie.setCellValueFactory(new PropertyValueFactory<>("labelcat"));
        l.affichercat().forEach(r -> {

               
                //r.setId((int)r.getTotal_ht());
                listU.add(r); });
        tbaffichcategorie.setItems(listU);
        
           }    

    @FXML
    
    private void ajoutercategorie(ActionEvent event) {
         if (tflabel.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Erreur de saisie !");
            alert.setContentText("SVP remplir tous les champs" + "");
            alert.showAndWait();   
         }
        Categorie categorie = new Categorie() ;
        ServiceCat servicecat = ServiceCat.getInstance();
        categorie.setLabelcat(tflabel.getText());
        servicecat.ajoutercat(categorie);
        
        
        
    }

    @FXML
    private void modifiercategorie(ActionEvent event) {
        Categorie categorie = new Categorie();
        ServiceCat servicecat = ServiceCat.getInstance();
        categorie.setLabelcat(tflabel.getText());
        servicecat.modifercat(categorie);

    }

    @FXML
    private void supprimmercategorie(ActionEvent event) {
       Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Voulez vous suprimer cette categorie ?");
        Optional<ButtonType> result = alert.showAndWait();
        Categorie supprimercat = tbaffichcategorie.getSelectionModel().getSelectedItem();

        if (supprimercat != null) {
            ServiceCat crs = new ServiceCat();
            crs.supprimercat(supprimercat);
            eventList.remove(supprimercat);

            if (result.get() == ButtonType.OK) {

                JOptionPane.showMessageDialog(null, "categorie supprimé");

            }

            System.out.println(result.get().getText().equals("Annuler"));
            if (result.get().getText().equals("Annuler")) {
                System.out.println("qqqqq");
                JOptionPane.showMessageDialog(null, "categorie Event n'a pas été supprimé");

                return;
        // Categorie c = tbaffichcategorie.getSelectionModel().getSelectedItem();
       //  ServiceCat crs = new ServiceCat();

       // crs.supprimercat(c);
       // affiche();
            }

            // affiche();
        }
       
    }
    public void setCellValue() {
      

        tbaffichcategorie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {

                Categorie p1 = tbaffichcategorie.getItems().get(tbaffichcategorie.getSelectionModel().getSelectedIndex());
                affichagecategorie.setText(String.valueOf(p1.getId()));
 
            }
        });
                }
     public void affiche() {

        ObservableList<Categorie> c = (ObservableList<Categorie>) new ServiceCat().affichercat();

        /*ID.setCellValueFactory(new PropertyValueFactory<>("id"));*/
       
        affichagecategorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        tbaffichcategorie.setItems(c);

    }

                
            

    @FXML
            private void versvehicule(ActionEvent event) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("./Vehicule.fxml"));
                    Parent view_2 = loader.load();

                    Stage stage;
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene(view_2);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
                }}}

    
   
    
     

      

           

      
      
