package treinos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteBotaoLimparAerobico implements ActionListener {
	
	private TelaCadastroExercicioAerobico cadastroAerobico;
	
	public OuvinteBotaoLimparAerobico(TelaCadastroExercicioAerobico cadastroAerobico) {
		this.cadastroAerobico = cadastroAerobico;
	}
	
	public void actionPerformed(ActionEvent e) {
		cadastroAerobico.getCampoNomeExercicio().setText("");
	}
	
}
