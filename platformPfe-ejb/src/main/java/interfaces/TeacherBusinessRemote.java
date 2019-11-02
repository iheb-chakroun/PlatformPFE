package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.Teacher;

@Remote
public interface TeacherBusinessRemote {
	
	
	List<Teacher> findAllTeachers();
}
