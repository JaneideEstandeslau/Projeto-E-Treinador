package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OuvinteDoBTVoltarCadastroAluno implements ActionListener {

	CadastroDEAluno c = null;

	public OuvinteDoBTVoltarCadastroAluno(CadastroDEAluno c) {
		this.c = c;
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		c.dispose();

	}

}
