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

import entities.users.DepartementHead;
import interfaces.DepartmentHeadRemote;

@Path("head-dep")
public class DepartmentHeadResource {
	@EJB
	DepartmentHeadRemote headBusiness;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addHead(DepartementHead dh) {
		if(dh == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		headBusiness.addDepartmentHead(dh);
		return Response.status(Response.Status.CREATED).entity(dh).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateHead(DepartementHead dh) {
		if(dh == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		headBusiness.updateDepartmentHead(dh);
		return Response.status(Response.Status.ACCEPTED).entity(dh).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findHead(@PathParam("id")int id) {
		return Response.ok(headBusiness.findDepartmentHeadById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteHead(@PathParam("id") int id) {
		String deleted = "Employe deleted :"+id;
		headBusiness.removeDepartmentHead(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllHeads() {
		return Response.ok(headBusiness.findDepartmentHeads(), MediaType.APPLICATION_JSON).build();
	}


}
