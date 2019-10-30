package business;

import interfaces.TeacherBusinessLocal;
import interfaces.TeacherBusinessRemote;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.documents.Thesis;
import entities.users.Teacher;

/**
 * Session Bean implementation class TeacherBusiness
 */
@Stateless
public class TeacherBusiness implements TeacherBusinessRemote, TeacherBusinessLocal {

	@PersistenceContext(unitName = "platform")
	EntityManager em;

	/**
	 * Default constructor.
	 */
	public TeacherBusiness() {
	}

	@Override
	public List<Teacher> getValidTeachers(Thesis thesis, Date date) {
		List<Teacher> teachers = em.createQuery("select t from Teacher t", Teacher.class).getResultList();
		List<Teacher> validTeachers = new ArrayList<Teacher>();
		for (Teacher teacher : teachers) {
			if (teacher.isValid(thesis, date))
				validTeachers.add(teacher);
		}
		return validTeachers;
	}

}
