package treinos;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import Excecoes.CampoVazioException;
import Excecoes.CaracterInvalidoException;
import Excecoes.ExercicioDuplicadoException;

public class OuvinteBotaoCadastrarAerobico implements ActionListener {
	Font fonte = new Font("Calibri", Font.PLAIN, 17);
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
	private TelaCadastroExercicioAerobico cadastroAerobico;
	private Verificacao verificar = new Verificacao();
//	private ImageIcon iconeErro = new ImageIcon(getClass().getResource("icones/erro.png"));
//	private ImageIcon iconeInformacao = new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon iconeInterrogacao = new ImageIcon(getClass().getResource("icones/interrogacao.png"));
	public OuvinteBotaoCadastrarAerobico(TelaCadastroExercicioAerobico cadastroAerobico) {
		this.cadastroAerobico = cadastroAerobico;
	}
	public void actionPerformed(ActionEvent e) {
		academia = persistencia.recuperar(academia.getArquivo());
		try{
			verificar.caracterInvalido(cadastroAerobico.getCampoNomeExercicio().getText());
			verificar.stringVazia(cadastroAerobico.getCampoNomeExercicio().getText());
			Exercicio exercicio = new Aerobico(cadastroAerobico.getCampoNomeExercicio().getText(), "Aerobico");
			academia.exercicioDuplicado(exercicio);
			academia.adicionarExercicio(exercicio);
			if(persistencia.salvar(academia, academia.getArquivo())){
				UIManager.put("OptionPane.messageFont", fonte);
				UIManager.put("OptionPane.buttonFont", fonte);
				JOptionPane.showMessageDialog(cadastroAerobico, "Exercício cadastrado com sucesso.", "Informação", JOptionPane.PLAIN_MESSAGE);
				Integer opcao = JOptionPane.showConfirmDialog(cadastroAerobico, "Você ainda deseja cadastrar mais algum exercício aeróbico?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(opcao == 0){
					cadastroAerobico.getCampoNomeExercicio().setText("");
				}else if(opcao == 1 || opcao == -1){
					cadastroAerobico.dispose();
				}
			}
		}catch(CampoVazioException | CaracterInvalidoException | ExercicioDuplicadoException a){
			UIManager.put("OptionPane.messageFont", fonte);
			UIManager.put("OptionPane.buttonFont", fonte);
			JOptionPane.showMessageDialog(cadastroAerobico, a.getMessage(), "Erro", JOptionPane.PLAIN_MESSAGE);
		}
	}
	
}
