package aluno;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class ObsVisializarCredenciais implements ActionListener{

	private JanelaTelaListagem j;
	
	public ObsVisializarCredenciais(JanelaTelaListagem j) {
		this.j = j;
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(j.getTabela().getSelectedRow() == -1){
			JOptionPane.showMessageDialog(j, "Por favor Selecione uma linha valida");
		}
		else{
			new JanelaVisualizarCredenciais(j.getTabela().getSelectedRow());
			j.dispose();
		}
	}

}
