package treinos;

public class Aerobico extends Exercicio{
	private Float duracao;
	
	public Float getDuracao() {
		return duracao;
	}

	public void setDuracao(Float duracao) {
		this.duracao = duracao;
	}

	public Aerobico(String nome, String tipo){
		this.setNome(nome);
		this.setTipo(tipo);
	}
	
	public Aerobico(String nome, Float duracao){
		setNome(nome);
		setDuracao(duracao);
	}
}
