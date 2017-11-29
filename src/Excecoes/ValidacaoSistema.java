package Excecoes;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import aluno.CadastroDEAluno;
import aluno.TelaEdicaoDoAluno;
import aluno.TelaVisualizarTreinos;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.TelaInicial;


public class ValidacaoSistema {
	Persistencia persistencia  = new Persistencia();
	Academia academia = persistencia.recuperar("academia.xml");
	
	//metodo que verifica se j� existe um aluno com o mesmo CPF
	public void alunoDuplicado(Aluno aluno) throws AlunoDuplicadoException{
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		if (alunos.isEmpty())
			return;
		for(Aluno al: alunos){
			if(al.equals(aluno)){
				throw new AlunoDuplicadoException();
			}
		}
	}
	
	public void emailInvalido(String email) throws EmailInvalidoException{
		 String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";

		//String de entrada para a valida��o
		//String email = "umcastec@.com";
		//Definir a seq��ncia de padr�o de e-mail
		Pattern p = Pattern.compile(EMAIL_REGEX);
		//Pattern p = Pattern.compile(EMAIL_REGEX2);

		// Match Joga seq��ncia de dados com o padr�o
		Matcher m = p.matcher(email);

		// Verifica se o Match jogado foi encontrado
		boolean matchEncon = m.matches();

		if (matchEncon == false){
			throw new EmailInvalidoException();
		}
	}
	public void campoBranco2(Aluno aluno, TelaEdicaoDoAluno janela) throws CampoEmBranco{
		if(aluno.getNome().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getSexo().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getIdade() == 0){
			throw new CampoEmBranco();
		}
		else if(janela.getTFidentidade().getText().equals(" .   .   ")){
			throw new CampoEmBranco();
		}
		else if(janela.getTFcpf().getText().equals("   .   .   -  ")){
			throw new CampoEmBranco();
		}
		else if(janela.getTFtelefone().getText().equals("(  )     -    ")){
			throw new CampoEmBranco();
		}
		else if(aluno.getEmail().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getEndereco().isEmpty()){
			throw new CampoEmBranco();
		}
	}
	
	
	public void campoBranco(Aluno aluno, CadastroDEAluno janela) throws CampoEmBranco{
		
		if(aluno.getNome().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getSexo().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getIdade() == 0){
			throw new CampoEmBranco();
		}
		else if(janela.getTFidentidade().getText().equals(" .   .   ")){
			throw new CampoEmBranco();
		}
		else if(janela.getTFcpf().getText().equals("   .   .   -  ")){
			throw new CampoEmBranco();
		}
		else if(janela.getTFtelefone().getText().equals("(  )     -    ")){
			throw new CampoEmBranco();
		}
		else if(aluno.getEmail().isEmpty()){
			throw new CampoEmBranco();
		}
		else if(aluno.getEndereco().isEmpty()){
			throw new CampoEmBranco();
		}
	}
	
	
	public void StringVazia(String nome) throws ValidarCPFException {
		
		if(nome.equals("   .   .   -  ")) {
			throw new ValidarCPFException();
		}
		
	}
	
	public void stringNula(String nome) throws CampoVazioException {
		if(nome.isEmpty()) {
			throw new CampoVazioException();
		}
	}
}
