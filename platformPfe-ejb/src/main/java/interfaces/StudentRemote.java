package interfaces;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

import entities.users.Student;
@Remote
public interface StudentRemote {
	
	public List<Student> getStudents(String option,String categorie,String year,String pays);

}
