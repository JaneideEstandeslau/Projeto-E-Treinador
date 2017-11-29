package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Excecoes.AlunoDuplicadoException;
import Excecoes.CampoEmBranco;
import Excecoes.EmailInvalidoException;
import Excecoes.ValidacaoSistema;
import treinos.Academia;
import treinos.Aluno;
import treinos.Persistencia;
import treinos.Treino;

public class OuvinteCrecenciaisAluno implements ActionListener{

	private TelaEdicaoDoAluno janela;
	private Persistencia persistencia = new Persistencia();
	private Academia academia;
	private Treino[] treinosDoAluno;
	
	public OuvinteCrecenciaisAluno(TelaEdicaoDoAluno janela){
		this.janela = janela;
	}
	public void remover(int indice){
		academia = persistencia.recuperar("academia.xml");
		ArrayList<Aluno> alunos = academia.getAlunos();
		treinosDoAluno = alunos.get(indice).getTreinosDoAluno();
		alunos.remove(indice);
		persistencia.salvar(academia, "academia.xml");
	}
	public void actionPerformed(ActionEvent arg0) {
		
		int indice = janela.getSelect();
		
		this.remover(indice);
		
		ValidacaoSistema validacao = new ValidacaoSistema();
		
		OuvinteDoBotaoCadastrar ouvinte = new OuvinteDoBotaoCadastrar();
		
		academia = persistencia.recuperar("academia.xml");
		
		ArrayList<Aluno> alunos = academia.getAlunos();
		
		
		try {
		
			//todas as v�riaveis
			String nome  = janela.getTFnome().getText();
			String sexo = (String) janela.getCombo().getSelectedItem();
			Integer idade = Integer.parseInt(janela.getTFidade().getText());
			String cpf = janela.getTFcpf().getText();
			String data = janela.getTFdata().getText();
			String rg = janela.getTFidentidade().getText();
			String email = janela.getTFemail().getText();
			String entereco = janela.getTFendereco().getText();
			String telefone = janela.getTFtelefone().getText();
			
			if(sexo.isEmpty()){
				sexo = ouvinte.getSexo();
			}
			
			Aluno aluno = new Aluno(nome, sexo, idade, rg, cpf, data, email, entereco, telefone);
			
			aluno.setTreinosDoAluno(treinosDoAluno);
			
			validacao.alunoDuplicado(aluno);
			validacao.campoBranco2(aluno, janela);
			validacao.emailInvalido(email);
			
			alunos.add(indice, aluno);
			persistencia.salvar(academia, "academia.xml");
			
			//se tudo der certo ele imprima a mensagem de aluno cadastrado e vai para janela de cadastro de treinos
			JOptionPane.showMessageDialog(janela, "Aluno Editado");
			janela.dispose();
				
		
		} catch (CampoEmBranco e) {
			JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(janela, "Voce digitou letra no lugar de numero\n" + "ou diitou letra no lugar de numero", "Erro", JOptionPane.ERROR_MESSAGE);
			
		}catch (AlunoDuplicadoException ex) {
			JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		} catch (EmailInvalidoException ex) {
		JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
}
}
