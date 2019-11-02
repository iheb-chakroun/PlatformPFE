package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Classroom;

@Remote
public interface ClassroomBusinessRemote {
	void addClassroom(Classroom classroom);
	void updateClassrom(Classroom classroom);
	boolean deleteClassroom(int id);
	Classroom getClassroomById(int id);
	List<Classroom> getClassrooms();
	

}
