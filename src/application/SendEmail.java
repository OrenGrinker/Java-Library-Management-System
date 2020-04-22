//package email;
package application;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Class for working with email.
 * @author OBL_Team13
 */
public class SendEmail 
{
	
	/**
	 * Constructor for sendEmail.
	 * @param emailRecipient	the email address of the receiver of the email as string.
	 * @param emailSubject		the subject of the email as string.
	 * @param emailMessage		the message of the email as string.
	 */
	public static void sendEmail(String emailRecipient, String emailSubject, String emailMessage) 
	{
		final String username = "Team13OBL@gmail.com";
		final String password = "T3am13Obl!";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() 
		{
			protected PasswordAuthentication getPasswordAuthentication() 
			{
				return new PasswordAuthentication(username, password);
			}
		  });

		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRecipient));
			message.setSubject(emailSubject);
			message.setText(emailMessage);

			Transport.send(message);

			System.out.println("Done");

		} 
		catch (MessagingException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
