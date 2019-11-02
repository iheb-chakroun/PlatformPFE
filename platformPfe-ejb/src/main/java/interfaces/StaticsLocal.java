package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

@Local
public interface StaticsLocal {
	public List<Long> percentageEntreprisePfeFilesByYear(String name,String year)  ;
	public Map<String,Integer> getCategoriesOrder();
}
