package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.ChangeSupervisorRequest;
import entities.users.Teacher;

@Remote
public interface ChangeSupervisorRequestRemote {

	public List<Teacher> getAllActiveForSupervisingTeachers();
	
	public boolean requestSupervisorchange(int studentId, ChangeSupervisorRequest c);
	
	public boolean acceptSupervisorchange(int id);
	
	public boolean refuseSupervisorchange(int id);
	
	public boolean assignSupervisorchange(int studentId,int techerId);
	
	
}
