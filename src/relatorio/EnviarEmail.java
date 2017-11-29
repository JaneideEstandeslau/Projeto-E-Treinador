package relatorio;

import java.util.Properties;

import javax.mail.Authenticator;

import javax.mail.Message;

import javax.mail.MessagingException;

import javax.mail.PasswordAuthentication;

import javax.mail.Session;

import javax.mail.Transport;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

//Classe necessaria para passar usuario e senha caso precisa de autenticacao

public class EnviarEmail {

	public void sendEmail() throws EmailException {

		SimpleEmail email = new SimpleEmail();
		// Utilize o hostname do seu provedor de email
		System.out.println("alterando hostname...");
		email.setHostName("smtp.gmail.com");
		email.setSmtpPort(465);
		email.setSSL(true);
		
		// Adicione os destinatários
		email.addTo("janeide.estan@gmail.com", "Cleyton");
		// Configure o seu email do qual enviará
		email.setFrom("janeide.estan@gmail.com", "Janeide");
		
		// Adicione um assunto
		email.setSubject("Testando");
		// Adicione a mensagem do email
		email.setMsg("Este é um teste simples de comunicações electrónicas");
	
		System.out.println("autenticando...");
		
		email.setAuthentication( "janeide.estan@gmail.com", "30neide456");
		System.out.println("enviando...");
		email.send();
		System.out.println("Email enviado!");
	}

	public static void main(String[] args) {

		try {
			new EnviarEmail().sendEmail();
		} catch (EmailException e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
	}
}
