/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ReclamationServices;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class StatisticController implements Initializable {

    @FXML
    private PieChart statistique;
    @FXML
    private Button b2;
    private AnchorPane recpane;
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           int total = 0;
ReclamationServices rs = new ReclamationServices();



int encours = rs.nbEncours();
int traite = rs.nbTraité();
total = encours + traite;

double prcntFeed = (encours * 100) / total;
double prcntRec = (traite * 100) / total;

ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
      new PieChart.Data("Encours : " +rs.nbEncours() + "reclamation",encours),
        new PieChart.Data("Traite : " + rs.nbTraité() + "reclamation",traite)
);


statistique.setTitle("Etat des demandes");
statistique.setAnimated(true);
statistique.setData(list);
    }    

    @FXML
    private void back1(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
        recpane.getChildren().setAll(pane);
          
    }
    
}
