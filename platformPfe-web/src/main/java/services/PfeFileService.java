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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import entities.documents.PfeFile;
import entities.users.DepartementHead;
import entities.users.Teacher;
import interfaces.PfeFileLocal;
import interfaces.TeacherRemote;
@Path("pfe")
public class PfeFileService {
	@EJB
	PfeFileLocal pfeService;
	@EJB
	TeacherRemote teacherBusiness;

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
	
	
	
	/*------------Internship director-----------*/
	//@Author: khaled
	@GET
	@Path("/filtred")
	@Produces("application/json")
	@Consumes("application/json")
	public Response getSortedPfe(String... ops) {
		return Response.ok(pfeService.filterPfe(ops), MediaType.APPLICATION_JSON).build();
	}
	
	//@author: khaled
	@GET
	@Path("/NonTreated")
	@Produces("application/json")
	public Response getNonPfe() {
		return Response.ok(pfeService.filterPfeNonTreated(), MediaType.APPLICATION_JSON).build();
	}
	//@author: khaled
	@GET
	@Path("/planification")
	@Produces("application/json")
	public Response getPendingPfePlans() {
		if(pfeService.getPedingFilesPlanification().isEmpty()) {
			String message = "{\"message\": \"No pending pfe for thesis defense planification was found!\"}";
			return Response.status(Response.Status.OK).entity(message).build();
		}
		return Response.ok(pfeService.getPedingFilesPlanification(), MediaType.APPLICATION_JSON).build();
	}	
	
	//@author: khaled
	@POST
	@Path("/approval")
	@Consumes("application/json")
	@Produces("application/json")
	public Response accepterPfe(PfeFile pfe) {
		if(pfe == null) {
			String message = "{\"message\": \"No content was entred!\"}";
			return Response.status(Response.Status.NO_CONTENT).entity(message).build();
		}
		DepartementHead dep = pfeService.accepterPfe(pfe.getId());
		String message = "{\"message\":\"The pfe is approved and Now "+dep.getFirstName()+" "+dep.getLastName()+" is the moderator of pfe number: "+pfe.getId()+"\"}";
		return Response.status(Status.CREATED).entity(message).build();

	}
	
	//@author: khaled
	@POST
	@Consumes("application/json")
	@Produces("application/json")
	public Response refusePfe(@QueryParam("id") int id,@QueryParam("reason") String reason) {
		pfeService.refusePfe(id, reason);
		String message = "{\"message\": \"Pfe file declined sucessufully\"}";
		 
	    return Response
	      .status(Response.Status.OK)
	      .entity(message)
	      .type(MediaType.APPLICATION_JSON)
	      .build();
	}
	
	//@author: khaled
	@POST
	@Path("/report")
	@Consumes("application/json")
	@Produces("application/json")
	public Response authorizeStudent(PfeFile input) {
		if(input == null) {
			String message = "{\"message\": \"No content!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
		PfeFile pfe = pfeService.findPfeById(input.getId());
		if(!pfe.isReportDeposite()) {
			pfeService.approveReport(pfe.getId());
			String message = "{\"message\": \"Report submitted successfully!\"}";
			return Response.status(Response.Status.OK).entity(message).build();
		}else {
			pfeService.approveReport(pfe.getId());
			String message = "{\"message\": \"Report not deposited yet!\"}";
			return Response.status(Response.Status.OK).entity(message).build();
		}
	}
	//@author: khaled
		@POST
		@Path("/validator")
		@Consumes("application/json")
		@Produces("application/json")
		public Response asignReporter(PfeFile input) {
			if(input == null) {
				String message = "{\"message\": \"No content!\"}";
				return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
			}
			Teacher teacher = teacherBusiness.findTeacherById(input.getPre_validator().getId());
			if(pfeService.asignReporter(input.getId(), teacher.getId())) {
				String message = "{\"message\": \"The rapporteur: "+teacher.getFirstName()+" "+teacher.getLastName()+" asigned to pfe number: "+input.getId()+"!\"}";
				return Response.status(Response.Status.OK).entity(message).build();
			}
			String message = "{\"message\": \"The student didn't deposit his report yet!\"}";
			return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
		}
	/*----------------------------------------------------------------------*/
	
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
			return Response.status(Status.OK).build(); 
			}	
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
}
