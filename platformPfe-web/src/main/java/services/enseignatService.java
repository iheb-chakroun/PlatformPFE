package services;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import collection.Role;
import interfaces.enseignantServiceLocal;

@Path("teacher")
public class enseignatService {

	@EJB
	enseignantServiceLocal enseignatBusiness;
	
	
	@GET
	@Path("getnrole/{id}/{r}/{year}")
	@Produces("application/json")
	public Response getnbactions(@PathParam("id") int id,@PathParam("r") Role r,@PathParam("year") String year) {

	

		if (enseignatBusiness.getnbroleyear(id,r,year) == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(enseignatBusiness.getnbroleyear(id,r,year), MediaType.APPLICATION_JSON).build();

	}
	
	@GET
	@Path("pick/{id}/{ids}")
	@Produces("application/json")
	public Response pickcategory(@PathParam("id") int id,@PathParam("ids") String ids) {

	

		if (enseignatBusiness.pickcategory(id,ids)) {
			return Response.status(Status.OK).build(); }
	return Response.status(Status.BAD_REQUEST).build();

	}
	
}
