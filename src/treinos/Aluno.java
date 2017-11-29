package treinos;

public class Aluno {
	
	private String nome;
	private String sexo;
	private int idade;
	private String identidade;
	private String CPF;
	private String dataDeCadastro;
	private String email;
	private String endereco;
	private String telefone;
	private Treino[] treinosDoAluno = new Treino[7];
	
	//m�todo construtor para o aluno
	public Aluno(String nome,String sexo, int idade,String identidade, String CPF, String dataDeCadastro, String email,String endereco, String telefone){
		
		this.nome = nome;
		this.sexo = sexo;
		this.idade = idade;
		this.identidade = identidade;
		this.CPF = CPF;
		this.dataDeCadastro = dataDeCadastro;
		this.email = email;
		this.endereco = endereco;
		this.telefone = telefone;
	}
	
	public boolean equals(Aluno aluno){
		if(aluno.getCPF().equalsIgnoreCase(this.getCPF())){
			return true;
		} else {
			return false;
		}
	}
	public Treino[] getTreinosDoAluno() {
		return treinosDoAluno;
	}

	public void setTreinosDoAluno(Treino[] treinosDoAluno) {
		this.treinosDoAluno = treinosDoAluno;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	public String getDataDeCadastro() {
		return dataDeCadastro;
	}
	public void setDataDeCadastro(String data) {
		this.dataDeCadastro = data;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getCPF() {
		return CPF;
	}
	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getIdentidade() {
		return identidade;
	}

	public void setIdentidade(String identidade) {
		this.identidade = identidade;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
}
