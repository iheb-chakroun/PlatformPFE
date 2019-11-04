package services;

import javax.ejb.EJB;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
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
	/*-------------------------------------------MAPBOX--------------------------------------------------*/
	@GET
	@Path("map")
	@Consumes("application/json")
	@Produces("application/json")
	public Response findLocations() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("https://api.mapbox.com/geocoding/v5/mapbox.places/10.730221855505988%2C36.46140285304264.json?access_token=pk.eyJ1IjoibWF0dGZpY2tlIiwiYSI6ImNqNnM2YmFoNzAwcTMzM214NTB1NHdwbnoifQ.Or19S7KmYPHW8YjRz82v6g&cachebuster=1572867777940&autocomplete=true&types=place%2Clocality%2Cpoi%2Caddress%2Cregion%2Ccountry");
		JsonObject response = target.request(MediaType.APPLICATION_JSON).get(JsonObject.class);
		/*String data = "{ ... }";
		Object json = new JSONTokener(data).nextValue();
		if (json instanceof JSONObject)
		  //you have an object
		else if (json instanceof JSONArray)*/
		return Response.ok().entity(response).build();
	}


}
