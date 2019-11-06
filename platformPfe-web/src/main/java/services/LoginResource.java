package services;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import business.LoginBusiness;
import entities.users.Employe;
import entities.users.Student;
import interfaces.TokenRemote;
import utilities.TokenUtility;

@Path("auth")
public class LoginResource {

	@EJB
	LoginBusiness loginBusiness;
	@EJB
	TokenRemote tokenService;

	@GET
	@Path("login")
	@Consumes("application/json")
	@Produces("application/json")
	public Response logIn(@QueryParam("email") String email, @QueryParam("password") String password) {
		
		Object user = loginBusiness.login(email, password);
		if(user instanceof Employe) {
		Employe emp = (Employe)user;
        System.out.println("User found, adding coockie");
        return Optional.ofNullable(user)
                .map(u ->
                        Response.ok(u).cookie(
                                new NewCookie("access_token",
                                        TokenUtility.getTokenEmploye(emp, tokenService.getKey()),
                                        "/",
                                        "127.0.0.1",
                                        "",
                                        36000,
                                        false,
                                        false))
                                .build()
                )
                .orElseThrow(NotFoundException::new);
        }else if(user instanceof Student) {
        	Student st = (Student)user;
            System.out.println("User found, adding coockie");
            return Optional.ofNullable(user)
                    .map(u ->
                            Response.ok(u).cookie(
                                    new NewCookie("access_token",
                                            TokenUtility.getTokenStudent(st, tokenService.getKey()),
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
		return null;
		
	}

	@Path("logout")
	@GET
	@Produces("application/json")
	public Response logout() {
		return Response.ok().cookie(new NewCookie("access_token", "none", "/", "127.0.0.1", "", 3600, true, true))
				.build();
	}
}
