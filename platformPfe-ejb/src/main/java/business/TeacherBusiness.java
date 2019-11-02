package business;

import interfaces.TeacherBusinessLocal;
import interfaces.TeacherBusinessRemote;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.users.Teacher;

/**
 * Session Bean implementation class TeacherBusiness
 */
@Stateless
@LocalBean
public class TeacherBusiness implements TeacherBusinessRemote, TeacherBusinessLocal {

	@PersistenceContext(unitName = "platform")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public TeacherBusiness() {
	}

	

	@Override
	public List<Teacher> findAllTeachers() {
		System.out.println("In findAllTeachers : "); 
		List<Teacher> teachers =  em.createQuery("from Teacher", Teacher.class).getResultList();
		System.out.println("Out of findAllTeachers : "); 
		return teachers;
	}

}
