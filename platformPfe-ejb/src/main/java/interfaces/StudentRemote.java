package interfaces;

import java.util.Date;
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
	public List<Student> findStudentsByYears(String year1, String year2, String year3);
	
}
