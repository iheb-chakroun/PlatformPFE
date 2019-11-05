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

import entities.documents.PfeFileChange;
import interfaces.PfeFileChangeLocal;
import interfaces.PfeFileLocal;

@Path("pfeModification")

public class PfeFileModification {

	@EJB
	PfeFileChangeLocal service;

	
	@POST

	@Consumes("application/json")
	public Response ajouterChange(PfeFileChange p ) {

		try {

			service.ajouterPfeFileChange(p);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}
	
	
	@GET
	@Produces("application/json")
	public Response getAllChanges() {

		if (service.getAllChanges() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (service.getAllChanges().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(service.getAllChanges(), MediaType.APPLICATION_JSON).build();

	}

	@DELETE
	@Path("{id}")

	public Response SupprimerPfeFileChange(@PathParam (value="id") int id) {
		
		
		
		if(service.deletePfeFileChangeById(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getPfeFileChangeById(@PathParam("id") int id) {
		System.out.print(service.findPfeChangeById(id));
		if (service.findPfeChangeById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(service.findPfeChangeById(id), MediaType.APPLICATION_JSON).build();

	}
	
	

	@PUT
	@Path("/validate/{id}")
	@Produces("application/json")
	public Response Validate(@PathParam (value="id") int id ) {

		if(service.ValidatePfeFileChange(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	@PUT
	@Path("/refuse/{id}")
	@Produces("application/json")
	public Response Refuse(@PathParam (value="id") int id ) {

		if(service.RefusePfeFileChange(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	
	
}
