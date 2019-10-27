package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.School;

@Remote
public interface SchoolBusinessRemote {
	public int addSchool(School school);
	public School getSchoolById(int id);
	public List<School> getAllSchool();
	public void updateSchool(School school);
	public boolean deleteSchool(int id);
}
