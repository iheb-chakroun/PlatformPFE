package services;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entities.administration.Site;
import entities.users.InternshipDirector;
import interfaces.InternshipDirectorRemote;
//import utilities.MapBusiness;
import utilities.Roles;
import utilities.Secured;

@Path("intern-dir")
public class InternshipDirectorResource {
	@EJB
	InternshipDirectorRemote directorBusiness;
	/*@EJB
	MapBusiness useMap;*/
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAdmin(InternshipDirector director) {
		if(director == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		directorBusiness.addDirector(director);
		return Response.status(Response.Status.CREATED).entity(director).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateAdmin(InternshipDirector director) {
		if(director == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		directorBusiness.updateDirector(director);
		return Response.status(Response.Status.ACCEPTED).entity(director).build();
	}
	
	//@author:khaled Requirement 8
	/*@Secured(Roles.DIRECTEURINTERNSHIPS)
	@PUT
	@Path("/maximum")
	@Consumes("application/json")
	@Produces("application/json")
	public Response fixNumbers(Site site) {
		if(site == null) {
			String message = "{\"message\": \"No content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		directorBusiness.fixNumbers(site);
		String message = "{\"message\": \"Each teacher now can be a rapporteur, supervisor, president and prevalidator for:"
		+site.getNmarapporteur()+" times, "+site.getNmasupervisor()+" times, "+site.getNmapresident()+" times, "+site.getNmavalidator()+" times. \"}";
		return Response.status(Response.Status.ACCEPTED).entity(message).build();
	}*/
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findDirector(@PathParam("id")int id) {
		return Response.ok(directorBusiness.findDirectorById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteDirector(@PathParam("id") int id) {
		String deleted = "Employe deleted :"+id;
		directorBusiness.removeDirector(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllDirectors() {
		return Response.ok(directorBusiness.findAllDirectors(), MediaType.APPLICATION_JSON).build();
	}
	/*-------------------------------------------MAPBOX--------------------------------------------------*/
	//@Author: khaled Requirement 11 (API + METIER)
	/*@Secured(Roles.DIRECTEURINTERNSHIPS)
	@GET
	@Path("map")
	@Consumes("application/json")
	@Produces("application/json")
	public Response findLocations() {
		/*String data = "{ ... }";
		Object json = new JSONTokener(data).nextValue();
		if (json instanceof JSONObject)
		  //you have an object
		else if (json instanceof JSONArray)*/
	//	return Response.ok(useMap.getLocationsForStudents(), MediaType.APPLICATION_JSON).build();
	//}
	
	@GET
	@Path("available")
	@Produces("application/json")
	public Response findAvaiDir() {
		return Response.ok(directorBusiness.findAvailable(), MediaType.APPLICATION_JSON).build();
	}

}
