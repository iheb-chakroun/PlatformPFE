package business;

import java.util.ArrayList;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.documents.Categorie;
import interfaces.CategorieSerivceLocal;

@Stateless
public class CategorieBusiness implements CategorieSerivceLocal{

	@PersistenceContext(unitName ="platform")
	EntityManager em;
	
	static List<Categorie> categories=new ArrayList<Categorie>();

	
	@Override
	public boolean addCategorie(Categorie categorie) {
			 em.persist(categorie);
				return true;
	}

	@Override
	public boolean deleteCategorie(int idcategorie) {
   em.remove(em.find(Categorie.class, idcategorie)); 
	   return true;
	}

	@Override
	public List<Categorie> getAllCategories() {
			List<Categorie> categories = em.createQuery("SELECT c from Categorie c", Categorie.class).getResultList();
		return categories;
	}

	@Override
	public boolean ModifyCategorie(int id, Categorie categorie) {
		Categorie c=em.find(Categorie.class, id);
		c.setLabel(categorie.getLabel());
		c.setStatus(categorie.getStatus());
		em.merge(c);
		return true;
	}

}
