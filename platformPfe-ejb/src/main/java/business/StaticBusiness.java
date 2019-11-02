package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sound.midi.Soundbank;

import interfaces.StaticsLocal;
import interfaces.StaticsRemote;
@Stateless
@LocalBean
public class StaticBusiness implements StaticsLocal,StaticsRemote {
	@PersistenceContext(unitName="platform") 
	EntityManager em;

	@Override
	public List<Long> percentageEntreprisePfeFilesByYear(String name, String year) {
		Query query=em.createQuery("Select Count(entreprise_id)/(Select Count(id) From PfeFile p where p.student.classe.scholarYear=:y)*100 from PfeFile p join p.entreprise e where e.country=:c and p.student.classe.scholarYear=:y", Long.class);
		
		query.setParameter("c", name);
		query.setParameter("y",year);
		List<Long> percentageList =query.getResultList();
		System.out.println(percentageList);
		return percentageList;
	}

	@Override
	public Map<String,Integer> getCategoriesOrder() {
		Query query=em.createQuery("Select label from Categorie");
		List<String> categorieList=query.getResultList();
		System.out.println(categorieList);
		Map<String,Integer> categorieMap = new HashMap<>();
		for(int i=0;i<categorieList.size();i++) {
		Query query2=em.createQuery("Select Count(p.id) as description from PfeFile p join p.categories c where c.label='"+categorieList.get(i)+"'");
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

	
	

	
	

}
