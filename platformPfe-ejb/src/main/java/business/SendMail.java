package business;

import com.sun.mail.smtp.SMTPTransport;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import javax.mail.Transport;
public class SendMail {

    public void  envoi (String too,String subject,String messageTextv){
    	  
  
    // Put sender’s address
    String from = "platform.esprit@gmail.com";
    final String username = "7f90b30ecb1c39";//username generated by Mailtrap
    final String password = "90983d5b1b2da6";//password generated by Mailtrap

    // Paste host address from the SMTP settings tab in your Mailtrap Inbox
    String host = "smtp.mailtrap.io";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");//it’s optional in Mailtrap  
    props.put("mail.smtp.host", host);
    props.put("mail.smtp.port", "2525");// use one of the options in the SMTP settings tab in your Mailtrap Inbox

    // Get the Session object.
    Session session = Session.getInstance(props,
       new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
             return new PasswordAuthentication(username, password);
  }
       });

    try {
  // Create a default MimeMessage object.
  Message message = new MimeMessage(session);

  // Set From: header field 
  message.setFrom(new InternetAddress(from));

  // Set To: header field
  message.setRecipients(Message.RecipientType.TO,
             InternetAddress.parse(too));

  // Set Subject: header field
  message.setSubject(subject);

  // Put the content of your message
  message.setText(messageTextv);

  // Send message
  Transport.send(message);

  System.out.println("Sent message successfully....");

    } catch (MessagingException e) {
       throw new RuntimeException(e);
    }
 }
	}
