package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OuvinteEditarTreino implements ActionListener{
	
	private TelaListarAlunoParaEdicaoDoTreino janela;
	
	public OuvinteEditarTreino(TelaListarAlunoParaEdicaoDoTreino janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(janela.getTabela().getSelectedRow() == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		else{
			new TelaEdicaoTreinoDoAluno(janela.getTabela().getSelectedRow());
			janela.dispose();
		}
	}

}
