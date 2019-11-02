package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.users.Employe;
import entities.users.Teacher;
import interfaces.TeacherRemote;

@Stateless
public class TeacherBusiness implements TeacherRemote {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;

	@Override
	public int addTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(teacher);
		System.out.println("Finished");
		return teacher.getId();
	}

	@Override
	public void removeTeacher(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
		Teacher teacher = em.find(Teacher.class, id);
		//Call remove method to remove the entity
		if(teacher != null){
		    em.remove(teacher);
		 }
	}

	@Override
	public void updateTeacher(Teacher teacher) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("update Teacher u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.username=:username, u.address=:address, u.password=:password where u.id=:id");
		query.setParameter("id", teacher.getId());
		query.setParameter("email", teacher.getEmail());
		query.setParameter("fname", teacher.getFirstName());
		query.setParameter("lname", teacher.getLastName());
		query.setParameter("password", teacher.getPassword());
		query.setParameter("address", teacher.getAddress());
		query.setParameter("username", teacher.getUsername());
		query.executeUpdate();	
	}

	@Override
	public Teacher findTeacherById(int id) {
		// TODO Auto-generated method stub
		Teacher teacher = em.find(Teacher.class, id); 
		System.out.println("Out of findAdminById : "); 
		return teacher; 
	}

	@Override
	public List<Teacher> findAllTeachers() {
		// TODO Auto-generated method stub
		System.out.println("In findAllTeachers : "); 
		List<Teacher> teachers =  em.createQuery("from Teacher", Teacher.class).getResultList();
		System.out.println("Out of findAllTeachers : "); 
		return teachers;
	}

}
