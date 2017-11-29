package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ObsVisualizarTreinos implements ActionListener{
	
	private JanelaTelaListagem j;
	
	public ObsVisualizarTreinos(JanelaTelaListagem j) {
		this.j = j;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		

		if(j.getTabela().getSelectedRow() == -1){
			JOptionPane.showMessageDialog(j, "Por favor Selecione uma linha valida");
		}
		else{
			new JanelaVisualizarTreino(j.getTabela().getSelectedRow());
			j.dispose();
		}
		
	}

}
