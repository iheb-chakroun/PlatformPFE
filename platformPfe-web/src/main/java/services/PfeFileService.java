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
import entities.documents.PfeFile;
import interfaces.EntrepriseLocal;
import interfaces.PfeFileLocal;
@Path("pfe")
public class PfeFileService {
	@EJB
	PfeFileLocal pfeService;

	@GET
	@Produces("application/json")
	public Response getAllPfe() {

		if (pfeService.getAllPfeFile() == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		if (pfeService.getAllPfeFile().size() == 0)
			return Response.status(Response.Status.NO_CONTENT).entity("Pas de contenu").build();

		else
			return Response.ok(pfeService.getAllPfeFile(), MediaType.APPLICATION_JSON).build();

	}

	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response getPfeById(@PathParam("id") int id) {
		System.out.print(pfeService.findPfeById(id));
		if (pfeService.findPfeById(id) == null)
			return Response.status(Response.Status.NOT_FOUND).build();

		else

			return Response.ok(pfeService.findPfeById(id), MediaType.APPLICATION_JSON).build();

	}

	@POST
	@Consumes("application/json")
	@Produces("application/json")

	public Response AjouterPfe(PfeFile p) {
		try {

			pfeService.ajouterPfeFile(p);
			return Response.status(Status.CREATED).build();

		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();

		}
	}

	@DELETE
	@Path("{id}")

	public Response SupprimerPfe(@PathParam (value="id") int id) {
		
		
		
		if(pfeService.deletePfeFileById(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	
	@PUT
	@Consumes("application/json")
	@Path("{id}")

	public Response modifierPfe(@PathParam (value="id") int id , PfeFile p) {
		
		
		
		if(pfeService.updatePfeFile(p) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.BAD_REQUEST).build();

}
	@PUT
	@Path("/validate/{id}")
	@Produces("application/json")
	public Response ToValid(@PathParam (value="id") int id ) {


		if(pfeService.ToValidStatusPfe(id) ) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	@PUT
	@Path("/refuse/{id}")
	@Produces("application/json")
	public Response ToUnValid(@PathParam (value="id") int id ,String cause) {


		if(pfeService.ToRefusedStatusPfe(id , cause)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	@PUT
	@Path("/process/{id}")
	@Produces("application/json")
	public Response toProcess(@PathParam (value="id") int id ) {


		if(pfeService.ToProcessStatusPfe(id)) {
			return Response.status(Status.OK).build(); }
		
	
	return Response.status(Status.NOT_ACCEPTABLE).build();

	}
	

	@GET
	@Path("supervisor/{id}")
	@Produces("application/json")
	public Response getSupervisor(@PathParam("id") int id) {
		
return Response.ok(pfeService.supervisorName(id), MediaType.APPLICATION_JSON).build();


	}
}
