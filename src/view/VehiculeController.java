/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import javafx.collections.transformation.FilteredList;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Categorie;
import entities.Vehicule;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.I;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ServiceCat;
import services.ServiceVt;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Amirov
 */
public class VehiculeController implements Initializable {

    private TextField catid;
    @FXML
    private TextField mq;
    private TextField dp;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TableColumn<Vehicule, String> idc;
    @FXML
    private TableColumn<Vehicule, String> marq;
    @FXML
    private TableColumn<Vehicule, String> disp;
    @FXML
    private TableView<Vehicule> affiche;

    ServiceVt service = new ServiceVt();

    Vehicule v = new Vehicule();
    @FXML
    private ComboBox<Categorie> combo1;
    @FXML
    private ComboBox<String> combo2;
    @FXML
    private TextField filterFiled;
    ObservableList<Vehicule> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
      ServiceVt rs = new ServiceVt();
    @FXML
    private Button btntrier;

    public void refreshTable() {
  data.clear();
   data = FXCollections.observableArrayList(rs.affichervt());


        //idv.setCellValueFactory(new PropertyValueFactory<>("id"));
        idc.setCellValueFactory(new PropertyValueFactory<>("Labelcat"));
        marq.setCellValueFactory(new PropertyValueFactory<>("marque"));
        disp.setCellValueFactory(new PropertyValueFactory<>("disponible"));

        //disp.setCellFactory(Boolean.TRUE);
        affiche.setItems(data);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceCat us = new ServiceCat();
        combo1.setItems(FXCollections.observableArrayList(us.afficherCatLable()));

        combo2.setItems(FXCollections.observableArrayList("true", "false"));
        // TODO
        // refreshTable();
    }

    @FXML
    private void supp(ActionEvent event) {

        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();

        service.supprimervl(vehicule.getId());
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule supprimer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        refreshTable();

    }

    @FXML
    private void ajt(ActionEvent event) {

        v.setMarque(mq.getText());
        if (String.valueOf(combo2.getValue()).equalsIgnoreCase("true")) {
            v.setDisponible(1);
        } else {
            v.setDisponible(0);
        }
        StringBuilder errors = new StringBuilder();
        if (mq.getText().trim().isEmpty()) {
            errors.append("svp entrer une marque \n");
        }

        if (errors.length() > 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }

        v.setLabelcat(String.valueOf(combo1.getValue()));
        service.ajoutervt(v);
Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule ajouter avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        refreshTable();
    }

    @FXML
    private void modifier(ActionEvent event) {
        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();
        v.setId(vehicule.getId());
        v.setMarque(mq.getText());
        if (String.valueOf(combo2.getValue()).equalsIgnoreCase("true")) {
            v.setDisponible(1);
        } else {
            v.setDisponible(0);
        }

        v.setLabelcat(String.valueOf(combo1.getValue()));

        service.updateVehicule(v);
        Notifications notificationBuilder = Notifications.create().title("Alert").text("Vehicule modifier avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
refreshTable();
    }

    @FXML
    private void rtr(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("./Categorie.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void display(ActionEvent event) {
        refreshTable();
    }

    @FXML
    private void select(ActionEvent event) throws SQLException {
        ServiceCat sc = new ServiceCat();

        Vehicule vehicule = affiche.getSelectionModel().getSelectedItem();
        mq.setText(vehicule.getMarque());
        combo1.setValue(sc.afficheCat(vehicule.getLabelcat()));
    }

    @FXML
private void pdf(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {
    Vehicule r = new Vehicule();
    Document doc = new Document();
    PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Ala\\Desktop\\pdf\\ListeVehicule.pdf"));
    doc.open();
    
    // Add title
    Paragraph p = new Paragraph("Liste des véhicules");
    p.setAlignment(Element.ALIGN_CENTER);
    p.setSpacingAfter(20);
    doc.add(p);
    
    // Add image
    Image img = null;
    try {
        img = Image.getInstance(new URL("file:///C:/Users/Ala/Desktop/Nouveau dossier/image.png"));
    } catch (IOException e) {
        System.err.println("An error occurred while loading the image: " + e.getMessage());
    }
    if (img != null) {
        img.scaleToFit(500, 500);
        img.setAlignment(Element.ALIGN_CENTER);
        doc.add(img);
    }
    
    // Add table
    PdfPTable table = new PdfPTable(3);
    table.setWidthPercentage(100);
    table.setSpacingBefore(20);
   
    
    PdfPCell c = new PdfPCell(new Phrase("Marque"));
    c.setBackgroundColor(new BaseColor(102, 178, 255)); // couleur bleue
    c.setHorizontalAlignment(Element.ALIGN_CENTER);
    c.setBorderColor(new BaseColor(0, 153, 51)); // couleur verte pour la bordure
    table.addCell(c);
    
    c = new PdfPCell(new Phrase("Catégorie"));
    c.setBackgroundColor(new BaseColor(102, 178, 255)); // couleur bleue
    c.setHorizontalAlignment(Element.ALIGN_CENTER);
    c.setBorderColor(new BaseColor(0, 153, 51)); // couleur verte pour la bordure
    table.addCell(c);
    
    c = new PdfPCell(new Phrase("Disponible"));
    c.setBackgroundColor(new BaseColor(102, 178, 255)); // couleur bleue
    c.setHorizontalAlignment(Element.ALIGN_CENTER);
    c.setBorderColor(new BaseColor(0, 153, 51)); // couleur verte pour la bordure
    table.addCell(c);
    
    Connection cnx = MyDB.getInstance().getcnx();
    String query = "SELECT * FROM `vehicule`";
    PreparedStatement smt = cnx.prepareStatement(query);
    ResultSet rs = smt.executeQuery();
    
    while (rs.next()) {
        String marque = rs.getString("marque");
        String categorie = rs.getString("categorie_id");
        String disponible = rs.getString("disponible");

        PdfPCell  cellMarque = new PdfPCell(new Phrase(marque));
        cellMarque.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellMarque.setBorderColor(new BaseColor(0, 153, 51)); // couleur verte pour la bordure
        table.addCell(cellMarque);
        
        PdfPCell cellCategorie = new PdfPCell(new Phrase(categorie));
        cellCategorie.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellCategorie.setBorderColor(new BaseColor(0, 153, 51)); // couleur verte pour la bordure
        table.addCell(cellCategorie);
        
        PdfPCell cellDisponible = new PdfPCell(new Phrase(disponible));
        cellDisponible.setHorizontalAlignment(Element.ALIGN_CENTER);
        cellDisponible.setBorderColor(new BaseColor(0, 153, 51));           
    }
    
    Notifications notificationBuilder = Notifications.create().title("Alert").text("pdf telechargé").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
    notificationBuilder.darkStyle();
    notificationBuilder.show();
    
    doc.add(table);
    doc.close();
}

   @FXML
   public void chercher() {
    ServiceVt rs = new ServiceVt();
    List<Vehicule> results = rs.affichervt();

    FilteredList<Vehicule> filteredData = new FilteredList<>(data, b -> true);

    filterFiled.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(vehicule -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }

            String lowerCaseFilter = newValue.toLowerCase();

            return vehicule.getMarque().toLowerCase().indexOf(lowerCaseFilter) != -1;
        });
    });

    // 3. Wrap the FilteredList in a SortedList. 
    SortedList<Vehicule> sortedData = new SortedList<>(filteredData);

    // 4. Bind the SortedList comparator to the TableView comparator.
    //    Otherwise, sorting the TableView would have no effect.
    sortedData.comparatorProperty().bind(affiche.comparatorProperty());

    // 5. Add sorted (and filtered) data to the table.
    affiche.setItems(sortedData);

    // Alternative implementation using stream
    // Replace the for loop in affichervt() method with stream

    List<Vehicule> vehicules = rs.affichervt().stream().filter(vehicule ->
            vehicule.getMarque().toLowerCase().contains(filterFiled.getText().toLowerCase()))
            .collect(Collectors.toList());

    affiche.setItems(FXCollections.observableArrayList(vehicules));


    }

   @FXML
private void affichrtTri(ActionEvent event) {
    ServiceVt rs = new ServiceVt();
    List<Vehicule> l = rs.affichervt();
    ObservableList<Vehicule> data = FXCollections.observableArrayList(l);
    data = data.stream()
               .sorted(Comparator.comparing(Vehicule::getMarque, String.CASE_INSENSITIVE_ORDER)
                                 .thenComparing(Vehicule::getMarque))
               .collect(Collectors.toCollection(FXCollections::observableArrayList));
    FilteredList<Vehicule> fle = new FilteredList<>(data, e -> true);
    affiche.setItems(fle);
}
    @FXML
 private void qr(ActionEvent event) throws com.google.zxing.WriterException {
    Vehicule selectedVehicule = affiche.getSelectionModel().getSelectedItem(); // Récupérer la catégorie sélectionnée dans la TableView
    if (selectedVehicule == null) {
        // Aucune catégorie sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune catégorie sélectionnée");
        alert.setContentText("Veuillez sélectionner une Véhicule dans la liste.");
        alert.showAndWait();
        return;
    }

    // Générer le code QR pour la catégorie
    String qrText = "Marque: " + selectedVehicule.getMarque() + "\n\n" +
            "Disponibilité: " + selectedVehicule.getDisponible()+ "\n\n" +
                    "Categorie: " + selectedVehicule.getLabelcat();
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    ByteMatrix byteMatrix;
    byteMatrix = qrCodeWriter.encode(qrText, BarcodeFormat.QR_CODE, 200, 200); // Erreur lors de la génération du code QR, afficher un message d'erreur

    // Convertir la matrice de bytes en une image BufferedImage
    int width = byteMatrix.getWidth();
    int height = byteMatrix.getHeight();
    BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            int grayValue = byteMatrix.get(x, y) & 0xFF;
            qrImage.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
        }
    }

    // Afficher l'image dans une nouvelle fenêtre
    ImageView imageView = new ImageView(SwingFXUtils.toFXImage(qrImage, null));
    imageView.setPreserveRatio(true);
    imageView.setFitWidth(400);
    Stage qrStage = new Stage();
    qrStage.setTitle("Code QR pour Nos Véhicules" );
    qrStage.setScene(new Scene(new StackPane(imageView), 400, 400));
    qrStage.show();
}



}
