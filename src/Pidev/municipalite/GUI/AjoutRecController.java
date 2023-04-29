/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import javafx.util.StringConverter;
import javax.mail.MessagingException;
import javax.swing.JOptionPane;
import org.controlsfx.control.Notifications;
import service.ReclamationServices;
import service.TypeServices;

/**
 * FXML Controller class
 *
 * @author hamza
 */
public class AjoutRecController implements Initializable {

    @FXML
    private AnchorPane recpane;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private TextField id_nom;
    @FXML
    private Label date;
    @FXML
    private TextField id_prenom;
    @FXML
    private Label tel;
    @FXML
    private Label email;
    @FXML
    private Label desc;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_description;
    @FXML
    private TextField id_tel;
    @FXML
    private DatePicker dpdate;
    Notifications no;
    String erreur;
    ReclamationServices rs = new ReclamationServices();
    @FXML
    private ComboBox<Type> combo1;
    @FXML
    private Label email1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeServices us = new TypeServices();
        List<Type> types = us.afficherType();
        combo1.getItems().addAll(types);
        combo1.setConverter(new StringConverter<Type>() {
            @Override
            public String toString(Type type) {
                return type.getType();
            }

            @Override
            public Type fromString(String string) {
                return combo1.getItems().stream().filter(type -> type.getType().equals(string)).findFirst().orElse(null);
            }
        });

    }

    @FXML
    private void ajouterReclamation(ActionEvent event) throws MessagingException {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Add", "Add", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            StringBuilder errors = new StringBuilder();
            if (id_nom.getText().trim().isEmpty()) {
                errors.append("- Please enter un Nom\n");
            }
            if (id_prenom.getText().trim().isEmpty()) {
                errors.append("- Please enter un Prenom\n");
            }
            if (id_email.getText().trim().isEmpty()) {
                errors.append("- Please enter un Email\n");
            }
            if (pat.matcher(id_email.getText()).matches() == false) {

                errors.append("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");

            }

            if (id_description.getText().trim().isEmpty()) {
                errors.append("- Please enter un Description\n");
            }

            if (id_tel.getText().trim().length() != 8) {
                errors.append("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
            }

            if (dpdate.getValue() == null) {
                errors.append("- Please enter a Date\n");
            }
            try {
                Integer.parseInt(id_tel.getText());
            } catch (NumberFormatException e) {
                errors.append("- Please enter a valid number\n");
            }

            if (errors.length() > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Errors");
                alert.setContentText(errors.toString());
                alert.showAndWait();
            } else {

                Reclamation r = new Reclamation();

                r.setTel(Integer.parseInt(id_tel.getText()));
                r.setNom(id_nom.getText());
                r.setPrenom(id_prenom.getText());
                r.setEmail(id_email.getText());
                r.setDescription(id_description.getText());
                Type selectedType = combo1.getValue();
                r.setType_id(selectedType);
                r.setEtat("Encours");
                r.setDate_reclamation(java.sql.Date.valueOf(dpdate.getValue()));
                rs.ajouterReclamation(r);
                no = Notifications.create()
                        .title("Reclamation Ajouter")
                        .text(erreur)
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(6));
                no.showInformation();

            }

        }
    }

}
