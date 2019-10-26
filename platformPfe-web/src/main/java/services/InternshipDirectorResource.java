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

import entities.users.InternshipDirector;
import interfaces.InternshipDirectorRemote;

@Path("intern-dir")
public class InternshipDirectorResource {
	@EJB
	InternshipDirectorRemote directorBusiness;
	
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
	
	

}
