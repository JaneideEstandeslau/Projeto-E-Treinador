package Excecoes;

public class CampoEmBranco extends Exception{

	public CampoEmBranco(){
		super("Há algum campo em brando");
	}
}
