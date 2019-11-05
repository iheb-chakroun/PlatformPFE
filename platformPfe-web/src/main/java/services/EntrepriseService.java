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

import entities.documents.Entreprise;
import interfaces.EntrepriseLocal;

@Path("entreprise")
public class EntrepriseService {

	@EJB
	EntrepriseLocal EntrepriseService;

	@GET
	@Produces("application/json")
	public Response getAllEntreprise() {

		if (EntrepriseService.getAllEntreprise() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (EntrepriseService.getAllEntreprise().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(EntrepriseService.getAllEntreprise(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getEntrepriseById(@PathParam("id") int id) {
		System.out.print(EntrepriseService.findEntrepriseById(id));
		if (EntrepriseService.findEntrepriseById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(EntrepriseService.findEntrepriseById(id), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")

	public Response AjouterEntreprise(Entreprise entreprise) {
		try {

			EntrepriseService.ajouterEntreprise(entreprise);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}

	@DELETE
	@Path("{id}")

	public Response SupprimerEntreprise(@PathParam (value="id") int id) {
		
		
		
		if(EntrepriseService.deleteEntrepriseById(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("{id}")

	public Response modifierEntreprise(@PathParam (value="id") int id , Entreprise e) {
		
		
		
		if(EntrepriseService.updateEntreprise(e) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
}