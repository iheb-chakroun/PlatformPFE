package interfaces;

import java.util.List;

import collection.Role;
import entities.documents.Thesis;

public interface ThesisLocal {

	Thesis findThesisById(int id);

	boolean deleteThesisById(int ThesisId);

	void ajouterThesis(Thesis p);

	List<Thesis> getAllThesis();

	boolean updateThesis(Thesis p);

boolean AjouterTeacher(int idThesis, int idTeacher, Role r);

}
