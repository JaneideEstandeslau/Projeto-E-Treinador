package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OuvinteBotaoEditarInformacoesPessoaisDoAluno implements ActionListener{

	private EscolhaDoTreinador janela;
	
	public OuvinteBotaoEditarInformacoesPessoaisDoAluno(EscolhaDoTreinador janela){
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		int select = janela.getJanela().getTabela().getSelectedRow();
		if(select == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione um aluno valido valida");
		}
		else{
			new TelaEdicaoDoAluno(select);	
			janela.getJanela().dispose();
			janela.dispose();
		}
		
	}

}
