package business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
	public void removeStudent(int id) {
		// TODO Auto-generated method stub
		//Find managed Entity reference
        Student st = em.find(Student.class, id );
        //Call remove method to remove the entity
        if(st != null){
            em.remove(st);
        }
	}

	@Override
	public void updateStudent(Student student) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("update Student u set u.email=:email, u.firstName=:fname, u.lastName=:lname, u.password=:password, u.sexe=:sexe, u.status=:status, u.tel=:tel where u.id=:id");
		query.setParameter("id", student.getId());
		query.setParameter("email", student.getEmail());
		query.setParameter("fname", student.getFirstName());
		query.setParameter("lname", student.getLastName());
		query.setParameter("password", student.getPassword());
		query.setParameter("sexe", student.getSexe());
		query.setParameter("status", student.isStatus());
		query.setParameter("tel", student.getTel());
		query.executeUpdate();
		
	}

	@Override
	public Student findStudentById(int id) {
		System.out.println("In findStudentById : "); 
		Student st = em.find(Student.class, id); 
		System.out.println(st);
		System.out.println("Out of findStudentById : "); 
		return st; 
	}

	@Override
	public List<Student> findAllStudents() {
		// TODO Auto-generated method stub
		System.out.println("In findAllStudents : "); 
		List<Student> students =  em.createQuery("from Student", Student.class).getResultList();
		System.out.println("Out of findAlltudents : "); 
		return students; 
	}

}
