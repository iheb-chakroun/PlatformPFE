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

import entities.documents.PfeFile;
import entities.documents.Thesis;
import interfaces.PfeFileLocal;
import interfaces.ThesisLocal;
@Path("thesis")
public class ThesisService {
	@EJB
	ThesisLocal thesisService;

	@GET
	@Produces("application/json")
	public Response getAllThesis() {

		if ( thesisService.getAllThesis() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (thesisService.getAllThesis().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(thesisService.getAllThesis(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getThesisById(@PathParam("id") int id) {
		System.out.print(thesisService.findThesisById(id));
		if (thesisService.findThesisById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(thesisService.findThesisById(id), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")

	public Response AjouterThesis(Thesis p) {
		try {

			thesisService.ajouterThesis(p);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}

	@DELETE
	@Path("{id}")

	public Response SupprimerThesis(@PathParam (value="id") int id) {
		
		
		
		if(thesisService.deleteThesisById(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("{id}")

	public Response modifierThesis(@PathParam (value="id") int id , Thesis p) {
		
		
		
		if(thesisService.updateThesis(p) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
}
