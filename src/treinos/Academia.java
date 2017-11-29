package treinos;

import java.util.ArrayList;

import Excecoes.ExercicioDuplicadoException;


public class Academia {

	private ArrayList<Exercicio> exercicios = new ArrayList<>();
	private ArrayList<Treino> treinos = new ArrayList<>();
	private ArrayList<Aluno> alunos = new ArrayList<>();
	private String arquivo = "academia.xml";
	
	public void adicionarAluno(Aluno aluno){
		alunos.add(aluno);
	}

	public ArrayList<Aluno> getAlunos() {
		return alunos;
	}


	public void setAlunos(ArrayList<Aluno> alunos) {
		this.alunos = alunos;
	}


	public String getArquivo() {
		return arquivo;
	}

	public void adicionarExercicio(Exercicio exercicio) {
		exercicios.add(exercicio);
	}

	public void adicionarTreino(Treino treino) {
		treinos.add(treino);
	}

	public ArrayList<Exercicio> getExercicios() {
		return exercicios;
	}

	public void setExercicios(ArrayList<Exercicio> exercicios) {
		this.exercicios = exercicios;
	}

	public ArrayList<Treino> getTreinos() {
		return treinos;
	}

	public void setTreinos(ArrayList<Treino> treinos) {
		this.treinos = treinos;
	}

	public ArrayList<Exercicio> listarTodosExerciciosAerobicos() {

		ArrayList<Exercicio> todosExerciciosAerobicos = new ArrayList<>();

		for (Exercicio exercicio : exercicios) {
			if (exercicio instanceof Aerobico) {
				todosExerciciosAerobicos.add(exercicio);
			}
		}

		return todosExerciciosAerobicos;

	}

	public ArrayList<Exercicio> listarTodosExerciciosAnaerobicos() {

		ArrayList<Exercicio> todosExerciciosAnaerobicos = new ArrayList<>();

		for (Exercicio exercicio : exercicios) {
			if (exercicio instanceof Anaerobico) {
				todosExerciciosAnaerobicos.add(exercicio);
			}
		}

		return todosExerciciosAnaerobicos;

	}

	public void excluirExercicio(int indice) {
		exercicios.remove(indice);
	}

	public void exercicioDuplicado(Exercicio exercicio) throws ExercicioDuplicadoException {
		for (Exercicio e : exercicios) {
			if (e.getNome().equals(exercicio.getNome())) {
				throw new ExercicioDuplicadoException();
			}
		}
	}

	public Integer obterTipoDoExercicio(String nomeExercicio) {
		for (Exercicio exercicio : exercicios) {
			if (exercicio.getNome().equals(nomeExercicio) && exercicio instanceof Aerobico) {
				return 1;
			}
		}
		return 2;
	}

	public Integer obterDuracao10Repeticoes(String nome) {
		for (Exercicio exercicio : getExercicios()) {
			if (exercicio instanceof Anaerobico) {
				if (nome.equals(exercicio.getNome())) {
					return ((Anaerobico) exercicio).getTempoPara10repeticoes();
				}
			}
		}
		return -1;
	}

	public Integer exercicioUsadoNoTreino(String nomeExercicio) {
		for (int i = 0; i < treinos.size(); i++) {
			for (int j = 0; j < treinos.get(i).getExerciciosDoTreino().size(); j++) {
				if (treinos.get(i).getExerciciosDoTreino().get(j).getNome().equals(nomeExercicio)) {
					return 1;
				}
			}
		}
		return -1;
	}

	public boolean exercicioNoTreino(Integer indice, String nomeExercicio) {
		boolean igual = false;
		for (int i = 0; i < treinos.get(indice).getExerciciosDoTreino().size(); i++) {
			if (treinos.get(indice).getExerciciosDoTreino().get(i).getNome().equals(nomeExercicio)) {
				igual = true;
				break;
			}
		}
		return igual;
	}
	
	public int obterIndiceDeExercicioNoTreino(Integer indice, String nomeExercicio){
		for(int i = 0; i < treinos.get(indice).getExerciciosDoTreino().size(); i++){
			if(treinos.get(indice).getExerciciosDoTreino().get(i).getNome().equals(nomeExercicio)){
				return i;
			}
		}
		return -1;
	}
	
	public ArrayList<Treino> procurarExercicioNoTreino(String nomeExercicio){	
		ArrayList<Treino> nomesTreinos = new ArrayList<>();
		for(int i = 0; i < treinos.size(); i++){
			for(int j = 0; j < treinos.get(i).getExerciciosDoTreino().size(); j++){
				if(treinos.get(i).getExerciciosDoTreino().get(j).getNome().equals(nomeExercicio)){
					nomesTreinos.add(treinos.get(i));
				}
			}
		}
		return nomesTreinos;
	}
	
	public ArrayList<Exercicio> obterExerciciosDoTreino(int indice){
		ArrayList<Exercicio> exerciciosDoTreino = new ArrayList<>();
		for(int i = 0; i < treinos.get(indice).getExerciciosDoTreino().size(); i++){
			exerciciosDoTreino.add(treinos.get(indice).getExerciciosDoTreino().get(i));
		}
		return exerciciosDoTreino;
	}
	
	public Boolean treinoUsadoPorAluno(String nomeTreino){
		boolean igual = false;
		for(int i = 0; i < alunos.size(); i++){
			for(int j = 0; j < 6; j++){
				if(alunos.get(i).getTreinosDoAluno()[j] != null){
					if(alunos.get(i).getTreinosDoAluno()[j].getNomeDoTreino().equals(nomeTreino)){
						igual = true;
					}
				}
			}
		}
		return igual;
	}
	
	public ArrayList<Aluno> alunosUsandoTreino(String nomeTreino){
		ArrayList<Aluno> alunosUsando = new ArrayList<>();

		for(int i = 0; i < alunos.size(); i++){
			for(int j = 0; j < alunos.get(i).getTreinosDoAluno().length; j++){
				if(alunos.get(i).getTreinosDoAluno()[j] != null){
					if(alunos.get(i).getTreinosDoAluno()[j].getNomeDoTreino().equals(nomeTreino)){	
						alunosUsando.add(alunos.get(i));
						break;
					}
				}
			}
		}
		return alunosUsando;
	}
}