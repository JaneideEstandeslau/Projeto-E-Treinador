package Excecoes;

public class TreinoNaoExisteException extends Exception{

	public TreinoNaoExisteException(){
		super("O treino não foi cadastrado nos trainos da semana");
	}
}
