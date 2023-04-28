/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
        

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.ByteMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.encoder.Encoder;
import com.google.zxing.qrcode.encoder.QRCode;
import com.google.zxing.qrcode.decoder.Version;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import com.itextpdf.text.pdf.qrcode.BitMatrix;
import com.itextpdf.text.pdf.qrcode.WriterException;
import entities.Categorie;
import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.ServiceCat;
import utils.MyDB;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;



/**
 * FXML Controller class
 *
 * @author Ala
 */
public class CategorieController implements Initializable {

   
    @FXML
    private TextField lb;
    @FXML
    private Button su;
    @FXML
    private Button aj;
    @FXML
    private Button mod;
    @FXML
    private TableColumn<Categorie, String> lbc;
    @FXML
    private TableView<Categorie> affiche;
    @FXML
    private Button excel;
        ObservableList<Categorie> data = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
      ServiceCat rs = new ServiceCat();
    @FXML
    private Button btntrier;
    
    
    
    
    public void refreshTable() {
        ServiceCat us = new ServiceCat();
    List<Categorie> l = new ArrayList<>();
    l = (ArrayList<Categorie>) us.affichercat();
    ObservableList<Categorie> data = FXCollections.observableArrayList(l);
    FilteredList<Categorie> fle = new FilteredList(data, e -> true);
   // idc.setCellValueFactory(new PropertyValueFactory<>("id"));
    lbc.setCellValueFactory(new PropertyValueFactory<>("labelcat"));
    

    affiche.setItems(fle);
    

}

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        refreshTable();
    }    

    @FXML
    private void supp(ActionEvent event) throws IOException {
        
        
        Categorie categorie = affiche.getSelectionModel().getSelectedItem();
        
         ServiceCat sm = new ServiceCat() ;   

            

      StringBuilder errors=new StringBuilder();
        
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     else{
                   sm.supprimercat(categorie.getId());

      Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Categorie is deleted successfully!");
            alert.show(); 
            Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie supprimer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
            
            refreshTable();
     }
    }


    @FXML
    private void ajt(ActionEvent event) throws IOException {
         ServiceCat sm = new ServiceCat() ;   
    Categorie c = new Categorie() ;
           
     
        StringBuilder errors=new StringBuilder();
        
        if(lb.getText().trim().isEmpty()){
            errors.append("svp entrer un label de categorie\n");
        }
     
     if(errors.length()>0){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(errors.toString());
            alert.showAndWait();
        }
     else{
     
     Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setContentText("Cours is added successfully!");
            alert.show(); 
            
      
      c.setLabelcat(lb.getText());
      

         
            sm.ajoutercat(c);
            Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie ajouter avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                       
            refreshTable();
     }
    }
     @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException {
        
         Categorie categorie = affiche.getSelectionModel().getSelectedItem();
        
        
    Categorie c = new Categorie();
    
    StringBuilder errors = new StringBuilder();
        
        
    // Vérifier si le champ nom est vide ou a une taille incorrecte
    if(lb.getText().trim().isEmpty() || lb.getText().length() < 2 || lb.getText().length() > 9){
        errors.append("Please enter a label between 4 and 9 characters\n");
    }
     
    if(errors.length() > 0){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setContentText(errors.toString());
        alert.showAndWait();
    } else { // Si la validation est réussie, effectuer la modification
        ServiceCat sm = new ServiceCat() ;  
         
        c.setId(categorie.getId());   
        c.setLabelcat(lb.getText());
       
        
        sm.modifercat(c);
        Notifications notificationBuilder = Notifications.create().title("Alert").text("categorie modifier avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
        
        refreshTable();
    }
}

    @FXML
    private void rtr(ActionEvent event) {
                
        try{
         FXMLLoader loader= new FXMLLoader(getClass().getResource("./Vehiculee.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();
        }catch (IOException ex) {
            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);    
        }
    }


    @FXML
    private void excelmth(ActionEvent event) {
         Connection connection = MyDB.getInstance().getcnx();    
        try {

            String filename = "C:\\Users\\Ala\\Desktop\\Exel\\data.xls";
            HSSFWorkbook hwb = new HSSFWorkbook();
            HSSFSheet sheet = hwb.createSheet("new sheet");

            HSSFRow rowhead = sheet.createRow((short) 0);
            rowhead.createCell((short) 0).setCellValue("label des catégories");

            Connection cnx = MyDB.getInstance().getcnx();
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery("select * from Categorie");
            int i = 1;
            while (rs.next()) {
                HSSFRow row = sheet.createRow((short) i);

                row.createCell((short) 0).setCellValue(rs.getString("labelcat"));
              
                i++;
            }
            FileOutputStream fileOut = new FileOutputStream(filename);
            hwb.write(fileOut);
            fileOut.close();
            System.out.println("Your excel file has been generated!");
            File file = new File(filename);
            if (file.exists()) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                    Notifications notificationBuilder = Notifications.create().title("Alert").text("ecxel genérer avec succées").graphic(null).hideAfter(Duration.seconds(6))
                .position(Pos.BOTTOM_RIGHT);
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                }
            }

        } catch (Exception ex) {
            System.out.println(ex);

        }

            
    }

    @FXML
    private void select(ActionEvent event) {
         Categorie categorie = affiche.getSelectionModel().getSelectedItem();        
       lb.setText(categorie.getLabelcat());
    }
   
  

    

    

    

    @FXML
       private void affichrtTri(ActionEvent event) {
        ServiceCat rs = new ServiceCat();
      List<Categorie> l = new ArrayList<>();
    l = (ArrayList<Categorie>) rs.affichercat();
    ObservableList<Categorie> data = FXCollections.observableArrayList(l);
   data.sort((r1, r2) -> {
    int compare = String.CASE_INSENSITIVE_ORDER.compare(r1.getLabelcat(), r2.getLabelcat());
    if (compare == 0) {
        compare = r1.getLabelcat().compareTo(r2.getLabelcat());
    }
    return compare;
});
    FilteredList<Categorie> fle = new FilteredList(data, e -> true);
    affiche.setItems(fle);
}

   @FXML
    private void qr(ActionEvent event) throws com.google.zxing.WriterException {
    Categorie selectedCategorie = affiche.getSelectionModel().getSelectedItem(); // Récupérer la catégorie sélectionnée dans la TableView
    if (selectedCategorie == null) {
        // Aucune catégorie sélectionnée, afficher un message d'erreur
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText("Aucune catégorie sélectionnée");
        alert.setContentText("Veuillez sélectionner une catégorie dans la liste.");
        alert.showAndWait();
        return;
    }

    // Générer le code QR pour la catégorie
    String qrText = "Id: " + selectedCategorie.getId() + "\n\n" +
                    "Categorie: " + selectedCategorie.getLabelcat();
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
    qrStage.setTitle("Code QR pour les catégories des véhicules " );
    qrStage.setScene(new Scene(new StackPane(imageView), 400, 400));
    qrStage.show();
}

}
         
 
//@FXML
//    private void QR(ActionEvent event) throws WriterException, FileNotFoundException {
//        Categorie pt = affiche.getSelectionModel().getSelectedItem();
//
//        System.out.println(pt.getLabelcat());
//        String value = pt.getLabelcat();
//
//        String data = value;
////     // The path where the image will get saved
//        String path = "C:\\Users\\Ala\\Desktop\\Exel\\" + data + ".png";
//
////  // Encoding charset
//        String charset = "UTF-8";
//
//        Map<EncodeHintType, ErrorCorrectionLevel> hashMap
//                = new HashMap<>();
//
//        BitMatrix matrix;
//        try {
//            matrix = new MultiFormatWriter().encode(
//                    new String(data.getBytes(charset), charset),
//                    BarcodeFormat.QR_CODE, 200, 200);
//            MatrixToImageWriter.writeToFile(
//                    matrix,
//                    path.substring(path.lastIndexOf('.') + 1),
//                    new File(path));
//            hashMap.put(EncodeHintType.ERROR_CORRECTION,
//                    ErrorCorrectionLevel.L);
//
//            // Create the QR code and save
//            // in the specified folder
//            // as a jpg file
//            createQR(data, path, charset, hashMap, 200, 200);
//            System.out.println("QR Code Generated!!! ");
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(CategorieController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        InputStream stream;
//        stream = new FileInputStream("C:\\Users\\Ala\\Desktop\\Exel\\" + data + ".png");
//        Image image = new Image(stream);
//////Creating the image view
//////Setting image to the image view
//        qrcode.setImage(image);
//
//    }


   

   



