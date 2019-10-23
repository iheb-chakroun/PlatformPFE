package services;

import javax.ejb.EJB;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.documents.Categorie;
import interfaces.CategorieSerivceLocal;

@Path("categorie")
public class CategorieService {

	@EJB
	CategorieSerivceLocal CategorieBusiness;
	
	@GET
	@Produces("application/json")
	public Response getAllCategories() {

		if (CategorieBusiness.getAllCategories() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (CategorieBusiness.getAllCategories().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(CategorieBusiness.getAllCategories(), MediaType.APPLICATION_JSON).build();

	}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addCategorie (Categorie categorie) {
		try {
			
			CategorieBusiness.addCategorie(categorie);
			return Response.status(Status.CREATED).build();

		}
		catch(Exception ex) {
			
			return Response.status(Status.NOT_ACCEPTABLE).build();
				}
		}
	
	@DELETE
	@Path("delete/{id}")
	public Response SupprimerEntreprise(@PathParam (value="id") int id) {
		
		if(CategorieBusiness.deleteCategorie(id) ) {
			return Response.status(Status.OK).build(); }

	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("modify/{id}")

	public Response modifierEntreprise(@PathParam (value="id") int id , Categorie c) {
		
			if(CategorieBusiness.ModifyCategorie(c) ) {
			return Response.status(Status.OK).build(); }
		return Response.status(Status.BAD_REQUEST).build();

}
	
	
}
