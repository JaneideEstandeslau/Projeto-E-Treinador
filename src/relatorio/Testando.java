package relatorio;

import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class Testando {

	public static boolean validaEmail(String email) {
		Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
		Matcher m = p.matcher(email);
		if (m.matches()) {
			return false;
		} else {
			return true;
		}
	}

	public static HtmlEmail conectaEmail() throws EmailException, NoSuchProviderException, MessagingException {
		String hostname = "smtp.gmail.com";
		String username = "seu@gmail.com";
		String password = "****";
		String emailOrigem = "seu@gmail.com";
		HtmlEmail email = new HtmlEmail();
		email.setHostName(hostname);
		email.setSmtpPort(587);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setTLS(true);
		email.setFrom(emailOrigem, "Destinatário");
		email.setDebug(true);
		email.setCharset(HtmlEmail.ISO_8859_1);
		Properties props = new Properties();
		props.setProperty(hostname, "smtp");
		props.setProperty("mail.smtp.host", "smtp.gmail.com");
		props.setProperty("mail.smtp.auth", "true");
		props.setProperty("mail.smtp.port", "" + 587);
		props.setProperty("mail.smtp.starttls.enable", "true");
		Session mailSession = Session.getInstance(props, new DefaultAuthenticator(username, password));
		email.setMailSession(mailSession);
		return email;
	}

	

}
