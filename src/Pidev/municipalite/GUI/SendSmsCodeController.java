/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Pidev.municipalite.GUI;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * FXML Controller class
 *
 * @author raedm
 */
public class SendSmsCodeController implements Initializable {

    @FXML
    private TextField numeroTextfield;
    @FXML
    private Button btn_envoyer;
    @FXML
    private TextField codeTextfield1;
    @FXML
    private Button btn_verifier;
    @FXML
    private ImageView back;
    @FXML
    private Label labelNumero;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visibility(true, false);
    }   
    public void visibility(boolean phase1, boolean phase2) {
        labelNumero.setVisible(phase1);
        numeroTextfield.setVisible(phase1);
        btn_envoyer.setVisible(phase1); // fin phase 1
        
        codeTextfield1.setVisible(phase2);
        btn_verifier.setVisible(phase2); // fin phase 2
    }
    
    Random rand = new Random();
       int codeVerification = rand.nextInt((1000) + (9999));
       String codeVerif = String.valueOf(codeVerification);
       
    @FXML
    private void SendSmsExample(ActionEvent event) {
        
       
        
        try {
        // Remplacer "YOUR_API_KEY" par votre clé API Textlocal
        String apiKey = "NmE1OTYyNDI2NDU0NmI0OTMwMzQ0NDU3NzE0YTUxNmQ=";

        // Remplacer "YOUR_MESSAGE" par le message à envoyer
        String message = "Votre code de vérification est : " + codeVerif;

        // Construire l'URL de l'API avec les paramètres nécessaires
        String apiUrl = "https://api.textlocal.in/send/?apikey=" + apiKey + "&numbers=" +  numeroTextfield.getText() + "&message=" + message;
        System.out.println(numeroTextfield.getText()+" "+codeVerif);
         visibility(false, true);
         
        // Désactiver la vérification SSL
    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return null;
        }
        public void checkClientTrusted(X509Certificate[] certs, String authType) {
        }
        public void checkServerTrusted(X509Certificate[] certs, String authType) {
        }
    } };
    SSLContext sc = SSLContext.getInstance("SSL");
    sc.init(null, trustAllCerts, new java.security.SecureRandom());
    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        // Créer une connexion HTTP
        URL url = new URL(apiUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // Envoyer la requête HTTP GET
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", "application/json");

        // Lire la réponse de l'API
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        // Afficher la réponse de l'API
        System.out.println(response.toString());
    } catch (Exception e) {
        System.out.println("Erreur lors de l'envoi du SMS : " + e.getMessage());
    }
}
    


    @FXML
    private void Verifier(ActionEvent event) {
        String codeV = codeTextfield1.getText();
        if (!codeV.equals(this.codeVerif)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code invalide !");
            alert.showAndWait();
        } else {
           try{
                            Stage stage = (Stage) btn_verifier.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceAdmin.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }
        }
    }

    @FXML
    private void GoToLogin(MouseEvent event) {
    }
    
}
