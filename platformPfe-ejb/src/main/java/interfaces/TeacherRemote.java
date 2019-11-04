package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.users.Teacher;
import entities.users.TeacherRole;

@Remote
public interface TeacherRemote {
	
	public int addTeacher(Teacher teacher);
	public void removeTeacher(int id);
	public void updateTeacher(Teacher teacher);
	public Teacher findTeacherById(int id);
	public List<Teacher> findAllTeachers();
	public int addTeacherRole(int id_teacher, int id_pfe);

}
