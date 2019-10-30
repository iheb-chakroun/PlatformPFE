package interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import entities.documents.Thesis;
import entities.users.Teacher;

@Remote
public interface TeacherBusinessRemote {
	
	List<Teacher> getValidTeachers(Thesis thesis, Date date);

}
