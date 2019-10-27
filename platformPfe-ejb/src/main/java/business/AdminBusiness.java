package business;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import interfaces.AdminBusinessLocal;
import interfaces.AdminBusinessRemote;

/**
 * Session Bean implementation class AdminBusiness
 */
@Stateless
@LocalBean
public class AdminBusiness implements AdminBusinessRemote, AdminBusinessLocal {

    /**
     * Default constructor. 
     */
    public AdminBusiness() {
        // TODO Auto-generated constructor stub
    }

}
