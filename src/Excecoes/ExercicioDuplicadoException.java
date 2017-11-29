package Excecoes;

public class ExercicioDuplicadoException extends Exception {

	public ExercicioDuplicadoException() {
		super("Esse exercício já está cadastrado.");
	}
}
