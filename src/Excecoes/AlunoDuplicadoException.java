package Excecoes;

public class AlunoDuplicadoException extends Exception{

	public AlunoDuplicadoException(){
		super("O aluno ja existe");
	}
}
