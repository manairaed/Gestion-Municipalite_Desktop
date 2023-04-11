
package Pidev.municipalite.entites;


public class Employee  extends User{
    
      public Employee( String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
          super(   email,  password,  roles,  nomUtil,  prenomUtil, tel,  adresse);
    }
    
     public Employee( int id,String email, String password, String roles, String nomUtil, String prenomUtil, int tel, String adresse) {
          super( id,  email,  password,  roles,  nomUtil,  prenomUtil, tel,  adresse);
    }
    
    public Employee(){
    }
}
