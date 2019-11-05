package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.documents.Categorie;
import entities.documents.Convention;


@Local
public interface categorieLocal {

	Categorie findCategorieisById(int id);

	boolean deleteCategorieById(int Id);

	void ajouterCategorie(Categorie p);

	List<Categorie> getAllCategorie();

	boolean updateCategorie(Categorie p);

}
