package business;

import java.security.Key;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import interfaces.TokenRemote;
import io.jsonwebtoken.impl.crypto.MacProvider;

@Singleton
@Startup
public class TokenBusiness implements TokenRemote {

	private Key key;
	
	@PostConstruct
	public void initKey() {
	    key = MacProvider.generateKey();
	    System.out.println("Key generated ... " + key.getEncoded().length);
	}
	@Override
	public Key getKey() {
		// TODO Auto-generated method stub
		return key;
	}
	   

	   

}
