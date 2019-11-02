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

import entities.users.Admin;
import interfaces.AdminRemote;
import utilities.EmailBusiness;

@Path("admin")
public class AdminResource {
	
	@EJB
	AdminRemote adminBusiness;
	
	EmailBusiness eb = new EmailBusiness();
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addAdmin(Admin admin) {
		if(admin == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		adminBusiness.addAdmin(admin);
		return Response.status(Response.Status.CREATED).entity(admin).build();
		
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateAdmin(Admin admin) {
		if(admin == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		adminBusiness.updateAdmin(admin);
		return Response.status(Response.Status.ACCEPTED).entity(admin).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findAdmin(@PathParam("id") int id) {
		return Response.ok(adminBusiness.findAdminById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteAdmin(@PathParam("id") int id) {
		String deleted = "Employe deleted :"+id;
		adminBusiness.removeAdmin(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllAdmins() {
		return Response.ok(adminBusiness.findAllAdmins(), MediaType.APPLICATION_JSON).build();
	}

}
