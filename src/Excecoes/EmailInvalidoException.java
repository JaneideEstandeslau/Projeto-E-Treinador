package Excecoes;

public class EmailInvalidoException extends Exception{

	public EmailInvalidoException(){
		super("Email não é valido");
	}
}
