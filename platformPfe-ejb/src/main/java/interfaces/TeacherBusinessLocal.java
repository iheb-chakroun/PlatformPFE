package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.users.Teacher;

@Local
public interface TeacherBusinessLocal {

	List<Teacher> findAllTeachers();
	
}
