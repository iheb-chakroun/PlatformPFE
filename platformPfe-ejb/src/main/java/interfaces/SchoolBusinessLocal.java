package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.School;

@Local
public interface SchoolBusinessLocal {
	public int addSchool(School school);
	public School getSchoolById(int id);
	public List<School> getAllSchool();
	public void updateSchool(School school);
	public boolean deleteSchool(int id);

}
