/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Document;
import entities.RendezVous;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author Farhat
 */
public class DocumentCRUD {
    Connection cnx2;

    public DocumentCRUD() {
         cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    public void ajouterdocument(Document d){
        
        try {
            String req = "INSERT INTO `document` (`name`, `image`) VALUES ('" + d.getName() + "', '" + d.getImage() + "')";
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Document created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
       
    }
    public void ajouterdocument2(Document d){
        
         try {
            String req2 = "INSERT INTO `document` (`name`, `image`) VALUES (?,?)";
            PreparedStatement ps = cnx2.prepareStatement(req2);
            ps.setString(1, d.getName());
            ps.setString(2, d.getImage());
            ps.executeUpdate();
            System.out.println("Document created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
      
    }
    public void modifier(Document d) {
        try {
            String req = "UPDATE `document` SET `name` = '" + d.getName() + "', `image` = '" + d.getImage() + "' WHERE `document`.`id` = " + d.getId();
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("Document updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimer(int id) {
        try {
            String req = "DELETE FROM `document` WHERE id = " + id;
            Statement st = cnx2.createStatement();
            st.executeUpdate(req);
            System.out.println("document deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Document> afficherdocument(){
    ObservableList<Document> list =  FXCollections.observableArrayList() ;
        try {
            String req = "Select * from document";
            Statement st = cnx2.createStatement();
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String image = rs.getString("image");
                
                Document d = new Document(id,name,image);

                list.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
        
        
    }
      public Document getOnedoc(int iddoc) throws SQLException {
        String req = "SELECT * FROM `rendez_vous` where id = ?";
        PreparedStatement ps = cnx2.prepareStatement(req);
        ps.setInt(1, iddoc);

        ResultSet rs = ps.executeQuery();
        Document o =new Document();
        
        while (rs.next()) {
        
                    o.setId(rs.getInt(1));
                    o.setName(rs.getString("name"));
                   o.setImage(rs.getString("image"));
           
                   
        }
        ps.close();
        return o;
    }
      
    public byte[] getImageById(int id) throws SQLException {
        byte[] imageBytes = null;
        String query = "SELECT image FROM document WHERE id = ?";
        try ( PreparedStatement statement = cnx2.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                imageBytes = resultSet.getBytes("image");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return imageBytes;
    }
    
    public int getDownloadedCount(int id) {
    int count = 0;
    try {
        String query = "SELECT download_count FROM document WHERE id = ?";
        PreparedStatement ps = cnx2.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt("download_count");
        }
        ps.close();
        
        // Increment the download_count column by 1
        String updateQuery = "UPDATE document SET download_count = ? WHERE id = ?";
        PreparedStatement updatePs = cnx2.prepareStatement(updateQuery);
        updatePs.setInt(1, count + 1);
        updatePs.setInt(2, id);
        updatePs.executeUpdate();
        updatePs.close();
        
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return count;
}
public int getDownloadCountById(int id) {
    int count = 0;
    try {
        String req = "SELECT download_count FROM document WHERE id=?";
        PreparedStatement pst = cnx2.prepareStatement(req);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            count = rs.getInt("download_count");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return count;
}


     
   
    
}
