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

import business.OptionBusiness;
import entities.administration.Option;

@Path("option")
public class OptionService {

	@EJB
	OptionBusiness optionBusiness;

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterOption(Option option) {
		try {
			optionBusiness.addOption(option);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerOption() {
		return Response.ok(optionBusiness.getAllOption(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerOptionParId(@PathParam("id") int id) {
		return Response.ok(optionBusiness.getOptionById(id), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response modifierOption(Option o, @PathParam("id") int id) {
		optionBusiness.updateOption(o);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerOption(@PathParam(value = "id") int id) {
		if (optionBusiness.deleteOption(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}

}
