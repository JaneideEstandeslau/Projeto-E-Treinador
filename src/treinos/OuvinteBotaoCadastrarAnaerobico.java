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
import Excecoes.SelecioneUmValorNoSpinnerException;

public class OuvinteBotaoCadastrarAnaerobico implements ActionListener {
	private Verificacao verificar = new Verificacao();
	private TelaCadastroExercicioAnaerobico telaCadastroAnaerobico;
	private Academia academia = new Academia();
	private Persistencia persistencia = new Persistencia();
//	private ImageIcon iconeErro = new ImageIcon(getClass().getResource("icones/erro.png"));
//	private ImageIcon iconeInformacao= new ImageIcon(getClass().getResource("icones/info.png"));
//	private ImageIcon iconeInterrogacao = new ImageIcon(getClass().getResource("icones/interrogacao.png"));
	private Font fonte = new Font("Calibri", Font.PLAIN, 17);
	
	public OuvinteBotaoCadastrarAnaerobico(TelaCadastroExercicioAnaerobico telaCadastroAnaerobico) {
		this.telaCadastroAnaerobico = telaCadastroAnaerobico;
	}

	public void actionPerformed(ActionEvent e) {
		academia = persistencia.recuperar(academia.getArquivo());
		try{
			verificar.caracterInvalido(telaCadastroAnaerobico.getCampoNomeExercicio().getText());
			verificar.stringVazia(telaCadastroAnaerobico.getCampoNomeExercicio().getText());
			verificar.stringVazia((String)telaCadastroAnaerobico.getComboBoxGruposMusculares().getSelectedItem());
			verificar.igualAZero((Integer)telaCadastroAnaerobico.getSpinner().getValue());
			Exercicio exercicio = new Anaerobico(telaCadastroAnaerobico.getCampoNomeExercicio().getText(), "Anaeróbico", (String)telaCadastroAnaerobico.getComboBoxGruposMusculares().getSelectedItem(), (Integer)telaCadastroAnaerobico.getSpinner().getValue());
			academia.exercicioDuplicado(exercicio);
			academia.adicionarExercicio(exercicio);
			if(persistencia.salvar(academia, academia.getArquivo())){
				UIManager.put("OptionPane.messageFont", fonte);
				UIManager.put("OptionPane.buttonFont", fonte);
				JOptionPane.showMessageDialog(telaCadastroAnaerobico, "Exercício cadastrado com sucesso.", "Informação", JOptionPane.PLAIN_MESSAGE);
				Integer opcao = JOptionPane.showConfirmDialog(telaCadastroAnaerobico, "Você ainda deseja cadastrar mais algum exercício anaeróbico?", "Pergunta", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
				if(opcao == 0){
					telaCadastroAnaerobico.getCampoNomeExercicio().setText("");
					telaCadastroAnaerobico.getComboBoxGruposMusculares().setSelectedIndex(0);
					telaCadastroAnaerobico.getSpinner().setValue(0);
				}else if(opcao == 1 || opcao == -1){
					telaCadastroAnaerobico.dispose();
				}
			}
		}catch(CampoVazioException | CaracterInvalidoException | ExercicioDuplicadoException | SelecioneUmValorNoSpinnerException x){
			UIManager.put("OptionPane.messageFont", fonte);
			UIManager.put("OptionPane.buttonFont", fonte);
			JOptionPane.showMessageDialog(telaCadastroAnaerobico, x.getMessage(), "Erro", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
