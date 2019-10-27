package interfaces;

import java.util.List;

import javax.ejb.Remote;

import entities.administration.Departement;

@Remote
public interface DepartementRemote {
	public int addDepartement(Departement departement);
	public Departement getDepartementById(int id);
	public List<Departement> getAllDepartement();
	public void updateDepartement(Departement departement);
	public boolean deleteDepartement(int id);

}
