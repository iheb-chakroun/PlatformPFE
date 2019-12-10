package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.administration.Departement;
import entities.administration.School;
import entities.administration.Site;
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

	@Override
	public List<Admin> getAvailableAdmin() {
		List<Admin> a_admin  = new ArrayList<Admin>();
		for (Admin admin : this.findAllAdmins()) {
			if (admin.getSchool() == null) {
				a_admin.add(admin);
			}
		}
		return a_admin;
	}

	@Override
	public School getAdminSchool(String email) {
		for (Admin admin : this.findAllAdmins()) {
			if (admin.getEmail().compareToIgnoreCase(email) == 0) {
				return admin.getSchool();
			}
		}
		return null;
	}
	
	@Override
	public List<Site> getAdminSite(String email) {
		List<Site> sites = new ArrayList<Site>();
		for (Admin admin : this.findAllAdmins()) {
			if (admin.getEmail().compareToIgnoreCase(email) == 0) {
				sites.addAll(admin.getSchool().getSites());
			}
		}
		return sites;
	}
	
	@Override
	public List<Departement> getAdminDepartment(String email) {
		List<Departement> departments = new ArrayList<Departement>();
		for (Admin admin : this.findAllAdmins()) {
			if (admin.getEmail().compareToIgnoreCase(email) == 0) {
				for(Site site : admin.getSchool().getSites()) {
					departments.addAll(site.getDepartements());
				}
			}
		}
		return departments;
	}

}
