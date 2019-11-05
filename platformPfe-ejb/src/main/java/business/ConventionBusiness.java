package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.Convention;
import interfaces.ConventionLocal;

@Stateless
public class ConventionBusiness implements ConventionLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	
	
	@Override
	public Convention findConventionisById(int id) {

		Convention a = em.find(Convention.class, id);

		return a;
	}

	@Override
	public boolean deleteConventionById(int Id) {
		// TODO Auto-generated method stub
		Convention emp = em.find(Convention.class,Id);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterConvention(Convention p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public List<Convention> getAllConvention() {
		// TODO Auto-generated method stub
		List<Convention> thesis = em.createQuery("select e from Convention e", Convention.class).getResultList();
		return thesis;
	}

	@Override
	public boolean updateConvention(Convention p) {
		// TODO Auto-generated method stub


		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
