package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Classroom;
import entities.administration.Departement;
import entities.administration.Option;
import entities.users.Teacher;

@Local
public interface DepartementLocal {
	int addDepartement(Departement departement);

	Departement getDepartementById(int id);

	List<Departement> getAllDepartement();

	void updateDepartement(Departement departement);

	boolean deleteDepartement(int id);

	List<Option> getOptionList(int idDep);

	List<Teacher> getTeacherList(int idDep);

	List<Classroom> getClassroomList(int idDep);

	boolean affecterSite(int idSite, int idDep);

	boolean affecterDepartementHead(int idDepartementHead, int idDep);

}
