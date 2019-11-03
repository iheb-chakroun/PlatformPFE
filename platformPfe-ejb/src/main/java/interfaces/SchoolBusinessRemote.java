package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.School;
import entities.administration.Site;
import entities.users.Admin;

@Remote
public interface SchoolBusinessRemote {
	int addSchool(School school);

	School getSchoolById(int id);

	List<School> getAllSchool();

	void updateSchool(School school);

	boolean deleteSchool(int id);

	Admin getSchoolAdmin(int idSchool);

	List<Site> getSchoolSites(int idSchool);
	
	boolean assignAdminToSchool(int isSchool, int idAdmin);
}
