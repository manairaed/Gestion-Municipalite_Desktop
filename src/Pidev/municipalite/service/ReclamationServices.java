/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

//import static com.sun.org.omg.SendingContext.CodeBasePackage.ValueDescSeqHelper.type;
import entities.Reclamation;
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


public class ReclamationServices {
  
     Connection cnx;

    public ReclamationServices() {
        cnx=MaConnexion.getInstance().getCnx();
    }
    
        
        
      public Reclamation getNomrByIdRec(int id){
        Reclamation u =new  Reclamation();
        try {
            String query="SELECT * FROM `reclamation` where id="+id;
            Statement st=cnx.createStatement();
            ResultSet res=st.executeQuery(query);
            if(res.next()){
                
                u.setNom(res.getString("id_nom"));
               
                
            }
        }
            catch (SQLException ex) {
            Logger.getLogger(Reclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return u;
        
        }
   
    
     public void ajouterReclamation(Reclamation r){
        String sql="insert into reclamation(nom,prenom,email,tel,etat,description,date_reclamation) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste= cnx.prepareStatement(sql);
            ste.setString(1,r.getNom());
            ste.setString(2,r.getPrenom());
            ste.setString(3,r.getEmail());
            ste.setInt(4, r.getTel());
        
            
            ste.setString(5,r.getEtat());
            ste.setString(6,r.getDescription()); 
            ste.setDate(7,new java.sql.Date(r.getDate_reclamation().getTime()));
            ste.executeUpdate();
            System.out.println("Reclamation Ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
  

public int supprimerreclamation(int id)throws SQLException {
 
            
     int i = 0;
        try {
            Statement ste = cnx.createStatement();
            String sql = "DELETE FROM `reclamation` WHERE id=" + id;
            i = ste.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return i;
    }


    
   
    public void modifierReclamation( Reclamation r, int id_reclamation ) {
        

    }
    
    
    
    
      public List<Reclamation> afficherReclamation(){
        List<Reclamation> reclamations = new ArrayList<>();
        String query="select * from reclamation";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ResultSet rs= ste.executeQuery();
            while(rs.next()){
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
              
               
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                 r.setDate_reclamation(rs.getDate("date_reclamation"));
 
                reclamations.add(r);
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return reclamations;
        
    }
      
      
      
      
      
     public List<Reclamation> ReclamationParId(String id) throws SQLException {
    List<Reclamation> reclamations=new ArrayList<>();
    //JSONArray roles = new JSONArray();

    Statement ste = cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from reclamation where (sn LIKE '%"+id+"%')");
    Reclamation r = new Reclamation();
    while (rs.next()) {                

                r.setId(rs.getInt("id"));
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
       
              
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                 r.setDate_reclamation(rs.getDate("date_reclamation"));
 
                reclamations.add(r);
       
}
return reclamations;
   
}
      
      
      
          public Reclamation findById(long id){
         Reclamation r =new Reclamation();
          try {
            Statement st=cnx.createStatement();
            String query="select * from reclamation where id="+id;
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
             
                
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                 r.setDate_reclamation(rs.getDate("date_reclamation"));
            }else{
                System.out.println("Reclamation n existe pas");
                
              
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
        
    }
          
          
          
          
          public Reclamation findByNom(String nom){
         Reclamation r=new Reclamation();
         try {
            Statement st=cnx.createStatement();
            String query="select * from reclamation where nom='"+nom+"'";
            ResultSet rs=st.executeQuery(query);
            if(rs.next()){
                r.setNom(rs.getString("nom"));
                r.setPrenom(rs.getString("prenom"));
                r.setEmail(rs.getString("email"));
                r.setTel(rs.getInt("tel"));
              
                
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                 r.setDate_reclamation(rs.getDate("date_reclamation"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
     }
     
    
    public List<Reclamation> sortByDate(){
    List<Reclamation> reclamations = afficherReclamation();
    List<Reclamation> resultat = reclamations.stream()
            .sorted(Comparator.comparing(Reclamation::getDate_reclamation))
            .collect(Collectors.toList());
    return resultat;
}
     
     
   public List<Reclamation> sortByNom(){
    List<Reclamation> reclamations = afficherReclamation();
    List<Reclamation> resultat = reclamations.stream()
            .sorted(Comparator.comparing(Reclamation::getNom))
            .collect(Collectors.toList());
    return resultat;
}
     
      public int nbEncours(){
     String req="SELECT COUNT(*) FROM reclamation WHERE etat='Encours' ";
      
      int nb =0;
        
        try {
        	Statement stm = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return nb;
    }
     
     
     public int nbTraité(){
     String req="SELECT COUNT(*) FROM reclamation WHERE etat='Traite' ";
      
      int nb =0;
        
        try {
        	Statement stm = MaConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = stm.executeQuery(req);
            while(rs.next()){
             nb= rs.getInt(1);
              System.out.println(nb);
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     return nb;
    }

    
     
     
      public int getNbrReclamation() {
        String sql="SELECT COUNT(*) FROM reclamation";
        ResultSet rs;
        int countIdRec=0;
        try {
            PreparedStatement st= cnx.prepareStatement(sql);
			ResultSet res= st.executeQuery(); 
                        while(res.next()) {
                           countIdRec= res.getInt(1);
                        }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return countIdRec;
    }

public void modifierReclamation(Reclamation r) {
    try {
        Statement ste = cnx.createStatement();
        String qry = "UPDATE reclamation SET nom='" + r.getNom() + "', prenom='" + r.getPrenom() + "', email='" + r.getEmail() + "', tel='" + r.getTel() + "', etat='" + r.getEtat() + "', description='" + r.getDescription()+ "', date_reclamation=? WHERE id=" +r.getId();
        PreparedStatement ps = cnx.prepareStatement(qry);
        ps.setDate(1, new java.sql.Date(r.getDate_reclamation().getTime()));
        ps.executeUpdate();
        System.out.println("Reclamation modifiée");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

   
}











    


