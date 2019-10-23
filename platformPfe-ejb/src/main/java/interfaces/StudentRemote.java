package interfaces;

import java.util.List;

import javax.ejb.Remote;
import entities.users.Student;

@Remote
public interface StudentRemote {
	
	//Simple CRUD Methods
	
	public int addStudent(Student student);
	public void removeStudent(int id);
	public void updateStudent(Student student);
	public String findStudentById(int id);
	public List<Student> findAllStudents();
	
}
