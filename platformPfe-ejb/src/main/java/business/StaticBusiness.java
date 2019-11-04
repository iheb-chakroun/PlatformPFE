package business;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entities.documents.PfeFile;
import interfaces.StaticsLocal;
import interfaces.StaticsRemote;
@Stateless
@LocalBean
public class StaticBusiness implements StaticsLocal,StaticsRemote {
	@PersistenceContext(unitName="platform") 
	EntityManager em;

	@Override
	public Map<String,Integer> getCategoriesOrder() {
		Query query=em.createQuery("Select label from Categorie");
		List<String> categorieList=query.getResultList();
		System.out.println(categorieList);
		Map<String,Integer> categorieMap = new HashMap<>();
		for(int i=0;i<categorieList.size();i++) {
		Query query2=em.createQuery("Select Count(p.id) as nb from PfeFile p join p.categories c where c.label='"+categorieList.get(i)+"'");
		categorieMap.put(categorieList.get(i),Integer.valueOf(query2.getResultList().get(0).toString()));
	
		}
		System.out.println("UnSorted...");
		System.out.println(categorieMap);
		Map<String, Integer> sortedCategories = new LinkedHashMap<>();
        categorieMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(x -> sortedCategories.put(x.getKey(), x.getValue()));
		System.out.println("Sorted...");
        System.out.println(sortedCategories);
		return sortedCategories;
	}

	@Override
	public Map<String, Integer> percentageEvoluation(String country) {
		Query query=em.createQuery("Select scholarYear from Class");
		List<String> yearList=query.getResultList();
		Map<String,Integer> countMap = new HashMap<>();
		for(int i=0;i<yearList.size();i++) {
		float percentage=percentageEntreprisePfeFilesByYear(country,yearList.get(i).toString());
		countMap.put(yearList.get(i),(int) percentage);
		}
		return countMap;
	}

	@Override
	public Map<String, Integer> EvoluationNumberCategoryByYear(String cat) {
		Query query=em.createQuery("Select scholarYear from Class");
		List<String> yearList=query.getResultList();
		Map<String,Integer> countMap = new HashMap<>();
		for(int i=0;i<yearList.size();i++) {
			Query query2=em.createQuery("Select Count(p.id) as nb from PfeFile p join p.categories c where c.label='"+cat+"' and  p.student.classe.scholarYear='"+yearList.get(i)+"'");
			countMap.put(yearList.get(i).toString(),Integer.valueOf(query2.getResultList().get(0).toString()));
		}
		return countMap;
	}

	@Override
	public float percentageEntreprisePfeFilesByYear(String name, String year) {
		/*Query query=em.createQuery("Select Count(entreprise_id)/(Select Count(id) From PfeFile p"
		+ " where p.student.classe.scholarYear=:y)*100 from PfeFile p join p.entreprise e "
		+ "where e.country=:c and p.student.classe.scholarYear=:y", Long.class);*/
		Query query1=em.createQuery("Select p from PfeFile p join p.entreprise e where e.country=:c and p.student.classe.scholarYear=:y ");
		Query query2=em.createQuery("Select p From PfeFile p where p.student.classe.scholarYear=:y");
		query1.setParameter("c", name);
		query1.setParameter("y",year);
		query2.setParameter("y",year);
		List<PfeFile> percentageList =query1.getResultList();
		List<PfeFile> percentageTotale =query2.getResultList();
		if(percentageList.size()==0) {
			return 0;
		}
		else
		return (percentageList.size()/percentageTotale.size())*100;
			}
	



	
	
	
	
	
	
	

	
	

}
