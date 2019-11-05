package utilities;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import interfaces.TokenRemote;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;


@Secured
@Provider
public class AuthenticationFilter implements ContainerRequestFilter{
	
	@EJB
    private TokenRemote tokenService;
    @Context
    private ResourceInfo resourceInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {

        System.out.println("here " + requestContext.getCookies());

        if (requestContext.getCookies().containsKey("access_token")) {
            String token = requestContext.getCookies().get("access_token").getValue();

            try {
                Jws<Claims> jws = Jwts.parser().setSigningKey(tokenService.getKey()).parseClaimsJws(token);
                if (!requiredRoles(jws)) {
                    throw new NotAuthorizedException("You do not have enough permission");
                }

            } catch (JwtException je) {
                throw new NotAuthorizedException("Token expired!");
            }
        } else {
            throw new NotAuthorizedException("Token not found");
        }
		
	}
	
	 private boolean requiredRoles(Jws<Claims> jws) {
	        Secured secured = resourceInfo.getResourceMethod().getAnnotation(Secured.class);
	        if (secured == null) {
	            secured = resourceInfo.getResourceClass().getAnnotation(Secured.class);
	        }

	        for (Roles role : secured.value()) {

	            if (role.toString().equals(jws.getBody().get("role"))) {
	                return true;
	            }
	        }

	        return false;
	    }
	

}
