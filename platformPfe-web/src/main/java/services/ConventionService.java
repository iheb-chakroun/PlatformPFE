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
import javax.ws.rs.core.Response.Status;

import entities.documents.Convention;
import interfaces.ConventionLocal;
@Path("convention")

public class ConventionService {
	@EJB
	ConventionLocal ConventionService;

	@GET
	@Produces("application/json")
	public Response getAllConvention() {

		if (ConventionService.getAllConvention() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (ConventionService.getAllConvention().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(ConventionService.getAllConvention(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getConventionById(@PathParam("id") int id) {
		System.out.print(ConventionService.findConventionisById(id));
		if (ConventionService.findConventionisById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(ConventionService.findConventionisById(id), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")

	public Response AjouterConvention(Convention c) {
		try {

			ConventionService.ajouterConvention(c);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}

	@DELETE
	@Path("{id}")

	public Response SupprimerConvention(@PathParam (value="id") int id) {
		
		
		
		if(ConventionService.deleteConventionById(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("{id}")

	public Response modifierEntreprise(@PathParam (value="id") int id , Convention e) {
		
		
		
		if(ConventionService.updateConvention(e) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
}
