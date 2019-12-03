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

import business.DepartementBusiness;
import entities.administration.Departement;

@Path("department")
public class DepartementService {
	
	@EJB
	DepartementBusiness departementBusiness;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerDepartments() {
		return Response.ok(departementBusiness.getAllDepartement(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerDepartementParId(@PathParam("id") int id) {
		return Response.ok(departementBusiness.getDepartementUsingId(id), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}/teachers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerTeachersDepartementId(@PathParam("id") int id) {
		return Response.ok(departementBusiness.getTeacherList(id), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}/options")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerOptionsDepartementId(@PathParam("id") int id) {
		return Response.ok(departementBusiness.getOptionList(id), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("{id}/classrooms")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClassroomsDepartementId(@PathParam("id") int id) {
		return Response.ok(departementBusiness.getClassroomList(id), MediaType.APPLICATION_JSON).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterDepartment(Departement dep) {
		try {
			departementBusiness.addDepartement(dep);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifierDep(Departement dep) {
		departementBusiness.updateDepartement(dep);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerDep(@PathParam(value = "id") int id) {
		if (departementBusiness.deleteDepartement(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}
	

}
