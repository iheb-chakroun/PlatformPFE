package interfaces;

import java.util.List;

import javax.ejb.Remote;
import entities.users.Student;

@Remote
public interface StudentRemote {
	
	//Simple CRUD Methods
	
	public int addStudent(Student student);
	public void removeUser(int id);
	public void updateUser(Student student);
	public Student findUserById(int id);
	public List<Student> findAllUsers();
	
}
