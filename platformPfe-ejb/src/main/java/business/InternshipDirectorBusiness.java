package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.administration.Site;
import entities.users.InternshipDirector;
import interfaces.InternshipDirectorRemote;

@Stateless
public class InternshipDirectorBusiness implements InternshipDirectorRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addDirector(InternshipDirector director) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(director);
		System.out.println("Finished");
		return director.getId();
	}

	@Override
	public void removeDirector(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
		InternshipDirector director = em.find(InternshipDirector.class, id);
		//Call remove method to remove the entity
		if(director != null){
		em.remove(director);
		 }

	}

	@Override
	public void updateDirector(InternshipDirector director) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		InternshipDirector director = em.find(InternshipDirector.class, id); 
		System.out.println("Out of findAdminById : "); 
		return director; 
	}

	@Override
	public List<InternshipDirector> findAllDirectors() {
		// TODO Auto-generated method stub
		System.out.println("In findAllDirectors : "); 
		List<InternshipDirector> directors =  em.createQuery("from InternshipDirector", InternshipDirector.class).getResultList();
		System.out.println("Out of findAllDirectors : "); 
		return directors;
	}
	//@Author: khaled
	//requirement 8
	@Override
	public void fixNumbers(Site site) {
		// TODO Auto-generated method stub
		Site st = em.find(Site.class, site.getId());
		st.setNmapresident(site.getNmapresident());
		st.setNmarapporteur(site.getNmarapporteur());
		st.setNmasupervisor(site.getNmasupervisor());
		st.setNmavalidator(st.getNmavalidator());
	}

}
