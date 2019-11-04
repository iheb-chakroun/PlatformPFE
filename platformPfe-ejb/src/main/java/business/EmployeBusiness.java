package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import entities.documents.PfeFile;
import entities.users.Employe;
import interfaces.EmployeRemote;


@Stateless
public class EmployeBusiness implements EmployeRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addEmploye(Employe employe) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(employe);
		System.out.println("Finished");
		return employe.getId();
	}

	@Override
	public void removeEmploye(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
        Employe employe = em.find(Employe.class, id);
        //Call remove method to remove the entity
        if(employe != null){
            em.remove(employe);
        }
	}

	@Override
	public void updateEmploye(Employe employe) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("update Employe u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.username=:username, u.address=:address, u.password=:password where u.id=:id");
		query.setParameter("id", employe.getId());
		query.setParameter("email", employe.getEmail());
		query.setParameter("fname", employe.getFirstName());
		query.setParameter("lname", employe.getLastName());
		query.setParameter("password", employe.getPassword());
		query.setParameter("address", employe.getAddress());
		query.setParameter("username", employe.getUsername());
		query.executeUpdate();
	}

	@Override
	public Employe findEmployeById(int id) {
		Employe employe = em.find(Employe.class, id); 
		System.out.println("Out of findEmployeById : "); 
		return employe; 
	}

	@Override
	public List<Employe> findAllEmployes() {
		System.out.println("In findAllEmployes : "); 
		List<Employe> employes =  em.createQuery("from Employe", Employe.class).getResultList();
		System.out.println("Out of findAllEmployes : "); 
		return employes; 
	}

	/*
	 * @Author: Saidi Khaled
	 */
	@Override
	public Employe login(String username, String password) {
		// TODO Auto-generated method stub
		TypedQuery<Employe> q = em.createQuery("select e from Employe e where e.username=:username and e.password=:password", Employe.class);
		q.setParameter("username", username);
		q.setParameter("password", password);

        try {
            return q.getSingleResult();
        } catch (Exception e) {
            return null;
        }
	}


}
