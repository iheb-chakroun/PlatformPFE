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

import entities.documents.Categorie;
import entities.documents.Convention;
import interfaces.ConventionLocal;
import interfaces.categorieLocal;
@Path("Categorie")

public class CategorieService {
	@EJB
	categorieLocal CategorieService;

	@GET
	@Produces("application/json")
	public Response getAllCategorie() {

		if (CategorieService.getAllCategorie() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (CategorieService.getAllCategorie().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(CategorieService.getAllCategorie(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getCategorieById(@PathParam("id") int id) {
		System.out.print(CategorieService.findCategorieisById(id));
		if (CategorieService.findCategorieisById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(CategorieService.findCategorieisById(id), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")

	public Response AjouterCategorie(Categorie c) {
		try {

			CategorieService.ajouterCategorie(c);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}

	@DELETE
	@Path("{id}")

	public Response SupprimerCategorie(@PathParam (value="id") int id) {
		
		
		
		if(CategorieService.deleteCategorieById(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("{id}")

	public Response modifierCategorie(@PathParam (value="id") int id , Categorie e) {
		
		
		
		if(CategorieService.updateCategorie(e) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
}
