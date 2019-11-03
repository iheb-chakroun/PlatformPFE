package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.School;
import entities.administration.Site;
import entities.users.Admin;

@Local
public interface SchoolBusinessLocal {
	int addSchool(School school);

	School getSchoolById(int id);

	List<School> getAllSchool();

	void updateSchool(School school);

	boolean deleteSchool(int id);

	Admin getSchoolAdmin(int idSchool);

	List<Site> getSchoolSites(int idSchool);
	
	boolean assignAdminToSchool(int isSchool, int idAdmin);

}
