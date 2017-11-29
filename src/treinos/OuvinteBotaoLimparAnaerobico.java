package treinos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteBotaoLimparAnaerobico implements ActionListener{
	
	private TelaCadastroExercicioAnaerobico cadastroAnaerobico;
	
	public OuvinteBotaoLimparAnaerobico(TelaCadastroExercicioAnaerobico cadastroAnaerobico) {
		this.cadastroAnaerobico = cadastroAnaerobico;
	}
	
	public void actionPerformed(ActionEvent d) {
		cadastroAnaerobico.getCampoNomeExercicio().setText("");
		cadastroAnaerobico.getComboBoxGruposMusculares().setSelectedIndex(0);
		cadastroAnaerobico.getSpinner().setValue(0);
	}

}
