/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.TextField;
import entities.Reclamation;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatisticsController implements Initializable {

    @FXML
    private BarChart<?, ?> rt;
    @FXML
    private NumberAxis x;
    @FXML
    private CategoryAxis y;
    @FXML
    private TextField fd;
    @FXML
    private Button b2;
        @FXML
    private AnchorPane recpane;

    /**
     * Initializes the controller class.
     */
    
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ReclamationServices rs = new ReclamationServices();
        Reclamation r = new Reclamation();
        BarChart.Series set1 = new BarChart.Series<>();
        
        fd.setText("vous avez "+rs.getNbrReclamation()+"reclamation") ;
       
       
        set1.getData().add(new BarChart.Data("1",rs.getNbrReclamation()));
        
        rt.getData().addAll(set1);
        // TODO
    }    

    @FXML
    private void back1(ActionEvent event) throws IOException {
        Stage stage = (Stage) recpane.getScene().getWindow();
        stage.close();
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/Reclamation.fxml"));
           
        
        Stage stageNew = new Stage();
        stageNew.setTitle("Réclamation");
        stageNew.setScene(new Scene(pane, 1430, 735));
        stageNew.show();
    }
   
}
