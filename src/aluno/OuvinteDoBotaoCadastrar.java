package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Excecoes.AlunoDuplicadoException;
import Excecoes.CampoEmBranco;
import Excecoes.EmailInvalidoException;
import Excecoes.ValidacaoSistema;
import treinos.Aluno;

public class OuvinteDoBotaoCadastrar implements ActionListener{


	private CadastroDEAluno janela;
	private String sexo;
	
	public OuvinteDoBotaoCadastrar(){}
	
	public OuvinteDoBotaoCadastrar(CadastroDEAluno janela){
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent arg0) {
		ValidacaoSistema validacao = new ValidacaoSistema();

		try{
			
			//todas as v�riaveis
			String nome  = janela.getTFnome().getText();
			sexo = (String) janela.getCombo().getSelectedItem();
			Integer idade = Integer.parseInt(janela.getTFidade().getText());
			String cpf = janela.getTFcpf().getText();
			String data = janela.getTFdata().getText();
			String rg = janela.getTFidentidade().getText();
			String email = janela.getTFemail().getText();
			String entereco = janela.getTFendereco().getText();
			String telefone = janela.getTFtelefone().getText();

			Aluno aluno = new Aluno(nome, sexo, idade, rg, cpf, data, email, entereco, telefone);
			
			//faz as verifica��es necessarias
			validacao.emailInvalido(email);
			validacao.campoBranco(aluno, janela);
			validacao.alunoDuplicado(aluno);
			
			
			//se tudo der certo ele imprima a mensagem de aluno cadastrado e vai para janela de cadastro de treinos
			JOptionPane.showMessageDialog(janela, "Aluno Cadastrado");
			
			Integer opcao = JOptionPane.showConfirmDialog(janela, "Você deseja cadastrar os treinos do aluno?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(opcao == 0){
				new CadastroDeTreinos(aluno);
				janela.dispose();
			}else if(opcao == 1 || opcao == -1){
				
				janela.dispose();
			}
			
		} catch (CampoEmBranco e) {
			JOptionPane.showMessageDialog(janela, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		
		}catch(NumberFormatException ex){
			JOptionPane.showMessageDialog(janela, "Voce deixou algum campo em branco ou\n" + "digitou letra no lugar de numero", "Erro", JOptionPane.ERROR_MESSAGE);
			
		}catch (AlunoDuplicadoException ex) {
			JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
			
		} catch (EmailInvalidoException ex) {
			JOptionPane.showMessageDialog(janela, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		} 
		
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
