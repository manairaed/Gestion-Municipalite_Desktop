
package Pidev.municipalite.entites;

public class Admin extends User {
    public Admin(){
        
    }
    public Admin(int id, String email, String password, String roles, String nomUtil, String prenomUtil){
        super( id,  email,  password,  roles,  nomUtil,  prenomUtil);
    }
}
