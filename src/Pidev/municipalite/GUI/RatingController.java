/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import chat.ClientController;
import com.jfoenix.controls.JFXTextField;
import entities.avis;
import java.io.IOException;
import javafx.scene.control.Button;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import tools.MaConnexion;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import service.AvisServices;
/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class RatingController implements Initializable {

    @FXML
    private JFXTextField commentaire;
    @FXML
    private JFXTextField titre;
    @FXML
    private Rating rating;
    @FXML
    private Button ajouterButton;
    @FXML
    private AnchorPane recpane;
   
    @FXML
    private TableColumn<avis, String> titret;
    @FXML
    private TableColumn<avis, String> commentairet;
    
     public static long id_user;
    
    
    Notifications no;
    String erreur;
    @FXML
    private TableView<avis> table;
    
        ObservableList<avis> data=FXCollections.observableArrayList();
 
    AvisServices as =new AvisServices();
    @FXML
    private Button a;
    @FXML
    private TableColumn<avis, String> ratingg;
    @FXML
    private Button btnChat2;






    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
        
          try { 
            initialiserlist();

            

        } catch (SQLException ex)
 {
            Logger.getLogger(RatingController.class.getName()).log(Level.SEVERE, null, ex);
        }refreshlist();
        
        /*try {
    Connection cnx = MaConnexion.getInstance().getCnx();
    rs = cnx.createStatement().executeQuery("SELECT id FROM produit");
    while(rs.next())
    id1.getItems().addAll(rs.getInt("id"));
    } catch (SQLException ex)
    {
    Logger.getLogger(ProduitController.class.getName()).log(Level.SEVERE, null, ex);
    }*/
       
    } 
    
    
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {
        
        int index = table.getSelectionModel().getSelectedIndex();
    if (index <= -1){
    
        return;
    }
    
            Connection cnx = MaConnexion.getInstance().getCnx();
            
     //ResultSet rsd =null ;
     TableColumn.CellEditEvent edittedcell = null;
        avis a = gettempAvis(edittedcell);

    titre.setText(titret.getCellData(index).toString());
    commentaire.setText(commentairet.getCellData(index).toString());
   

              
    }
    
    
     public avis gettempAvis(TableColumn.CellEditEvent edittedCell) {
        avis test = table.getSelectionModel().getSelectedItem();
        return test;
    }
    
    
    public void refreshlist(){
        data.clear();
        data=FXCollections.observableArrayList(as.afficher());
          ratingg.setCellValueFactory(new PropertyValueFactory<>("rating"));
          commentairet.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
          titret.setCellValueFactory(new PropertyValueFactory<>("titre"));

        table.setItems(data);
    }
    

    public void initialiserlist() throws SQLException{
             try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM avis");
         
            } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    } 
    
    

      
    
    
    @FXML
    private void ajouterAvis(ActionEvent event) {
        int p = JOptionPane.showConfirmDialog(null,"Do you really want to Add","Add",JOptionPane.YES_NO_OPTION);
 if(p==0){
        
       StringBuilder errors=new StringBuilder();
        if(titre.getText().trim().isEmpty()){
            errors.append("- Please enter un Titre\n");
        }
        
        else{
            avis a =new avis();
            a.setRating((int) rating.getRating());
            System.out.println("rating given by user:" + rating.getRating());
            a.setTitre(titre.getText());
            a.setCommentaire(commentaire.getText());

    
            as.ajouterAvis(a);

            no = Notifications.create()
                    .title("Avis Ajouter")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
        }
        refreshlist();
        
 }
    }

    @FXML
     public void supprimer(ActionEvent event) throws SQLException {
        
         //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());
         int p = JOptionPane.showConfirmDialog(null,"Do you really want to Delete","Delete",JOptionPane.YES_NO_OPTION);
 if(p==0){
 
   TableColumn.CellEditEvent edittedcell = null;
        avis a = gettempAvis(edittedcell);

        if (a != null) {

            int i = a.getId();
            AvisServices cat = new AvisServices();

            int s = cat.supprimerAvis(i);
            if (s == 1) {
               no = Notifications.create()
                    .title("Avis Supprimer")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
              
                data.clear();
                initialiserlist(); 
                refreshlist();
                table.refresh();
    titre.setText("");
    commentaire.setText("");


            }

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Selection un champ SVP");
            alert.showAndWait();
        }
 }
    }

    @FXML
    private void b(ActionEvent event)throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/dash.fxml"));
           recpane.getChildren().setAll(pane); 
        
    
       }

    @FXML
    private void Message(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/Server.fxml"));
        Parent root;
        try {

            root = loader.load();
            ClientController c1 = loader.getController();
            btnChat2.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}     
    
