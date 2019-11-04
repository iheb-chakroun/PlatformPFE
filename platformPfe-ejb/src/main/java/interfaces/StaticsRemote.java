package interfaces;


import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface StaticsRemote {
	public float percentageEntreprisePfeFilesByYear(String name,String year);
	public Map<String,Integer> percentageEvoluation(String country);
	public Map<String, Integer>getCategoriesOrder();
	public Map<String,Integer> EvoluationNumberCategoryByYear(String cat);
	

}
