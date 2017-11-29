package treinos;

public abstract class Exercicio {

	private String nome;
	private String tipo;
	
	private Float duracao;
	
	public void setDuracao(Float duracao){
		this.duracao = duracao;
	}

	public Float getDuracao() {
		return duracao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String toString() {
		return getNome();
	}
}
