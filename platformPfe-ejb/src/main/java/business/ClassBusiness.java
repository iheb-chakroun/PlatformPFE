package business;

import interfaces.ClassBusinessLocal;
import interfaces.ClassBusinessRemote;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.Class;

/**
 * Session Bean implementation class ClassBusiness
 */
@Stateless
@LocalBean
public class ClassBusiness implements ClassBusinessRemote, ClassBusinessLocal {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public int addClass(Class classe) {
		em.persist(classe);
		return classe.getId();
	}

	@Override
	public Class getClassById(int id) {
		return em.find(Class.class, id);
	}

	@Override
	public List<Class> getAllClass() {
		return em.createQuery("select c from Class c", Class.class).getResultList();
	}

	@Override
	public void update(Class classe) {
		em.merge(classe);
		
	}

	@Override
	public boolean deleteClass(int id) {
		try {
			em.remove(getClassById(id));
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

}
