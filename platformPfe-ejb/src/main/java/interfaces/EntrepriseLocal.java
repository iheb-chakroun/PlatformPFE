package interfaces;

import java.util.List;

import entities.documents.Entreprise;

public interface EntrepriseLocal {

	boolean deleteEntrepriseById(int EntrepriseId);

	List<Entreprise> getAllEntreprise();

	 Entreprise findEntrepriseById(int id) ;

	 boolean updateEntreprise(Entreprise E);

	void ajouterEntreprise(Entreprise E);
}