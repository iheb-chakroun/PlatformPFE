package business;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.Admin;
import interfaces.AdminBusinessLocal;
import interfaces.AdminBusinessRemote;

/**
 * Session Bean implementation class AdminBusiness
 */
@Stateless
@LocalBean
public class AdminBusiness implements AdminBusinessRemote, AdminBusinessLocal {

	@PersistenceContext
	EntityManager em;
	
    public AdminBusiness() {
    }
    
    @Override
	public int addAdmin(Admin admin) {
		System.out.println("Starting input:");
		em.persist(admin);
		System.out.println("Finished");
		return admin.getId();
	}

	@Override
	public void removeAdmin(int id) {
		//Find managed Entity reference
		Admin admin = em.find(Admin.class, id);
        //Call remove method to remove the entity
        if(admin != null){
            em.remove(admin);
        }
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		Query query = em.createQuery("update Admin u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.username=:username, u.address=:address, u.password=:password where u.id=:id");
		query.setParameter("id", admin.getId());
		query.setParameter("email", admin.getEmail());
		query.setParameter("fname", admin.getFirstName());
		query.setParameter("lname", admin.getLastName());
		query.setParameter("password", admin.getPassword());
		query.setParameter("address", admin.getAddress());
		query.setParameter("username", admin.getUsername());
		query.executeUpdate();
		
	}

	@Override
	public Admin findAdminById(int id) {
		Admin admin = em.find(Admin.class, id); 
		System.out.println("Out of findAdminById : "); 
		return admin; 
	}

	@Override
	public List<Admin> findAllAdmins() {
		System.out.println("In findAllAdmins : "); 
		List<Admin> employes =  em.createQuery("from Admin", Admin.class).getResultList();
		System.out.println("Out of findAllAdmins : "); 
		return employes;
	}

}
