
package pidev;

import Pidev.municipalite.entites.User;
import Pidev.municipalite.services.ServiceUser;
import Pidev.municipalite.utils.MyConnection;
import java.util.ArrayList;
import java.util.List;


public class PIDEV {

    
    public static void main(String[] args) {
        
      //  List l = new ArrayList();
        
 //MyConnection cnx = MyConnection.getInstance();
ServiceUser us = new ServiceUser();
User u = new User("saif12345@gmail.com", "saifsaif002", "ROLE_CITOYEN", "saif", "messaoudi", 29603440, "Nabeul 8000");
     // User u = new User(25,"saifmessaoudi@gmail.com","saif", "messaoudi", 29886500, "Nabeul 8000");
 //l=us.getAll();
     //   System.out.println(l);
us.ajouter(u);
//us.supprimer(23);
//System.out.println(us.getOneById(3));
 
    }
    
}
