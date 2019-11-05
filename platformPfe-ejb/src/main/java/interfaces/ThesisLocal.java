package interfaces;

import java.util.List;

import entities.documents.PfeFile;
import entities.documents.Thesis;

public interface ThesisLocal {

	Thesis findThesisById(int id);

	boolean deleteThesisById(int ThesisId);

	void ajouterThesis(Thesis p);

	List<Thesis> getAllThesis();

	boolean updateThesis(Thesis p);

	void plan(PfeFile pfeFile);

}