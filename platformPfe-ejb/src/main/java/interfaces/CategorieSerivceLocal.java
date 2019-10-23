package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.documents.Categorie;

@Local
public interface CategorieSerivceLocal {


	public boolean addCategorie( Categorie categorie);
	public boolean deleteCategorie(int idcategorie);
	public List<Categorie> getAllCategories();
	public boolean ModifyCategorie(Categorie categorie);

	
}
