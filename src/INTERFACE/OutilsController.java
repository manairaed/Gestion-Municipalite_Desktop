/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package INTERFACE;

import edu.esprit.entities.outils;
import edu.esprit.services.outilsCRUD;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory; 
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Amen
 */
public class OutilsController implements Initializable {

    @FXML
    private TableColumn<outils, Integer> id;
    @FXML
    private TableColumn<outils, String> nomOutils;
    @FXML
    private TableColumn<outils, String> quantite;
    @FXML
    private TableColumn<outils, String> image;
    @FXML
    private TableView<outils> tableoutils;
    @FXML
    private TextField textid;
    @FXML
    private TextField textlabeloutils;
    @FXML
    private TextField textquantite;
    @FXML
    private TextField textimage;
    @FXML
    private Button Addoutils;
    @FXML
    private Button supprimeroutil;
    @FXML
    private Button modifieroutil;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        showOutils();
        setCellValue();
        // TODO
    }   
    
    public void showOutils()
    {   outilsCRUD  ls= new outilsCRUD();
     ObservableList<outils> list=ls.getAll(); 
 
     id.setCellValueFactory(new PropertyValueFactory<outils,Integer>("id"));
  nomOutils.setCellValueFactory(new PropertyValueFactory<outils,String>("label_outils"));
   quantite.setCellValueFactory(new PropertyValueFactory<outils,String>("quantite"));
   image.setCellValueFactory(new PropertyValueFactory<outils,String>("image"));

   
   tableoutils.setItems(list);
     
        
    }
    public void setCellValue(){
    
    tableoutils.setOnMouseClicked(new EventHandler<MouseEvent>(){
    public void handle(MouseEvent event)
    { 
       
        outils p1=tableoutils.getItems().get(tableoutils.getSelectionModel().getSelectedIndex());
        id.setText(String.valueOf(p1.getId()));
     
     //date_livraison.setValue(p1.getDateLivraison()); 
           textid.setText(String.valueOf(p1.getId()));
               textlabeloutils.setText(String.valueOf(p1.getLabel_outils()));
                   textquantite.setText(String.valueOf(p1.getQuantite()));
                       textimage.setText(String.valueOf(p1.getImage()));
                       
    }
});
}
    @FXML
     private void Addoutils(ActionEvent event) throws Exception { 
         if (textlabeloutils.getText().isEmpty()||textquantite.getText().isEmpty()||textimage.getText().isEmpty()){
             Alert a= new Alert (Alert.AlertType.ERROR, "champ must not be empty",ButtonType.OK) ;
             a.showAndWait();
         }
         else {    
                outilsCRUD  ls= new outilsCRUD ();
         
            outils l =new outils();
          
          l.setLabel_outils(textlabeloutils.getText());
          l.setQuantite(textquantite.getText());
          l.setImage(textimage.getText());
        
            try {
                ls.ajouter(l) ;
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("outil is added successfully!");
           // EnvoyerMail.sendMail("salim.sghaier@esprit.tn","Vous avez un nouveau client: \n" +"Son identifiant est:");
            alert.show(); 
            showOutils() ; 
         
        }
          catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            }
     }
      
    
    @FXML
    private void Supprimeroutils(ActionEvent event) {
            outilsCRUD  cs= new outilsCRUD ();
                                       
           
        
            outils c =new outils(); 
              

           
            int id=Integer.parseInt(textid.getText()) ; 
               
           
            c.setId(id);
                        

            try 
            {
            cs.supprimer(id); 
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("service is deleted successfully!");
            alert.show();
         showOutils(); 
            
            } 
               catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


 }
    @FXML
    private void Updateoutils(ActionEvent event){
            outilsCRUD  cs= new outilsCRUD ();
                       
        
           outils c =new outils(); 
           
       int id=Integer.parseInt(textid.getText());
          c.setLabel_outils(textlabeloutils.getText());
  
          c.setQuantite(textquantite.getText());   
          c.setImage(textimage.getText()); 
           
           try 
            {

          cs.modifier(id,textlabeloutils.getText(),textquantite.getText(),textimage.getText()) ;
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Success");
          alert.setContentText("outil is updated successfully!");
          alert.show();
          showOutils();
             }
            catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }}

    @FXML
    private void Homeback(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashbord.fxml"));
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


