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

import business.ClassBusiness;
import entities.administration.Class;;

@Path("class")
public class ClassService {
	@EJB
	ClassBusiness classBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterClass(Class classe) {
		try {
			classBusiness.addClass(classe);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClasss() {
		return Response.ok(classBusiness.getAllClass(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerClassParId(@PathParam("id") int id) {
		return Response.ok(classBusiness.getClassById(id), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response modifierClass(Class classe) {
		classBusiness.update(classe);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerClass(@PathParam(value = "id") int id) {
		if (classBusiness.deleteClass(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}
}
