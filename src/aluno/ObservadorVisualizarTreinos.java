package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ObservadorVisualizarTreinos implements ActionListener{
	
	private TelaVisualizarTreinos janela;
	
	public ObservadorVisualizarTreinos(TelaVisualizarTreinos janela){
		this.janela = janela;
	}

	public void actionPerformed(ActionEvent arg0) {
		
		int select = janela.getTabela().getSelectedRow();
		if(select == -1){
			JOptionPane.showMessageDialog(janela, "Por favor Selecione uma linha valida");
		}
		else{
			
			new ListarTreinos(select);
			janela.dispose();
		}
	}
}