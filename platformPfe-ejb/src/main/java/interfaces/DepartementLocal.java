package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.administration.Departement;

@Local
public interface DepartementLocal {
	public int addDepartement(Departement departement);
	public Departement getDepartementById(int id);
	public List<Departement> getAllDepartement();
	public void updateDepartement(Departement departement);
	public boolean deleteDepartement(int id);

}
