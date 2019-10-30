package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.InternshipDirector;
import interfaces.InternshipDirectorRemote;

@Stateless
public class InternshipDirectorBusiness implements InternshipDirectorRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addDirector(InternshipDirector director) {
		System.out.println("Starting input:");
		em.persist(director);
		System.out.println("Finished");
		return director.getId();
	}

	@Override
	public void removeDirector(int id) {
		//Find managed Entity reference
		InternshipDirector director = em.find(InternshipDirector.class, id);
		//Call remove method to remove the entity
		if(director != null){
		em.remove(director);
		 }

	}

	@Override
	public void updateDirector(InternshipDirector director) {
		Query query = em.createQuery("update InternshipDirector u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.username=:username, u.address=:address, u.password=:password where u.id=:id");
		query.setParameter("id", director.getId());
		query.setParameter("email", director.getEmail());
		query.setParameter("fname", director.getFirstName());
		query.setParameter("lname", director.getLastName());
		query.setParameter("password", director.getPassword());
		query.setParameter("address", director.getAddress());
		query.setParameter("username", director.getUsername());
		query.executeUpdate();

	}

	@Override
	public InternshipDirector findDirectorById(int id) {
		InternshipDirector director = em.find(InternshipDirector.class, id); 
		System.out.println("Out of findAdminById : "); 
		return director; 
	}

	@Override
	public List<InternshipDirector> findAllDirectors() {
		System.out.println("In findAllDirectors : "); 
		List<InternshipDirector> directors =  em.createQuery("from InternshipDirector", InternshipDirector.class).getResultList();
		System.out.println("Out of findAllDirectors : "); 
		return directors;
	}

}