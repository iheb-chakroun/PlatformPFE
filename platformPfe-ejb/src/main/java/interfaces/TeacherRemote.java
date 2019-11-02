package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.Employe;
import entities.users.Teacher;

@Remote
public interface TeacherRemote {
	
	public int addTeacher(Teacher teacher);
	public void removeTeacher(int id);
	public void updateTeacher(Teacher teacher);
	public Teacher findTeacherById(int id);
	public List<Teacher> findAllTeachers();

}
