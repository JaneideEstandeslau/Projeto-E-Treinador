package aluno;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class OuvinteDoBotaoEditarDaTelaDeListagem implements ActionListener{
	
	private JanelaTelaListagem janela;
	
	public OuvinteDoBotaoEditarDaTelaDeListagem (JanelaTelaListagem  janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent arg0) {
		if(janela.getTabela().getSelectedRow() == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		else{
			new EscolhaDoTreinador(janela);
		}
		
	}

}
