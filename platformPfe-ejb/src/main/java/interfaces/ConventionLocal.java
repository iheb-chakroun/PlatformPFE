package interfaces;

import java.util.List;

import javax.ejb.Local;

import entities.documents.Convention;

@Local
public interface ConventionLocal {

	Convention findConventionisById(int id);

	boolean deleteConventionById(int Id);

	void ajouterConvention(Convention p);

	List<Convention> getAllConvention();

	boolean updateConvention(Convention p);

}
