package business;

import interfaces.ClassroomBusinessLocal;
import interfaces.ClassroomBusinessRemote;

import java.util.ArrayList;
import java.util.Date;
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
		// TODO addClassroom method
		
	}

	@Override
	public void updateClassrom(Classroom classroom) {
		// TODO updateClassroom method 
		
	}

	@Override
	public boolean deleteClassroom(int id) {
		// TODO deleteClassroom method 
		return false;
	}

	@Override
	public Classroom getClassroomById(int id) {
		// TODO getClassroomById method 
		return null;
	}

	@Override
	public List<Classroom> getClassrooms() {
		return em.createQuery("select c from Classroom c", Classroom.class).getResultList();
	}

	@Override
	public List<Classroom> getAvailableClassrooms(Date date) { 
		 List<Classroom> availableClassrooms = new ArrayList<Classroom>();
		 for (Classroom classroom : this.getClassrooms()) {
			if (classroom.isAvailable(date)) {
				availableClassrooms.add(classroom);
			}
		}
		 return availableClassrooms;
	}
	
	

}
