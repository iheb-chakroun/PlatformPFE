package utilities;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Cookie;

import entities.users.Employe;
import entities.users.Student;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author Saidi Khaled
 *
 */
public class TokenUtility {
	
	public static String getTokenEmploye(Employe user, Key key) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("cin", user.getEmail().toString());

        if (user instanceof Employe) {
            claims.put("role", "employe");
        }

        claims.put("firstname", user.getFirstName());
        claims.put("lastname", user.getFirstName());
        claims.put("exp", new Date(new Date().getTime() + 3600));

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }
	public static String getTokenStudent(Student user, Key key) {
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("cin", user.getEmail().toString());
        claims.put("role", "student");
        claims.put("firstname", user.getFirstName());
        claims.put("lastname", user.getFirstName());
        claims.put("exp", new Date(new Date().getTime() + 3600));

        return Jwts.builder()
                .setSubject(user.getEmail())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
    }

    public static Jws<Claims> getClaims(Cookie cookie, Key key) {
        if (cookie == null) {
            throw new NotAuthorizedException("Not token found");
        }

        try {
            return Jwts.parser().setSigningKey(key).parseClaimsJws(cookie.getValue());
        } catch (JwtException je) {
            throw new NotAuthorizedException("Token error");
        }
    }

}
