package Excecoes;

public class TreinoNaoExisteException extends Exception{

	public TreinoNaoExisteException(){
		super("O treino n�o foi cadastrado nos trainos da semana");
	}
}
