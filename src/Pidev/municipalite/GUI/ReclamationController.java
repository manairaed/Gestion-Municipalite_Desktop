/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import chat.ClientController;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;

import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import entities.Reclamation;
import service.ReclamationServices;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import tools.MaConnexion;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.net.URL;
import java.util.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;
import javafx.util.Duration;
import javax.mail.MessagingException;
import org.controlsfx.control.Notifications;
import tools.MailAPI;

/**
 * FXML Controller class
 *
 * @author AMEN
 */
public class ReclamationController implements Initializable {

    @FXML
    private TableView<Reclamation> tableviewreclamation;

    @FXML
    private TextField id_nom;

    private TextField search;
    @FXML
    private TextField id_tel;
    @FXML
    private TextField id_prenom;
    @FXML
    private TextField id_email;
    @FXML
    private TextField id_description;
    @FXML
    private TextField id_etat;
    private AnchorPane chartNode;
    public static int numeroPDF = 0;
    Document doc = new Document();

    public static long id_user;

    @FXML
    private DatePicker dpdate;
    private Stage primaryStage;
    private TextField recherchetf;
    @FXML
    private AnchorPane recpane;
    @FXML
    private ComboBox<String> combotri;
    @FXML
    private Button statBtn;

    @FXML
    private TextField filterField;
    private ImageView screenshotView;
    File selectedFile;
    Notifications no;
    String erreur;
    @FXML
    private Button btntri;

    ObservableList<Reclamation> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();

    ReclamationServices rs = new ReclamationServices();
    private TableColumn<Reclamation, Integer> id1;

    @FXML
    private TableColumn<Reclamation, Integer> t;
    @FXML
    private TableColumn<Reclamation, String> p;
    @FXML
    private TableColumn<Reclamation, String> e;
    @FXML
    private TableColumn<Reclamation, String> d;
    private TableColumn<Reclamation, String> ty;
    @FXML
    private TableColumn<Reclamation, String> et;
    @FXML
    private TableColumn<Reclamation, Date> da;
    @FXML
    private TableColumn<Reclamation, String> n;
    @FXML
    private TableColumn<Reclamation, Integer> type;

    long id_modif;
    String nom_modif;

    int selectedCompteID;
    static Reclamation selectionedReclamation;
    java.sql.Timestamp timestamp = null;
    private PreparedStatement pst = null;
    private ImageView nomCheckMark;
    private ImageView telCheckMark;
    private ImageView prenomCheckMark;
    private ImageView emailCheckMark;
    private JFXTimePicker TempsDispoTimePicker;
    private JFXDatePicker DateDispoTimePicker;
    private ImageView dateCheckMark;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private Label tel;

    @FXML
    private Label email;
    @FXML
    private Label desc;
    @FXML
    private Button r;
    @FXML
    private Button rrrr;
    @FXML
    private Button BtnChat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ss.add("Par date");
        ss.add("Par nom");

        // TODO
        combotri.setItems(ss);

        try {
            initialiserlist();

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        refreshlist();

    }

    @FXML
    private void getSelected(javafx.scene.input.MouseEvent event) throws SQLException {

        int index = tableviewreclamation.getSelectionModel().getSelectedIndex();
        if (index <= -1) {

            return;
        }

        Connection cnx = MaConnexion.getInstance().getCnx();

        //ResultSet rsd =null ;
        TableColumn.CellEditEvent edittedcell = null;
        Reclamation r = gettempReclamation(edittedcell);
        //date to localdate
        Instant instant = Instant.ofEpochMilli(r.getDate_reclamation().getTime());
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        id_tel.setText(t.getCellData(index).toString());
        id_nom.setText(n.getCellData(index).toString());
        id_prenom.setText(p.getCellData(index).toString());
        id_email.setText(e.getCellData(index).toString());
        id_description.setText(d.getCellData(index).toString());

//       id_etat.setText(et.getCellData(index));
        dpdate.setValue(ldt.toLocalDate());

    }

    public void refreshlist() {
        data.clear();
        data = FXCollections.observableArrayList(rs.afficherReclamation());

        t.setCellValueFactory(new PropertyValueFactory<>("tel"));
        n.setCellValueFactory(new PropertyValueFactory<>("nom"));
        p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        e.setCellValueFactory(new PropertyValueFactory<>("email"));
        d.setCellValueFactory(new PropertyValueFactory<>("description"));

        et.setCellValueFactory(new PropertyValueFactory<>("etat"));
        da.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));

        tableviewreclamation.setItems(data);
    }

    public void initialiserlist() throws SQLException {
        try {
            Connection cnx = MaConnexion.getInstance().getCnx();
            ResultSet rs = cnx.createStatement().executeQuery("SELECT * FROM reclamation");

        } catch (SQLException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Boolean testMail() {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (id_email.getText() == null) {
            return false;
        }

        if (pat.matcher(id_email.getText()).matches() == false) {
            emailCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Veuillez verifier votre adresse email\n");
            return false;
//            

        } else {
            emailCheckMark.setImage(new Image("Images/checkMark.png"));
        }
        return true;

    }

    //   
    private Boolean testTel() {
        if (id_tel.getText().trim().length() == 8) {
            int nbChar = 0;
            for (int i = 1; i < id_tel.getText().trim().length(); i++) {
                char ch = id_tel.getText().charAt(i);
                if (Character.isLetter(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
                telCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (id_email.getText().trim().length() != 8) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            telCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            return false;
        }

        return true;

    }

    private Boolean testDate() {
        java.sql.Timestamp today_date = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() != null) {
            timestamp = java.sql.Timestamp.valueOf(DateDispoTimePicker.getValue() + " " + TempsDispoTimePicker.getValue() + ":00");
            if (timestamp.compareTo(today_date) > 0) {
                dateCheckMark.setImage(new Image("Images/checkMark.png"));
                return true;
            } else {
                dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
            }
            return false;
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() == null) {
            return true;
        } else if (TempsDispoTimePicker.getValue() != null && DateDispoTimePicker.getValue() == null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        } else if (TempsDispoTimePicker.getValue() == null && DateDispoTimePicker.getValue() != null) {
            dateCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
        }
        return false;

    }

    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < id_nom.getText().trim().length(); i++) {
            char ch = id_nom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_nom.getText().trim().length() >= 3) {
            nomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            nomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testPrenom() {
        int nbNonChar = 0;
        for (int i = 1; i < id_prenom.getText().trim().length(); i++) {
            char ch = id_prenom.getText().charAt(i);
            if (!Character.isLetter(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && id_prenom.getText().trim().length() >= 3) {
            prenomCheckMark.setImage(new Image("Images/checkMark.png"));
            return true;
        } else {
            prenomCheckMark.setImage(new Image("Images/erreurcheckMark.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testSaisie() {
        erreur = "";
        if (!testMail()) {
            erreur = erreur + ("Veuillez verifier que votre adresse email est de la forme : ***@***.** \n");
        }
        if (!testTel()) {
            erreur = erreur + ("Telephone doit avoir 8 chiffres et ne doit pas contenir des caracteres \n");
        }
        if (!testDate()) {
            erreur = erreur + ("Veuillez verifier que vous avez choisi une date superieur a la date courante \n");
        }

        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testPrenom()) {
            erreur = erreur + ("Veuillez verifier votre Prenom: seulement des caractères et de nombre >= 3");
        }

        if (!testMail() || !testTel() || !testDate() || !testNom() || !testPrenom()) {
            no = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
        }

        return testMail() && testTel() && testDate() && testNom() && testPrenom();
    }

    @FXML
    private void modifierReclamation(ActionEvent event) throws SQLException {
        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Modify", "Modify", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            // Récupérer l'objet Reclamation sélectionné
            Reclamation r = tableviewreclamation.getSelectionModel().getSelectedItem();

            // Modifier les champs de la réclamation avec les nouvelles valeurs
            r.setNom(id_nom.getText()); // replace id_nom_label with the appropriate element
            r.setPrenom(id_prenom.getText());
            r.setEmail(id_email.getText());
            try {
                r.setTel(Integer.parseInt(id_tel.getText()));
            } catch (NumberFormatException e) {
                // handle the exception here (e.g. show an error message)
                return;
            }
            r.setEtat("Traite");
            r.setDescription(id_description.getText());
            LocalDate localDate = dpdate.getValue();
            java.sql.Date date = java.sql.Date.valueOf(localDate);
            r.setDate_reclamation(date);
            rs.modifierReclamation(r);

            // Mettre à jour la ligne sélectionnée dans le TableView
            tableviewreclamation.refresh();

            // Afficher une confirmation
            no = Notifications.create()
                    .title("Reclamation Modifier")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            no.showInformation();
        }
    }

    public Reclamation gettempReclamation(TableColumn.CellEditEvent edittedCell) {
        Reclamation test = tableviewreclamation.getSelectionModel().getSelectedItem();
        return test;
    }

    @FXML
    public void supprimer(ActionEvent event) throws SQLException {
        //viewProduit.getItems().removeAll(viewProduit.getSelectionModel().getSelectedItem());

        int p = JOptionPane.showConfirmDialog(null, "Do you really want to Delete", "Delete", JOptionPane.YES_NO_OPTION);
        if (p == 0) {
            TableColumn.CellEditEvent edittedcell = null;
            Reclamation r = gettempReclamation(edittedcell);

            if (r != null) {

                int i = r.getId();
                ReclamationServices cat = new ReclamationServices();

                int s = cat.supprimerreclamation(i);
                if (s == 1) {
                    data.clear();
                    initialiserlist();
                    refreshlist();
                    tableviewreclamation.refresh();

                    id_tel.setText("");
                    id_nom.setText("");
                    id_prenom.setText("");
                    id_email.setText("");
                    id_description.setText("");

                    id_etat.setText("");

                    no = Notifications.create()
                            .title("Reclamation Supprimer")
                            .text(erreur)
                            .graphic(null)
                            .position(Pos.TOP_CENTER)
                            .hideAfter(Duration.seconds(6));
                    no.showInformation();

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
    public void chercher() {
        ReclamationServices rs = new ReclamationServices();
        List<Reclamation> results = new ArrayList<>();
        results = rs.afficherReclamation();

        // 1. Create a stream from the results list.
        Stream<Reclamation> reclamationStream = results.stream();

        FilteredList<Reclamation> filteredData = new FilteredList<>(
                FXCollections.observableList(results),
                reclamation -> true
        );
        Reclamation r = new Reclamation();

        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(reclamation -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // 2. Use Stream operations to filter the data.
                return reclamation.getNom().toLowerCase().contains(lowerCaseFilter) || String.valueOf(r.getTel()).contains(lowerCaseFilter);
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Reclamation> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(tableviewreclamation.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableviewreclamation.setItems(sortedData);
    }

    private void qq(KeyEvent event) {
        try {
            ReclamationServices rs = new ReclamationServices();
            List<Reclamation> listReclamation = rs.ReclamationParId(search.getText());
            data.clear();
            if (!listReclamation.isEmpty()) {
                for (int i = 0; i < listReclamation.size(); i++) {
                    data.add(listReclamation.get(i));
                    System.out.println(listReclamation.get(i));
                }
            }

            t.setCellValueFactory(new PropertyValueFactory<>("tel"));
            n.setCellValueFactory(new PropertyValueFactory<>("nom"));
            p.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            e.setCellValueFactory(new PropertyValueFactory<>("email"));
            d.setCellValueFactory(new PropertyValueFactory<>("description"));
            ty.setCellValueFactory(new PropertyValueFactory<>("type"));
            et.setCellValueFactory(new PropertyValueFactory<>("etat"));
            da.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
            type.setCellValueFactory(new PropertyValueFactory<>("type_id"));

            tableviewreclamation.setItems(data);

        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void print(ActionEvent event) {

        System.out.println("To Printer!");
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            Window primaryStage = null;
            job.showPrintDialog(this.primaryStage);

            Node root = this.tableviewreclamation;
            job.printPage(root);
            job.endJob();
        }

    }

    @FXML
    private void afficherStatistique(ActionEvent event) {

        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/statistics.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Reclamation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void Statistic(ActionEvent event) {

        try {
            Stage stageclose = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stageclose.close();
            Parent root = FXMLLoader.load(getClass().getResource("/GUI/stat.fxml"));
            Stage stage = new Stage();

            Scene scene = new Scene(root);

            stage.setTitle("Reclamation");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

 @FXML

private void trilist(ActionEvent event) {
    ObservableList<Reclamation> triList = FXCollections.observableArrayList();

    if (combotri.getValue().equals("Par nom")) {
        triList = FXCollections.observableArrayList(rs.afficherReclamation().stream()
                .sorted(Comparator.comparing(Reclamation::getNom))
                .collect(Collectors.toList()));
    } else if (combotri.getValue().equals("Par date")) {
        triList = FXCollections.observableArrayList(rs.afficherReclamation().stream()
                .sorted(Comparator.comparing(Reclamation::getDate_reclamation))
                .collect(Collectors.toList()));
    }

    tableviewreclamation.setItems(triList);
}

    @FXML
    private void back(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/GUI/menu.fxml"));
        recpane.getChildren().setAll(pane);
    }

    @FXML
    private void msg(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/Client.fxml"));
        Parent root;
        try {

            root = loader.load();
            ClientController c1 = loader.getController();
            BtnChat.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML

    private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException {

        Reclamation selectedReclamation = tableviewreclamation.getSelectionModel().getSelectedItem(); // Récupérer la catégorie sélectionnée dans la TableView
        if (selectedReclamation == null) {
            // Aucune catégorie sélectionnée, afficher un message d'erreur
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucune catégorie sélectionnée");
            alert.setContentText("Veuillez sélectionner une Véhicule dans la liste.");
            alert.showAndWait();
            return;
        }

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\hamza\\Desktop\\PDF\\ListeReclamation.pdf"));
            document.open();
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL);

            // Créer un Chunk pour le texte "Réclamation" en rouge
            Chunk chunk = new Chunk("Réclamation", FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, BaseColor.RED));

            // Créer un objet Phrase qui contient le Chunk
            Phrase phrase = new Phrase(chunk);

            // Ajouter le Phrase à un Paragraph
            Paragraph titre = new Paragraph(phrase);
            titre.setAlignment(Element.ALIGN_CENTER);
            titre.setSpacingAfter(50);
            document.add(titre);

            Paragraph nom = new Paragraph("Nom : " + selectedReclamation.getNom());
            nom.setSpacingAfter(25);
            document.add(nom);

            Paragraph prenom = new Paragraph("Prénom : " + selectedReclamation.getPrenom());
            prenom.setSpacingAfter(25);
            document.add(prenom);

            Paragraph email = new Paragraph("Email : " + selectedReclamation.getEmail());
            email.setSpacingAfter(25);
            document.add(email);

            Paragraph tel = new Paragraph("Téléphone : " + selectedReclamation.getTel());
            tel.setSpacingAfter(25);
            document.add(tel);

            Paragraph description = new Paragraph("Description : " + selectedReclamation.getDescription());
            description.setSpacingAfter(25);
            document.add(description);

            Paragraph date = new Paragraph("Date de réclamation : " + selectedReclamation.getDate_reclamation());
            date.setSpacingAfter(25);
            document.add(date);

            Paragraph etat = new Paragraph("Etat : " + selectedReclamation.getEtat());
            etat.setSpacingAfter(25);
            document.add(etat);

        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } finally {
            // Close the document
            document.close();
        }
    }

    private void addTableHeader(PdfPTable table, String headerText) {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.RED);
        header.setBorderWidth(1);
        header.setPhrase(new Phrase(headerText));
        table.addCell(header);
    }

    private void addTableRow(PdfPTable table, String cellText) {
        PdfPCell cell = new PdfPCell();
        cell.setBorderWidth(1);
        cell.setPhrase(new Phrase(cellText));
        table.addCell(cell);
    }

    @FXML
    private void mail(ActionEvent event) throws MessagingException {
        MailAPI.send("tmunicipalite@gmail.com", "Hamzarh14453949", "hamzarahmouni068@gmail.com", "Reclamation", " Votre Reclamation est en cours de traitement ");
        no = Notifications.create()
                .title("Mail Envoyer")
                .text(erreur)
                .graphic(null)
                .position(Pos.TOP_CENTER)
                .hideAfter(Duration.seconds(6));
        no.showInformation();
    }

}
