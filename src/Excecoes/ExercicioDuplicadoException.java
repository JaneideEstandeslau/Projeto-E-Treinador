package Excecoes;

public class ExercicioDuplicadoException extends Exception {

	public ExercicioDuplicadoException() {
		super("Esse exerc�cio j� est� cadastrado.");
	}
}
