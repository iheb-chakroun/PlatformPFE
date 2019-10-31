package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.Thesis;
import interfaces.ThesisLocal;

@Stateless
public class ThesisBuisness implements ThesisLocal  {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	@Override
	public Thesis findThesisById(int id) {

		Thesis a = em.find(Thesis.class, id);

		return a;
	}

	@Override
	public boolean deleteThesisById(int ThesisId) {
		// TODO Auto-generated method stub
		Thesis emp = em.find(Thesis.class, ThesisId);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterThesis(Thesis p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public List<Thesis> getAllThesis() {
		// TODO Auto-generated method stub
		List<Thesis> thesis = em.createQuery("select e from Thesis e", Thesis.class).getResultList();
		return thesis;
	}

	@Override
	public boolean updateThesis(Thesis p) {
		// TODO Auto-generated method stub
		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}

}
