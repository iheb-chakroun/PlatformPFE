package business;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.users.Student;

import interfaces.StudentRemote;

@Stateless
public class StudentBusiness implements StudentRemote{

	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	@Override
	public int addStudent(Student student) {
		// TODO Auto-generated method stub
		System.out.println("Starting input:");
		em.persist(student);
		System.out.println("Finished");
		return student.getId();
	}

	@Override
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student findUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
