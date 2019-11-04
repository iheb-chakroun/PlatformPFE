package services;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import entities.users.Employe;
import interfaces.EmployeRemote;
import interfaces.TokenRemote;
import utilities.TokenUtility;

@Path("employe")
public class EmployeResource {
	
	@EJB
	EmployeRemote employeBusiness;
	@EJB
	TokenRemote tokenService;
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	@Produces("application/json")
	public Response addEmploye(Employe employe) {
		if(employe == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		employeBusiness.addEmploye(employe);
		return Response.status(Response.Status.CREATED).entity(employe).build();
	}
	
	@PUT
	@Consumes("application/json")
	@Produces("application/json")
	public Response updateEmploye(Employe employe) {
		if(employe == null) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No Content.").build();
		}
		employeBusiness.updateEmploye(employe);
		return Response.status(Response.Status.ACCEPTED).entity(employe).build();
	}
	
	@GET
	@Path("{id}")
	@Produces("application/json")
	public Response findEmploye(@PathParam("id")int id) {
		return Response.ok(employeBusiness.findEmployeById(id), MediaType.APPLICATION_JSON).build();
	}
	
	@DELETE
	@Path("delete/{id}")
	@Produces("application/json")
	public Response deleteEmploye(@PathParam("id") int id) {
		String deleted = "Employe deleted :"+id;
		employeBusiness.removeEmploye(id);
	    return Response.ok(MediaType.APPLICATION_JSON).entity(deleted).build();
	}
	
	@GET
	@Consumes("application/json")
	@Produces("application/json")
	public Response findAllEmployes() {
		return Response.ok(employeBusiness.findAllEmployes(), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response logIn(Employe emp) {
		if(emp == null) {
			String message = "{\"message\": \"No content was entred!\"}";
			return Response.status(Response.Status.NO_CONTENT).entity(message).build();
		}
		Employe user = employeBusiness.login(emp.getUsername(), emp.getPassword());
        System.out.println("User found, adding coockie");
        return Optional.ofNullable(user)
                .map(u ->
                        Response.ok(u).cookie(
                                new NewCookie("access_token",
                                        TokenUtility.getToken(user, tokenService.getKey()),
                                        "/",
                                        "127.0.0.1",
                                        "",
                                        36000,
                                        false,
                                        false))
                                .build()
                )
                .orElseThrow(NotFoundException::new);
	}
	
	@Path("logout")
    @GET
    @Produces("application/json")
    public Response logout() {
        return Response.ok().cookie(new NewCookie("access_token",
                "none",
                "/",
                "127.0.0.1",
                "",
                3600,
                true,
                true)).build();
    }

}
