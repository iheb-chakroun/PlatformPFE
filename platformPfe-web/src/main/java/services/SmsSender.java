package services;





import com.textmagic.sdk.RestClient;
import com.textmagic.sdk.RestException;
import com.textmagic.sdk.resource.instance.*;
import java.util.Arrays;
public class SmsSender {
	
	 public static void main(String[] args) throws RestException {
		    RestClient client = new RestClient("<username>", "<apiv2_key>");
		 
		    TMNewMessage m = client.getResource(TMNewMessage.class);
		    m.setText("Hello from TextMagic Java");
		    m.setPhones(Arrays.asList(new String[] {"99900000"}));
		    try {
		      m.send();
		    } catch (final RestException e) {
		      System.out.println(e.getErrors());
		      throw new RuntimeException(e);
		    }
		    System.out.println(m.getId());
		  }
}
