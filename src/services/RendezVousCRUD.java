/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;


import com.itextpdf.text.BaseColor;
import entities.RendezVous;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

import entities.RendezVous;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.File;
import java.time.LocalTime;
import javax.swing.JOptionPane;


//import us.zoom.sdk.ZoomError;
//import us.zoom.sdk.ZoomMeetingOptions;
//import us.zoom.sdk.ZoomMeetingService;
//import us.zoom.sdk.ZoomSDK;
//import us.zoom.sdk.ZoomSDKInitializeParams;
//import us.zoom.sdk.ZoomSDKInitializeResult;




/**
 *
 * @author Farhat
 */
public class RendezVousCRUD {
    Connection cnx2;
    private static final String apiKey = "BFSfbVeUQSms7caXzOANpg";
    private static final String apiSecret = "4cIRnX29CaSmZkA2vffV4xdzKD6uaKbF93gy";

    public RendezVousCRUD() {
        cnx2 = MyConnection.getInstance().getCnx();
        
    }
    
    public void ajouterrendezvous(RendezVous r){
        
        try {
            String req = "INSERT INTO `rendez_vous` (`description`, `date_ren`) VALUES ('" + r.getDescription() + "', '" + r.getDate_ren() + "')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendez Vous created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
    public void ajouterrendezvous2(RendezVous r){
        
         try {
            String req2 = "INSERT INTO `rendez_vous` (`description`, `date_ren`) VALUES (?,?)";
            PreparedStatement ps = cnx2.prepareStatement(req2);
            ps.setString(1, r.getDescription());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateString = dateFormat.format(r.getDate_ren());
        ps.setString(2, dateString);
            ps.executeUpdate();
            System.out.println("rendez vous created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      
    }
   
   

    
  public void modifierrendez(RendezVous r) {
    try {
        String req = "UPDATE rendez_vous SET description=?, date_ren=? WHERE id=?";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setString(1, r.getDescription());
         ps.setTimestamp(2, Timestamp.valueOf(r.getDate_ren()));
        ps.setInt(3, r.getId());
        ps.executeUpdate();
        System.out.println("rendezvous updated !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    public void supprimerrendez(int id) {
        try {
            String req = "DELETE FROM `rendez_vous` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("rendez vous deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<RendezVous> afficherrendezvous() throws ParseException {
    ObservableList<RendezVous> list =  FXCollections.observableArrayList() ;
    try {
        String req = "Select * from rendez_vous";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            int id = rs.getInt("id");
            String description = rs.getString("description");
            java.util.Date date_ren = rs.getTimestamp("date_ren");

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String dateString = dateFormat.format(date_ren);

            java.util.Date date = dateFormat.parse(dateString);
            Instant instant = date.toInstant();
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            RendezVous d = new RendezVous(id,description, localDateTime);
            list.add(d);
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }

    return list;
}
    public RendezVous getOnerendez(int idrendez) throws SQLException {
        String req = "SELECT * FROM `rendez_vous` where id = ?";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, idrendez);

        ResultSet rs = ps.executeQuery();
        RendezVous o =new RendezVous();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setDescription(rs.getString("description"));
                  String dateString = rs.getString("date_ren");
            LocalDateTime dateTime = LocalDateTime.parse(dateString);
            o.setDate_ren(dateTime);
                   
        }
        ps.close();
        return o;
    }
    public LocalDateTime queryLastMeetingDateTime() {
    LocalDateTime lastMeetingDateTime = null;
    try {
        String req = "SELECT MAX(date_ren) AS lastMeetingDateTime FROM rendez_vous";
        Statement st = cnx2.createStatement();
        ResultSet rs = st.executeQuery(req);
        if (rs.next()) {
            lastMeetingDateTime = rs.getTimestamp("lastMeetingDateTime").toLocalDateTime();
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return lastMeetingDateTime;
}

    public class PDFGenerator {

        public void generateRendezVousPDF(List<RendezVous> rendezVousList, String fileName) throws FileNotFoundException, DocumentException {
            String downloadFolder = System.getProperty("user.home") + "/Downloads/";

            // Create the file with the specified name in the download folder
            File file = new File(downloadFolder + fileName);
            FileOutputStream fos = new FileOutputStream(file);

            Document document = new Document();
            PdfWriter.getInstance(document, fos);
            document.open();

            // Add a title to the document
            Paragraph title = new Paragraph("Liste des rendez-vous", new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, new BaseColor(0, 0, 255)));
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            // Add a blank line to the document
            document.add(new Paragraph("\n"));

            // Create a table to display the rendez-vous data
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);
            table.setSpacingAfter(10);

            // Add table headers
            PdfPCell descriptionHeader = new PdfPCell(new Phrase("Description", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            descriptionHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            descriptionHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            descriptionHeader.setBackgroundColor(new BaseColor(0, 191, 255));
            table.addCell(descriptionHeader);

            PdfPCell dateTimeHeader = new PdfPCell(new Phrase("Date et heure", new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD)));
            dateTimeHeader.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateTimeHeader.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateTimeHeader.setBackgroundColor(new BaseColor(0, 191, 255));
            table.addCell(dateTimeHeader);

            // Add the rendez-vous data to the table
            for (RendezVous rendezVous : rendezVousList) {

                PdfPCell descriptionCell = new PdfPCell(new Phrase(rendezVous.getDescription()));
                descriptionCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                descriptionCell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(descriptionCell);

                PdfPCell dateTimeCell = new PdfPCell(new Phrase(rendezVous.getDate_ren().toString()));
                dateTimeCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                dateTimeCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(dateTimeCell);
            }

            // Add the table to the document
            document.add(table);

            document.close();

            JOptionPane.showMessageDialog(null, "Le fichier a été téléchargé dans le dossier de téléchargement.");
        }

    }
      

    
    
    


    public  String generateAccessToken() {
        // Set the expiration time for the token to 1 hour from now
        Date expirationTime = new Date(System.currentTimeMillis() + 3600000);
        
        // Create the JWT token using the apiKey, apiSecret, and expiration time
        String token = Jwts.builder()
                .setIssuer(apiKey)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, apiSecret)
                .compact();
        
        return token;
    }
    
    
    
    
public boolean canAddMeeting(LocalDate date, LocalTime time) {
    boolean canAdd = true;
    LocalDateTime dateTime = LocalDateTime.of(date, time);
    try {
        String req = "SELECT date_ren FROM rendez_vous WHERE DATE(date_ren) = ?";
        PreparedStatement st = cnx2.prepareStatement(req);
        st.setDate(1, java.sql.Date.valueOf(date));
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            LocalDateTime dbDateTime = rs.getTimestamp("date_ren").toLocalDateTime();
            if (dbDateTime.isBefore(dateTime.plusMinutes(30)) && dbDateTime.isAfter(dateTime.minusMinutes(30))) {
                canAdd = false;
                break;
            }
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return canAdd;
}

    



        
        
}
    
    
    
    
    
    
    
    

