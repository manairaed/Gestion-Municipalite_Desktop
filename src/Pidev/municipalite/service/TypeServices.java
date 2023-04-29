/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

//import static com.sun.org.omg.SendingContext.CodeBasePackage.ValueDescSeqHelper.type;
import entities.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static org.omg.CORBA.ServiceInformationHelper.type;
import tools.MaConnexion;


public class TypeServices {
  
     Connection cnx;

    public TypeServices() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
        
        
    
   
    
     public void ajouterType(Type r){
        String sql="insert into type(type) values(?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);

            ste.setString(1,r.getType());

            ste.executeUpdate();
            System.out.println("Type Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
  

public int supprimertype(int id)throws SQLException {
 
            
     int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `type` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(TypeServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }


    
   
    public void modifierType(long id, Type r) {
    
        
        try {
            PreparedStatement ste;
            ste=cnx.prepareStatement(
                    "UPDATE `reclamation` SET `type`=? WHERE id=?");
  
            ste.setString(1,r.getType());
    
            ste.setLong(2, id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
     

    
     

    }
    
    
    
    
      public List<Type> afficherType(){
        List<Type> types = new ArrayList<>();
        String query="select * from type";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Type r = new Type();
                r.setId(rs.getInt("id"));
          
                r.setType(rs.getString("type"));

 
                types.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return types;
        
    }
      
      
      
      
      
     public List<Type> TypeParId(String id) throws SQLException {
    List<Type> types=new ArrayList<>();
    //JSONArray roles = new JSONArray();

    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from type where (sn LIKE '%"+id+"%')");
    Type r = new Type();
    while (rs.next()) {                

                r.setId(rs.getInt("id"));
           
                r.setType(rs.getString("type"));
              
 
                types.add(r);
       
}
return types;
   
}
      
      
      
        
    
   
}











    


