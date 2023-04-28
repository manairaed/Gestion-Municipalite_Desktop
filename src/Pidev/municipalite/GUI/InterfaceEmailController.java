
package Pidev.municipalite.GUI;

import Pidev.municipalite.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class InterfaceEmailController implements Initializable {

    @FXML
    private TextField emailTextField;
    @FXML
    private Button btn_verif;
    @FXML
    private TextField codetextField;
    @FXML
    private Button btn_valider;
    @FXML
    private Label labelEmail;
    @FXML
    private Label labelCode;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        visibility(true, false);
    }    
    
    public void visibility(boolean phase1, boolean phase2) {
        labelEmail.setVisible(phase1);
        emailTextField.setVisible(phase1);
        btn_verif.setVisible(phase1); // fin phase 1
        labelCode.setVisible(phase2);
        codetextField.setVisible(phase2);
        btn_valider.setVisible(phase2); // fin phase 2
    }
    
       Random rand = new Random();
       int codeVerification = rand.nextInt((1000) + (9999));
       String codeVerif = String.valueOf(codeVerification);

    @FXML
    private void Verifier(ActionEvent event) throws SQLException, AddressException, MessagingException{
        
        String mail = emailTextField.getText();
       ServiceUser us = new ServiceUser();
       /*if (emailTextField.getText().isEmpty()|| !emailTextField.getText().contains("@")|| !emailTextField.getText().contains(".")){
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Email invalide !");
            alert.showAndWait();
       }
       else if (!us.verifierEmailBd(mail)) {
           Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Verifier adresse");
            alert.setHeaderText("Veuillez saisir une adresse mail valide");
        }*/
       /*else  {*/
           
           
           
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.outlook.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            
            final String username = "municipalitetunis@outlook.fr";
            final String password = "pidev123";
            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
             try {
                    // Création du message
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
                    message.setSubject("Code de vérification Municipalite");
                    message.setText("Nous avons reçu une demande de réinitialisation du mot de passe de votre compte Municipalite. \n"
                         +  "\n" 
                         +"Pour terminer le processus de réinitialisation du mot de passe, veuillez utiliser le code de vérification suivant "
                         +  "\n" 
                         + " Votre code de vérification est : " + codeVerif);

                    // Envoi du message
                    Transport.send(message);
                    System.out.println("message envoyer avec succes");
                    System.out.println(codeVerif);
                    visibility(false, true);
                    
                    /*try{
                            Stage stage = (Stage) btn_verif.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("InterfaceVerifCode.fxml"));
                            Scene scene = new Scene(root );
                            stage.setScene(scene);
                            stage.setResizable(false);
                            stage.show();
                    }catch(IOException ex){
                            System.out.println(ex.getMessage());
                    }*/
              
            } catch (MessagingException e) {
                    // Gestion des exceptions
                    System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
                    
            }
            
          /*}*/
    }
    
    @FXML
    private void VerifCode(ActionEvent event) {
       String codeV = codetextField.getText();
        if (!codeV.equals(this.codeVerif)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Code invalide !");
            alert.showAndWait();
        } else {
           try{
                            Stage stage = (Stage) btn_valider.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
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
    private void goToLogin(MouseEvent event) {
          try{
                            Stage stage = (Stage) btn_verif.getScene().getWindow();
                            Parent root =FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root );
            stage.setScene(scene);
            stage.setResizable(false);
                    stage.show();
        }catch(IOException ex){
                            System.out.println(ex.getMessage());
        }
    }

    
    
}
