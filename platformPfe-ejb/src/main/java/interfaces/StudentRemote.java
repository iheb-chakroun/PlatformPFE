package interfaces;

import java.util.List;

import javax.ejb.Remote;

import collection.Status;
import entities.tracking.StudentNotification;
import entities.users.Student;
import utilities.Reason;

@Remote
public interface StudentRemote {
	
	//Simple CRUD Methods
	
	public int addStudent(Student student);
	public void removeStudent(int id);
	public void updateStudent(Student student);
	public Student findStudentById(int id);
	public List<Student> findAllStudents();
	public List<StudentNotification> findAllNotifications();
	public List<Student> findStudentsByYears(String... years );
	public void authorizeStudent(int id);
	public Status demandeAnnulation(int id);
	public Status approveAnnulation(int id);
	public void declineAnnulation(Reason reason);
	
}
