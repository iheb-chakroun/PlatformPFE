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

import business.SchoolBusiness;
import entities.administration.School;

@Path("school")
public class SchoolService {

	@EJB
	SchoolBusiness schoolBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterSchool(School school) {
		try {
			schoolBusiness.addSchool(school);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerSchool() {
		return Response.ok(schoolBusiness.getAllSchool(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerSchoolParId(@PathParam("id") int id) {
		return Response.ok(schoolBusiness.getSchoolById(id), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response modifierSchool(School s, @PathParam("id") int id) {
		schoolBusiness.updateSchool(s);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerSchool(@PathParam(value = "id") int id) {
		if (schoolBusiness.deleteSchool(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}

}
