package treinos;

public class Anaerobico extends Exercicio {
	
	private String grupoMuscular;
	private Integer qtdDeSeries;
	private Integer qtdDeRepeticoes;
	private Integer tempoPara10repeticoes;
	
	public Integer getQtdDeSeries() {
		return qtdDeSeries;
	}

	public void setQtdDeSeries(Integer qtdDeSeries) {
		this.qtdDeSeries = qtdDeSeries;
	}

	public Integer getQtdDeRepeticoes() {
		return qtdDeRepeticoes;
	}

	public Float duracao(){
		Float tempoMedioParaUmaSerie = (float) (tempoPara10repeticoes*qtdDeRepeticoes);
		Float tempoMedioParaUmaSerie2 = tempoMedioParaUmaSerie/10;
		return tempoMedioParaUmaSerie2*qtdDeSeries;
	}
	public void setQtdDeRepeticoes(Integer qtdDeRepeticoes) {
		this.qtdDeRepeticoes = qtdDeRepeticoes;
	}

	public Anaerobico(String nome, String tipo, String grupo, Integer tempo10repeticoes){
		this.setNome(nome);
		this.setTipo(tipo);
		this.grupoMuscular = grupo;
		this.tempoPara10repeticoes = tempo10repeticoes;
	}
	
	public Anaerobico(String nome, Integer qtdSeries, Integer qtdRepeticoes, Integer tempo10repeticoes) {
		setNome(nome);
		setQtdDeSeries(qtdSeries);
		setQtdDeRepeticoes(qtdRepeticoes);
		setTempoPara10repeticoes(tempo10repeticoes);
	}
	
	public String getGrupoMuscular() {
		return grupoMuscular;
	}

	public void setGrupoMuscular(String grupoMuscular) {
		this.grupoMuscular = grupoMuscular;
	}	
	
	public Integer getTempoPara10repeticoes() {
		return tempoPara10repeticoes;
	}

	public void setTempoPara10repeticoes(Integer tempoPara10repeticoes) {
		this.tempoPara10repeticoes = tempoPara10repeticoes;
	}
}