package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.Admin;
import entities.users.Employe;
import interfaces.AdminRemote;

@Stateless
public class AdminBusiness implements AdminRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addAdmin(Admin admin) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(admin);
		System.out.println("Finished");
		return admin.getId();
	}

	@Override
	public void removeAdmin(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
		Admin admin = em.find(Admin.class, id);
        //Call remove method to remove the entity
        if(admin != null){
            em.remove(admin);
        }
		
	}

	@Override
	public void updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		Admin admin = em.find(Admin.class, id); 
		System.out.println("Out of findAdminById : "); 
		return admin; 
	}

	@Override
	public List<Admin> findAllAdmins() {
		// TODO Auto-generated method stub
		System.out.println("In findAllAdmins : "); 
		List<Admin> employes =  em.createQuery("from Admin", Admin.class).getResultList();
		System.out.println("Out of findAllAdmins : "); 
		return employes;
	}

}
