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

import business.SiteBusiness;
import entities.administration.Site;


@Path("site")
public class SiteService {
	
	@EJB
	SiteBusiness siteBusiness;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response ajouterSite(Site site) {
		try {
			siteBusiness.addSite(site);
			return Response.status(Status.CREATED).build();
		} catch (Exception e) {
			return Response.status(Status.NOT_ACCEPTABLE).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerSite() {
		return Response.ok(siteBusiness.getAllSite(), MediaType.APPLICATION_JSON).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerSiteParId(@PathParam("id") int id) {
		return Response.ok(siteBusiness.getSiteById(id), MediaType.APPLICATION_JSON).build();
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Response modifierSite(Site site, @PathParam("id") int id) {
		siteBusiness.updateSite(site);
		return Response.status(Status.OK).build();
	}

	@DELETE
	@Path("{id}")
	public Response supprimerSite(@PathParam(value = "id") int id) {
		if (siteBusiness.deleteSite(id)) {
			return Response.status(Status.OK).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();

	}

}
