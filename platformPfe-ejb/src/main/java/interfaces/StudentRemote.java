package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.tracking.StudentNotification;
import entities.users.Student;

@Remote
public interface StudentRemote {
	
	//Simple CRUD Methods
	
	public int addStudent(Student student);
	public void removeStudent(int id);
	public void updateStudent(Student student);
	public Student findStudentById(int id);
	public List<Student> findAllStudents();
	public List<StudentNotification> findAllNotifications();
	
}