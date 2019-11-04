package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface StaticsLocal {
	public float percentageEntreprisePfeFilesByYear(String name,String year)  ;
	public Map<String, Integer> percentageEvoluation(String country);
	public Map<String,Integer> getCategoriesOrder();
	public Map<String,Integer> EvoluationNumberCategoryByYear(String cat);
}
