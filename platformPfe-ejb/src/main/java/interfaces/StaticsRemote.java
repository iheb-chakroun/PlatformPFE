package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface StaticsRemote {
	public List<Long> percentageEntreprisePfeFilesByYear(String name,String year);
	public Map<String,Integer> getCategoriesOrder();
	

}
