package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.Categorie;
import entities.documents.Convention;
import interfaces.categorieLocal;

@Stateless
public class CategorieBusiness implements categorieLocal {
	@PersistenceContext(unitName = "platform")
	EntityManager em;
	
	
	
	@Override
	public Categorie findCategorieisById(int id) {

		Categorie a = em.find(Categorie.class, id);

		return a;
	}

	@Override
	public boolean deleteCategorieById(int Id) {
		// TODO Auto-generated method stub
		Categorie emp = em.find(Categorie.class,Id);

		try {
			em.remove(emp);
			return true;

		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public void ajouterCategorie(Categorie p) {
		// TODO Auto-generated method stub
		em.persist(p);
	}

	@Override
	public List<Categorie> getAllCategorie() {
		// TODO Auto-generated method stub
		List<Categorie> c = em.createQuery("select e from Categorie e", Categorie.class).getResultList();
		return c;
	}

	@Override
	public boolean updateCategorie(Categorie p) {
		// TODO Auto-generated method stub


		try {
			em.merge(p);
			return true;

		} catch (Exception e) {
			return false;
		}
	}
}
