package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.DepartementHead;
import interfaces.DepartmentHeadRemote;

@Stateless
public class DepartmentHeadBusiness implements DepartmentHeadRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addDepartmentHead(DepartementHead dh) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(dh);
		System.out.println("Finished");
		return dh.getId();
	}

	@Override
	public void removeDepartmentHead(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
		DepartementHead dh = em.find(DepartementHead.class, id);
		        //Call remove method to remove the entity
		        if(dh != null){
		            em.remove(dh);
		        }
		
	}

	@Override
	public void updateDepartmentHead(DepartementHead dh) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("update DepartementHead u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.username=:username, u.address=:address, u.password=:password where u.id=:id");
		query.setParameter("id", dh.getId());
		query.setParameter("email", dh.getEmail());
		query.setParameter("fname", dh.getFirstName());
		query.setParameter("lname", dh.getLastName());
		query.setParameter("password", dh.getPassword());
		query.setParameter("address", dh.getAddress());
		query.setParameter("username", dh.getUsername());
		query.executeUpdate();
		
	}

	@Override
	public DepartementHead findDepartmentHeadById(int id) {
		// TODO Auto-generated method stub
		DepartementHead dh = em.find(DepartementHead.class, id); 
		System.out.println("Out of findDepartmentHeadById : "); 
		return dh; 
	}

	@Override
	public List<DepartementHead> findDepartmentHeads() {
		// TODO Auto-generated method stub
		System.out.println("In findAllAdmins : "); 
		List<DepartementHead> dhs =  em.createQuery("from DepartementHead", DepartementHead.class).getResultList();
		System.out.println("Out of findAllAdmins : "); 
		return dhs;
	}

}
