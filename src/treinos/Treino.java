package treinos;

import java.util.ArrayList;

public class Treino {

	private ArrayList<Exercicio> exerciciosDoTreino = new ArrayList<>();

	private String nomeDoTreino;
	
	public String getNomeDoTreino() {
		return nomeDoTreino;
	}

	public void setNomeDoTreino(String nomeDoTreino) {
		this.nomeDoTreino = nomeDoTreino;
	}

	public Treino(String nomeDoTreino) {
		this.nomeDoTreino = nomeDoTreino;
	}
	
	public Treino(){}

	public Float duracao(){
		Float count = 0f;
		for(Exercicio exercicio: exerciciosDoTreino){
			
			if(exercicio instanceof Aerobico){
				count += ((Aerobico) exercicio).getDuracao();
			}
			else{
				count += ((Anaerobico) exercicio).duracao();
			}
		}
		return count;
	}
	
	public void adicinarExercicio(Exercicio e){
		exerciciosDoTreino.add(e);
	}
	
	public ArrayList<Exercicio> getExerciciosDoTreino() {
		return exerciciosDoTreino;
	}

	public void setExerciciosDoTreino(ArrayList<Exercicio> exerciciosDoTreino) {
		this.exerciciosDoTreino = exerciciosDoTreino;
	}
	
	public String toString() {
		return getNomeDoTreino();
	}
	
}
