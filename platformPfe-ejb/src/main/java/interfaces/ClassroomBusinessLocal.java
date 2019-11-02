package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Classroom;

@Local
public interface ClassroomBusinessLocal {
	void addClassroom(Classroom classroom);
	void updateClassrom(Classroom classroom);
	boolean deleteClassroom(int id);
	Classroom getClassroomById(int id);
	List<Classroom> getClassrooms();

}
