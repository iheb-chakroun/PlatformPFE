package utilities;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.json.JsonObject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import entities.tracking.Location;
import entities.users.Student;

@Stateless
public class MapBusiness {
	
	@PersistenceContext(unitName="platform")
	EntityManager em;
	
	public List<Location> getLocationsForStudents() {
		List<Location> result = new ArrayList<>();
		Client client = ClientBuilder.newClient();
		this.getAcceptedStudents().forEach(e -> {
			Location l = new Location();
			String location = e.getPfeFile().getEntreprise().getAddress()+" "+e.getPfeFile().getEntreprise().getCountry();
			WebTarget target = client.target("https://api.mapbox.com/geocoding/v5/mapbox.places/"+location+".json?access_token=pk.eyJ1IjoibWF0dGZpY2tlIiwiYSI6ImNqNnM2YmFoNzAwcTMzM214NTB1NHdwbnoifQ.Or19S7KmYPHW8YjRz82v6g&cachebuster=1572867777940&autocomplete=true&types=place%2Clocality%2Cpoi%2Caddress%2Cregion%2Ccountry");
			JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
			l.setFname(e.getFirstName());
			l.setLname(e.getLastName());
			l.setEntrepriseName(e.getPfeFile().getEntreprise().getWebsite());
			l.setAddress(location);
			l.setLatitude(response.getJsonArray("features").getJsonObject(0).getJsonArray("center").get(1).toString());
			l.setLongitude(response.getJsonArray("features").getJsonObject(0).getJsonArray("center").get(0).toString());
			result.add(l);
			System.out.println(response);
		});
		
		return result;
	}

	public List<Student> getAcceptedStudents() {
		TypedQuery<Student> q = em.createQuery("select e from Student e where e.pfeFile.status = 1", Student.class); 
		return q.getResultList(); 
	}
}
