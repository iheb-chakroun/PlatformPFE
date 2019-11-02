package business;

import interfaces.ClassroomBusinessLocal;
import interfaces.ClassroomBusinessRemote;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.administration.Classroom;

/**
 * Session Bean implementation class ClassroomBusiness
 */
@Stateless
@LocalBean
public class ClassroomBusiness implements ClassroomBusinessRemote, ClassroomBusinessLocal {

	@PersistenceContext
	EntityManager em;
	

	@Override
	public void addClassroom(Classroom classroom) {
		em.persist(classroom);
		
	}

	@Override
	public void updateClassrom(Classroom classroom) {
		em.merge(classroom);
		
	}

	@Override
	public boolean deleteClassroom(int id) {
		try {
			em.remove(em.find(Classroom.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Classroom getClassroomById(int id) {
		return em.find(Classroom.class, id);
	}

	@Override
	public List<Classroom> getClassrooms() {
		return em.createQuery("select c from Classroom c", Classroom.class).getResultList();
	}

	
	

}
