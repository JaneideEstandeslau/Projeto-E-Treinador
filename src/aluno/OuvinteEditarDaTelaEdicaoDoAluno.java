package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class OuvinteEditarDaTelaEdicaoDoAluno implements ActionListener{

	private EditarAluno janela;
	
	public OuvinteEditarDaTelaEdicaoDoAluno(EditarAluno janela){
		this.janela = janela;
	}
	public void actionPerformed(ActionEvent e) {
		
		int select = janela.getTabela().getSelectedRow();
		
		if(select == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		else{
			new TelaEdicaoDoAluno(select);
			janela.dispose();
		}	
	}
}
