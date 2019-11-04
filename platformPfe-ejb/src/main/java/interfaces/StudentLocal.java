package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.users.Student;
@Local
public interface StudentLocal {
	
	public List<Student> getStudents(String option,String categorie,String year,String pays);

}
