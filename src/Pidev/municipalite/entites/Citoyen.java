
package Pidev.municipalite.entites;


public class Citoyen extends User {
    
    public Citoyen( String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
          super(   email,  password,  roles,  nomUtil,  prenomUtil, tel,  adresse);
    }
    
     public Citoyen( int id,String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
          super( id,  email,  password,  roles,  nomUtil,  prenomUtil, tel,  adresse);
    }
    
    public Citoyen(){
    }
}
