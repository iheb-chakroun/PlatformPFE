package interfaces;

import java.util.List;

import entities.documents.Thesis;

public interface ThesisLocal {
Thesis findThesisById(int id);

void ajouterThesis(Thesis p);

List<Thesis> getAllThesis();

boolean updateThesis(Thesis p);

boolean deleteThesisById(int ThesisId);

}
