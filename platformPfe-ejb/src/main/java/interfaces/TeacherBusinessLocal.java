package interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import entities.documents.Thesis;
import entities.users.Teacher;

@Local
public interface TeacherBusinessLocal {
	List<Teacher> getValidTeachers(Thesis thesis, Date date);

}
