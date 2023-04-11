
package pidev;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import Pidev.municipalite.utils.MyConnection;


public class PIDEV {

    
    public static void main(String[] args) {
 //MyConnection cnx = MyConnection.getInstance();
ServiceUser us = new ServiceUser();
//User u = new User("saif@gmail.com", "ki zebii", "ROLE_CITOYEN", "saif", "messaoudi", 29603440, "Nabeul 8000");
      
//us.ajouter(u);
us.supprimer(23);
 
    }
    
}
